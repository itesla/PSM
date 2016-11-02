package org.power_systems_modelica.psm.ddr.dyd;

import java.util.HashMap;
import java.util.Map;

import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;

public class ModelProvider
{
	public Model getModel(Identifiable<?> e)
	{
		Model mdef = getDynamicModelForId(ModelicaUtil.normalizedIdentifier(e.getId()));
		if (mdef == null) mdef = getDynamicModelForStaticType(getType(e));
		return mdef;
	}

	public ModelForEvent getModelForEvent(String e)
	{
		return dynamicModelsForEvent.get(e);
	}

	public Model getDynamicModelForId(String id)
	{
		return dynamicModelsForElement.get(id);
	}

	public Model getDynamicModelForStaticType(String type)
	{
		return dynamicModelsForType.get(type);
	}

	public void add(Model m)
	{
		// We index each model added explicitly
		index(m);
	}

	private void index(Model m)
	{
		if (m instanceof ModelForElement)
			dynamicModelsForElement.put(((ModelForElement) m).getStaticId(), (ModelForElement) m);
		else if (m instanceof ModelForType)
			dynamicModelsForType.put(((ModelForType) m).getType(), (ModelForType) m);
		else if (m instanceof ModelForEvent)
			dynamicModelsForEvent.put(((ModelForEvent) m).getEvent(), (ModelForEvent) m);
	}

	private String getType(Identifiable<?> e)
	{
		if (e instanceof Bus) return "Bus";
		else if (e instanceof Line) return "Line";
		else if (e instanceof TwoWindingsTransformer) return "Transformer";
		else if (e instanceof Load) return "Load";
		else if (e instanceof ShuntCompensator) return "Shunt";
		return null;
	}

	private final Map<String, ModelForElement>	dynamicModelsForElement	= new HashMap<>();
	private final Map<String, ModelForType>		dynamicModelsForType	= new HashMap<>();
	private final Map<String, ModelForEvent>	dynamicModelsForEvent	= new HashMap<>();
}
