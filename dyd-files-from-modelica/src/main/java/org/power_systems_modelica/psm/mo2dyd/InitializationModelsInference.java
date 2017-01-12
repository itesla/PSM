package org.power_systems_modelica.psm.mo2dyd;

import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.ddr.Stage;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.DynamicDataRepositoryDydFiles;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelForAssociation;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.Parameter;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetContainer;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSetReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitializationModelsInference
{
	public static Model inferModel(
			String staticId,
			Model m,
			DynamicDataRepositoryDydFiles ddr,
			String paramSetContainerName)
	{
		// To infer initialization models,
		// We are only changing names of components
		// taken from the dynamic model definition used for simulation
		// adding the suffix "_Init"

		Model minit = null;
		if (m instanceof ModelForElement)
			minit = new ModelForElement(staticId, m.getId());
		else if (m instanceof ModelForAssociation)
			minit = new ModelForAssociation(((ModelForAssociation) m).getAssociation(), m.getId());
		if (minit == null) return null;

		minit.setStage(Stage.INITIALIZATION);
		minit.addComponents(m.getComponents()
				.stream()
				.map(c -> inferInitializationComponent(staticId, c, ddr, paramSetContainerName))
				.collect(Collectors.toList()));
		minit.addConnections(m.getConnections());
		minit.addInterconnections(m.getInterconnections());
		return minit;
	}

	private static Component inferInitializationComponent(
			String staticId,
			Component c,
			DynamicDataRepositoryDydFiles ddr,
			String paramSetContainerName)
	{
		ParameterSetContainer par = ddr.getParameterSetContainer(paramSetContainerName);

		Component ci = new Component(c.getId(), c.getType() + "_Init");
		if (c.getParameterSet() != null)
		{
			ParameterSet psi = new ParameterSet(c.getParameterSet().getId());
			List<Parameter> params0 = c.getParameterSet().getParameters();
			psi.add(filterOutInitParameters(params0));
			ci.setParameterSet(psi);
		}
		else if (c.getParameterSetReference() != null)
		{
			String psetId0Ref = c.getParameterSetReference().getSet();
			String psetId0 = psetId0Ref.replace("{staticId}", staticId);
			String psetiIdRef = psetId0Ref.concat("_Init");
			String psetiId = psetiIdRef.replace("{staticId}", staticId);

			ParameterSet pseti = new ParameterSet(psetiId);
			par.add(pseti);
			List<Parameter> params0 = par.get(psetId0).getParameters();
			List<Parameter> paramsi = filterOutInitParameters(params0);
			pseti.add(paramsi);
			ParameterSetReference psetir = new ParameterSetReference(
					par.getName(),
					psetiIdRef);
			ci.setParameterSetReference(psetir);
		}
		return ci;
	}

	public static void saveInitModelParams(
			String staticId,
			Model m,
			DynamicDataRepositoryDydFiles ddr,
			String paramSetContainerName)
	{
		m.getComponents().forEach(
				c -> saveInitializationComponentParams(staticId, c, ddr, paramSetContainerName));
	}

	private static void saveInitializationComponentParams(
			String staticId,
			Component c0,
			DynamicDataRepositoryDydFiles ddr,
			String paramSetContainerName)
	{
		ParameterSetContainer par = ddr.getParameterSetContainer(paramSetContainerName);
		if (c0.getParameterSetReference() != null)
		{
			String psetId0Ref = c0.getParameterSetReference().getSet();
			String psetId0 = psetId0Ref.replace("{staticId}", staticId);
			String psetiIdRef = psetId0Ref.concat("_Init");
			String psetiId = psetiIdRef.replace("{staticId}", staticId);

			ParameterSet pseti = new ParameterSet(psetiId);
			par.add(pseti);

			ParameterSet pset0 = par.get(psetId0);
			if (pset0 == null)
			{
				LOG.error("parameter set not found [" + psetId0 + "]");
				LOG.error("\n    " + par.getSets().stream().map(s -> s.getId())
						.collect(Collectors.joining("\n    ")));
				throw new RuntimeException("adios");
			}
			List<Parameter> params0 = par.get(psetId0).getParameters();
			List<Parameter> paramsi = filterOutInitParameters(params0);
			pseti.add(paramsi);
		}
	}

	private static List<Parameter> filterOutInitParameters(List<Parameter> params)
	{
		return params.stream()
				.filter(p -> !(p.getName().startsWith("init_")))
				.collect(Collectors.toList());
	}

	private static final Logger LOG = LoggerFactory.getLogger(InitializationModelsInference.class);
}
