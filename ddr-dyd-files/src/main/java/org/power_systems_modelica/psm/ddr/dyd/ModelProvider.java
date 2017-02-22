package org.power_systems_modelica.psm.ddr.dyd;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.power_systems_modelica.psm.ddr.StaticType;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import eu.itesla_project.iidm.network.Identifiable;

public class ModelProvider
{
	public ModelProvider(AssociationProvider associations)
	{
		this.associations = associations;
	}

	public Model getModel(Identifiable<?> e)
	{
		return getDynamicModelForId(ModelicaUtil.normalizedIdentifier(e.getId()))
				.orElse(getDynamicModelForAssociation(e)
						.orElse(getDynamicModelForStaticType(StaticType.from(e))
								.orElse(null)));
	}

	public ModelForEvent getModelForEvent(String e)
	{
		return dynamicModelsForEvent.get(e);
	}

	public Collection<String> getEvents()
	{
		return dynamicModelsForEvent.keySet();
	}

	public Optional<Model> getDynamicModelForId(String id)
	{
		return Optional.ofNullable(dynamicModelsForElement.get(id))
				.map(m -> m.stampOrigin("id"));
	}

	public Optional<Model> getDynamicModelForStaticType(StaticType type)
	{
		return Optional.ofNullable(dynamicModelsForType.get(type))
				.map(m -> m.stampOrigin("staticType"));
	}

	public Optional<Model> getDynamicModelForAssociation(Identifiable<?> e)
	{
		Optional<Association> association = associations.findAssociation(e);
		return association.map(a -> dynamicModelsForAssociation.get(a.getId()))
				.map(m -> m.stampOrigin("association"));
	}

	public Model getDynamicModelForAssociation(String associationId)
	{
		return dynamicModelsForAssociation.get(associationId);
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
		else if (m instanceof ModelForAssociation)
		{
			dynamicModelsForAssociation.put(
					((ModelForAssociation) m).getAssociation(),
					(ModelForAssociation) m);
		}
		else if (m instanceof ModelForType)
			dynamicModelsForType.put(((ModelForType) m).getType(), (ModelForType) m);
		else if (m instanceof ModelForEvent)
			dynamicModelsForEvent.put(((ModelForEvent) m).getEvent(), (ModelForEvent) m);
	}

	private final AssociationProvider				associations;
	private final Map<String, ModelForElement>		dynamicModelsForElement		= new HashMap<>();
	private final Map<String, ModelForAssociation>	dynamicModelsForAssociation	= new HashMap<>();
	private final Map<StaticType, ModelForType>		dynamicModelsForType		= new HashMap<>();
	private final Map<String, ModelForEvent>		dynamicModelsForEvent		= new HashMap<>();
}
