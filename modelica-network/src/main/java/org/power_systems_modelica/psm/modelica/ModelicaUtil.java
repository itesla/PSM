package org.power_systems_modelica.psm.modelica;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModelicaUtil
{
	public static String idvar2ref(String id, String var)
	{
		if (id == null) return var;
		String s = String.join(ID_VAR_SEPARATOR, id, var);
		return s;
	}

	public static String[] ref2idvar(String ref)
	{
		if (ref.contains(ID_VAR_SEPARATOR))
		{
			String[] parts = ref.split(QUOTED_ID_VAR_SEPARATOR);
			return parts;
		}
		else
		{
			String id = null;
			String var = ref;
			String[] parts = { id, var };
			return parts;
		}
	}

	public static String normalizedIdentifier(String id)
	{
		return id.replace("-", "_").replace(".", "_");
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
				.collect(Collectors.groupingBy(eq -> getNormalizedStaticId(eq)));
		Map<String, List<Annotation>> as = mo.getSystemModel()
				.getAnnotations()
				.stream()
				.collect(Collectors.groupingBy(
						a -> ModelicaUtil.normalizedIdentifier(a.getStaticId().orElse(SYSTEM_ID))));

		// These are all the static identifiers that have been found
		Set<String> sids = new HashSet<>();
		sids.addAll(ds.keySet());
		sids.addAll(eqs.keySet());
		sids.addAll(as.keySet());

		// Build a ModelicaModel that contains all found declarations and equations for each static id
		Map<String, ModelicaModel> models = sids.stream()
				.collect(Collectors.toMap(
						sid -> sid,
						sid -> buildModelicaModel(sid, ds.get(sid), eqs.get(sid), as.get(sid))));

		return models;
	}

	public static String getSystemStaticId()
	{
		return SYSTEM_ID;
	}

	public static boolean isInterconnection(ModelicaModel m)
	{
		return m.getStaticId().equals(INTERCONNECTIONS_ID);
	}

	public static List<ModelicaEquation> getInterconnections(
			Map<String, ModelicaModel> dynamicModelsByStaticId)
	{
		if (!dynamicModelsByStaticId.containsKey(INTERCONNECTIONS_ID))
			return Collections.emptyList();
		return dynamicModelsByStaticId.get(INTERCONNECTIONS_ID).getEquations();
	}

	public static boolean isSystemModel(ModelicaModel m)
	{
		return m.getStaticId().equals(SYSTEM_ID);
	}

	public static String getNormalizedStaticId(ModelicaDeclaration d)
	{
		String id = null;
		if (d.getAnnotation() != null)
		{
			Optional<String> oid = d.getAnnotation().getStaticId();
			if (oid.isPresent()) id = oid.get();
		}
		if (id == null) id = getStaticIdFromDynamicId(d.getId());
		if (id == null) id = SYSTEM_ID;
		return normalizedIdentifier(id);
	}

	private static String getNormalizedStaticId(ModelicaEquation eq)
	{
		if (eq instanceof ModelicaConnect)
		{
			ModelicaConnect eqc = (ModelicaConnect) eq;
			String id1 = getNormalizedStaticId(eqc, 1);
			String id2 = getNormalizedStaticId(eqc, 2);
			if (id1 != null && id1.equals(id2)) return id1;
			else return INTERCONNECTIONS_ID;
		}
		return SYSTEM_ID;
	}

	public static String getNormalizedStaticId(ModelicaConnect eqc, int side)
	{
		return getNormalizedStaticId(eqc, side, ModelicaUtil.ref2idvar(eqc.getRef(side))[0]);
	}

	private static String getNormalizedStaticId(ModelicaConnect eqc, int side, String dynamicId)
	{
		String id = null;
		if (eqc.getAnnotation() != null)
		{
			// If the equation is an interconnection it will have two different staticIds
			if (eqc.getAnnotation().getStaticId(side).isPresent())
				id = eqc.getAnnotation().getStaticId(side).get();
			// If the equation is a connection inside components of the same dynamic model there is only one staticId
			if (id == null)
				if (eqc.getAnnotation().getStaticId().isPresent())
					id = eqc.getAnnotation().getStaticId().get();
		}
		if (id == null) id = getStaticIdFromDynamicId(dynamicId);
		if (id == null) id = SYSTEM_ID;
		return normalizedIdentifier(id);
	}

	private static String getStaticIdFromDynamicId(String id)
	{
		return ModelicaTricks.staticIdFromDynamicId(id);
	}

	private static ModelicaModel buildModelicaModel(
			String staticId,
			List<ModelicaDeclaration> declarations,
			List<ModelicaEquation> equations,
			List<Annotation> annotations)
	{
		String dmid = dynamicIdFromStaticId(staticId);
		ModelicaModel m = new ModelicaModel(dmid);
		m.setStaticId(staticId);

		if (declarations != null) m.addDeclarations(declarations);
		if (equations != null) m.addEquations(equations);
		if (annotations != null)
		{
			List<ModelicaInterconnection> connectors = Annotation.readConnectors(annotations);
			m.setInterconnections(connectors);
		}

		return m;
	}

	private static String dynamicIdFromStaticId(String staticId)
	{
		return "DM_".concat(staticId);
	}

	private static final String	ID_VAR_SEPARATOR		= ".";
	private static final String	QUOTED_ID_VAR_SEPARATOR	= Pattern.quote(ID_VAR_SEPARATOR);

	private static final String	SYSTEM_ID				= "_SYSTEM_";
	private static final String	INTERCONNECTIONS_ID		= "_INTERCONNECTIONS_";
}
