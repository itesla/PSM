package org.power_systems_modelica.psm.modelica;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		return normalizedDynamicIdentifier("DM".concat(id));
	}

	public static String dynamicDeclarationIdFromModelForType(String type, String name, String id)
	{
		// This is to ease comparison with previous Modelica transformations
		type = ModelicaTricks.legacyType(type);
		return normalizedDynamicIdentifier(type.concat("_").concat(id));
	}

	public static String dynamicDeclarationIdFromModelForEvent(String event, String name, String id)
	{
		return normalizedDynamicIdentifier(event.concat("_").concat(id));
	}

	private static String normalizedDynamicIdentifier(String id)
	{
		return id.replace("-", "_");
	}

	public static Map<String, ModelicaModel> groupByStaticId(ModelicaDocument mo)
	{
		// Group all declarations and equations by static id
		Map<String, List<ModelicaDeclaration>> ds = mo.getSystemModel()
				.getDeclarations()
				.stream()
				.collect(Collectors.groupingBy(d -> getStaticId(d)));
		Map<String, List<ModelicaEquation>> eqs = mo.getSystemModel()
				.getEquations()
				.stream()
				.collect(Collectors.groupingBy(eq -> getStaticId(eq)));

		// These are all the static ids that have been found
		Set<String> sids = new HashSet<>();
		sids.addAll(ds.keySet());
		sids.addAll(eqs.keySet());

		// Build a ModelicaModel that contains all found declarations and equations for each static id
		Map<String, ModelicaModel> models = sids.stream()
				.collect(Collectors.toMap(sid -> sid, sid -> {
					ModelicaModel m = buildModelicaModel(sid);
					if (ds.get(sid) != null) m.addDeclarations(ds.get(sid));
					if (eqs.get(sid) != null) m.addEquations(eqs.get(sid));
					return m;
				}));

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

	private static String getStaticId(ModelicaDeclaration d)
	{
		String m = getStaticIdFromAnnotation(d.getAnnotation());
		if (m == null) m = getStaticIdFromDynamicId(d.getId());
		if (m == null) m = SYSTEM_ID;
		return m;
	}

	private static String getStaticId(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			ModelicaConnect eqc = (ModelicaConnect) eq;
			String m1 = getStaticIdFromDynamicId(ModelicaUtil.ref2idvar(eqc.getRef1())[0]);
			String m2 = getStaticIdFromDynamicId(ModelicaUtil.ref2idvar(eqc.getRef2())[0]);
			if (m1.equals(m2)) return m1;
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
		// TODO if (annotation.contains("ExternalReference")) return whichModelFromExternalReference();
		return null;
	}

	private static ModelicaModel buildModelicaModel(String id)
	{
		String dmid = ModelicaUtil.dynamicIdFromStaticId(id);
		ModelicaModel m = new ModelicaModel(dmid);
		m.setStaticId(id);
		return m;
	}

	private static final String	ID_VAR_SEPARATOR		= ".";
	private static final String	QUOTED_ID_VAR_SEPARATOR	= Pattern.quote(ID_VAR_SEPARATOR);

	private static final String	SYSTEM_ID				= "_SYSTEM_";
	private static final String	INTERCONNECTIONS_ID		= "_INTERCONNECTIONS_";
}
