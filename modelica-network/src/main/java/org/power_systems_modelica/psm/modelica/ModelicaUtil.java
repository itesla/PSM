package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModelicaUtil
{
	public static String idvar2ref(String id, String var)
	{
		String s = String.join(ID_VAR_SEPARATOR, id, var);
		return s;
	}

	public static String[] ref2idvar(String ref)
	{
		String[] parts = ref.split(QUOTED_ID_VAR_SEPARATOR);
		return parts;
	}

	public static String dynamicIdFromStaticId(String id)
	{
		return normalizedIdentifier("DM".concat(id));
	}

	public static String dynamicDeclarationIdFromModelForType(String type, String name, String id)
	{
		// This is to ease comparison with previous Modelica transformations
		type = ModelicaTricks.legacyType(type);
		return normalizedIdentifier(type.concat("_").concat(id));
	}

	public static String dynamicDeclarationIdFromModelForEvent(String event, String name, String id)
	{
		return normalizedIdentifier(event.concat("_").concat(id));
	}

	private static String normalizedIdentifier(String id)
	{
		return id.replace("-", "_");
	}

	public static Map<String, ModelicaModel> groupByNormalizedStaticId(ModelicaDocument mo)
	{
		// When we try to recover static identifier from dynamic identifier
		// we can not recover the original static identifier because it has been normalized
		// (Modelica do not allow hyphens in identifiers, that are commonly used in IIDM branch identifiers)
		// This would be possible only if we recover static identifiers from annotations (where exact static id is preserved)

		// Group all declarations and equations by static id
		Map<String, List<ModelicaDeclaration>> ds = mo.getSystemModel()
				.getDeclarations()
				.stream()
				.collect(Collectors.groupingBy(d -> getNormalizedStaticId(d)));
		Map<String, List<ModelicaEquation>> eqs = mo.getSystemModel()
				.getEquations()
				.stream()
				.collect(Collectors.groupingBy(eq -> getStaticId(eq)));

		// These are all the static identifiers that have been found
		Set<String> sids = new HashSet<>();
		sids.addAll(ds.keySet());
		sids.addAll(eqs.keySet());

		// Build a ModelicaModel that contains all found declarations and equations for each static id
		Map<String, ModelicaModel> models = sids.stream()
				.collect(Collectors.toMap(
						sid -> sid,
						sid -> buildModelicaModel(sid, ds.get(sid), eqs.get(sid))));

		return models;
	}

	public static boolean isInterconnection(ModelicaModel m)
	{
		return m.getStaticId().equals(INTERCONNECTIONS_ID);
	}

	public static boolean isSystemModel(ModelicaModel m)
	{
		return m.getStaticId().equals(SYSTEM_ID);
	}

	public static boolean isPsmDummy(ModelicaDeclaration d)
	{
		return d.getType().equals(PSM_DUMMY_MODEL_NAME);
	}

	private static String getNormalizedStaticId(ModelicaDeclaration d)
	{
		String m = getStaticIdFromAnnotation(d.getAnnotation());
		if (m == null) m = getStaticIdFromDynamicId(d.getId());
		if (m == null) m = SYSTEM_ID;
		return normalizedIdentifier(m);
	}

	private static String getStaticId(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			ModelicaConnect eqc = (ModelicaConnect) eq;
			String m1 = getStaticIdFromDynamicId(ModelicaUtil.ref2idvar(eqc.getRef1())[0]);
			String m2 = getStaticIdFromDynamicId(ModelicaUtil.ref2idvar(eqc.getRef2())[0]);
			if (m1 != null && m1.equals(m2)) return m1;
			else return INTERCONNECTIONS_ID;
		}
		return SYSTEM_ID;
	}

	private static String getStaticIdFromDynamicId(String id)
	{
		return ModelicaTricks.staticIdFromDynamicId(id);
	}

	private static String getStaticIdFromAnnotation(Annotation annotation)
	{
		if (annotation == null) return null;

		String text = annotation.getText();
		if (text == null) return null;

		Matcher m = REF_ANNOTATION.matcher(text);
		if (!m.find()) return null;

		MatchResult r = m.toMatchResult();
		text = r.group(1);

		String[] attributes = text.split(",");
		for (String a : attributes)
		{
			String[] keyvalue = a.split("=");
			if (keyvalue.length < 2) continue;
			String key = keyvalue[0];
			String value = keyvalue[1].replace("\"", "");
			if (key.equals(REF_ATTR_STATIC_ID)) return value;
		}
		return null;
	}

	private static ModelicaModel buildModelicaModel(
			String id,
			List<ModelicaDeclaration> declarations,
			List<ModelicaEquation> equations)
	{
		String dmid = ModelicaUtil.dynamicIdFromStaticId(id);
		ModelicaModel m = new ModelicaModel(dmid);
		m.setStaticId(id);

		if (declarations != null)
		{
			m.addDeclarations(declarations);
			List<ModelicaConnector> connectors = readConnectors(declarations);
			m.setConnectors(connectors);
		}
		if (equations != null) m.addEquations(equations);
		return m;
	}

	private static List<ModelicaConnector> readConnectors(List<ModelicaDeclaration> declarations)
	{
		return declarations.stream()
				.map(d -> d.getAnnotation())
				.filter(Objects::nonNull)
				.map(a -> a.getText())
				.filter(Objects::nonNull)
				.map(t -> readConnectors(t))
				.filter(Objects::nonNull)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public static String writeConnectors(List<ModelicaConnector> connectors)
	{
		if (connectors == null || connectors.isEmpty()) return "";
		return connectors.stream()
				.map(c -> writeConnector(c))
				.collect(Collectors.joining(","));
	}

	public static List<ModelicaConnector> readConnectors(String text)
	{
		List<ModelicaConnector> connectors = new ArrayList<>();
		Matcher m = CONNECTOR_ANNOTATION.matcher(text);
		while (m.find())
		{
			MatchResult r = m.toMatchResult();
			String sc = r.group(1);
			connectors.add(readConnector(sc));
		}
		return connectors;
	}

	private static ModelicaConnector readConnector(String text)
	{
		// Format expected is: id=<identifier>,pin=<pin name>,target=<dataSource>:<item>:<pin>
		// Values may be quoted
		// Target is not parsed
		// Attributes may be given in any order
		// id and target are optional

		String id = null, pin = null, target = null;
		String[] attributes = text.split(",");
		for (String a : attributes)
		{
			String[] keyvalue = a.split("=");
			if (keyvalue.length < 2) continue;
			String key = keyvalue[0];
			String value = keyvalue[1].replace("\"", "");
			switch (key)
			{
			case CONNECTOR_ATTR_ID:
				id = value;
				break;
			case CONNECTOR_ATTR_PIN:
				pin = value;
				break;
			case CONNECTOR_ATTR_TARGET:
				target = value;
				break;
			}
		}
		if (id == null && target == null) return new ModelicaConnector(pin);
		else return new ModelicaConnector(id, pin, target);
	}

	public static String writeRefStaticId(String staticId)
	{
		if (staticId == null) return null;
		return REF_KEYWORD
				.concat("(")
				.concat(writeAttributeValue("staticId", staticId))
				.concat(")");
	}

	private static String writeConnector(ModelicaConnector c)
	{
		Optional<String> sid, spin, starget;

		sid = c.getId().map(id -> writeAttributeValue(CONNECTOR_ATTR_ID, id));
		spin = Optional.of(writeAttributeValue(CONNECTOR_ATTR_PIN, c.getPin()));
		starget = c.getTarget().map(t -> writeAttributeValue(CONNECTOR_ATTR_TARGET, t));

		String attrs = Arrays.asList(sid, spin, starget).stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.joining(","));

		return CONNECTOR_KEYWORD
				.concat("(")
				.concat(attrs)
				.concat(")");
	}

	private static String writeAttributeValue(String attribute, String value)
	{
		return attribute.concat("=\"").concat(value).concat("\"");
	}

	private static final String		ID_VAR_SEPARATOR		= ".";
	private static final String		QUOTED_ID_VAR_SEPARATOR	= Pattern.quote(ID_VAR_SEPARATOR);

	private static final String		SYSTEM_ID				= "_SYSTEM_";
	private static final String		INTERCONNECTIONS_ID		= "_INTERCONNECTIONS_";

	private static final String		CONNECTOR_KEYWORD		= "PSMConnector";
	private static final String		CONNECTOR_ATTR_ID		= "id";
	private static final String		CONNECTOR_ATTR_PIN		= "pin";
	private static final String		CONNECTOR_ATTR_TARGET	= "target";
	private static final Pattern	CONNECTOR_ANNOTATION	= Pattern
			.compile(CONNECTOR_KEYWORD + "\\(([^\\)]*)\\)");

	private static final String		REF_KEYWORD				= "PSMRef";
	private static final String		REF_ATTR_STATIC_ID		= "staticId";
	private static final Pattern	REF_ANNOTATION			= Pattern
			.compile(REF_KEYWORD + "\\(([^\\)]*)\\)");

	public static final String		PSM_DUMMY_MODEL_NAME	= "PSMDummy";
}
