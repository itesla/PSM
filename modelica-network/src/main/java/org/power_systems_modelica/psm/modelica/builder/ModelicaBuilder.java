package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaTricks;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelicaBuilder
{
	protected void registerResolver(String dataSource, ReferenceResolver resolver)
	{
		referenceResolvers.put(dataSource, resolver);
	}

	protected void removeResolver(String dataSource)
	{
		referenceResolvers.remove(dataSource);
	}

	protected List<ModelicaDeclaration> resolveReferences(
			List<ModelicaDeclaration> ds0,
			ModelicaModel m)
	{
		List<ModelicaDeclaration> ds = ds0
				.stream()
				.map(d -> resolveReferences(d, m))
				.collect(Collectors.toList());
		return ds;
	}

	private ModelicaDeclaration resolveReferences(
			ModelicaDeclaration d,
			ModelicaModel m)
	{
		// TODO consider if we have to resolveReferences only in arguments or also in assignments
		if (d.isAssignment()) return d;

		List<ModelicaArgument> args = d
				.getArguments()
				.stream()
				.map(a -> resolveReference(a, m, d))
				.collect(Collectors.toList());
		return new ModelicaDeclaration(d.getType(), d.getId(), args, d.isParameter());
	}

	private ModelicaArgument resolveReference(
			ModelicaArgument a0,
			ModelicaModel m,
			ModelicaDeclaration d)
	{
		ModelicaArgument a = a0;
		if (a0 instanceof ModelicaArgumentReference)
			a = resolveReference(((ModelicaArgumentReference) a0), m, d);
		return a;
	}

	private ModelicaArgument resolveReference(
			ModelicaArgumentReference a0,
			ModelicaModel m,
			ModelicaDeclaration d)
	{
		Object value = null;

		ReferenceResolver r = referenceResolvers.get(a0.getDataSource());
		if (r == null)
		{
			LOG.warn("No resolver found for reference to data source {}", a0.getDataSource());
			return null;
		}

		value = r.resolveReference(a0.getSourceName(), m, d);
		if (value == null)
		{
			String msg = new StringBuilder()
					.append("could not resolve reference ")
					.append(a0.getSourceName())
					.append(" in data source ")
					.append(a0.getDataSource())
					.append(" for model with staticId ")
					.append(m.getStaticId())
					.toString();
			LOG.error(msg);
			throw new RuntimeException(msg);
		}
		return new ModelicaArgument(a0.getName(), value);
	}

	public static Collection<ModelicaModel> groupByStaticId(ModelicaDocument mo)
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

		return models.values();
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

	private static final String						SYSTEM_ID			= "_SYSTEM_";
	private static final String						INTERCONNECTIONS_ID	= "_INTERCONNECTIONS_";

	private final Map<String, ReferenceResolver>	referenceResolvers	= new HashMap<>();
	private static final Logger						LOG					= LoggerFactory
			.getLogger(ModelicaBuilder.class);
}
