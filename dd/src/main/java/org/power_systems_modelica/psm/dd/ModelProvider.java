package org.power_systems_modelica.psm.dd;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelProvider
{
	public ModelProvider(AssociationProvider associations)
	{
		this.associations = associations;
	}

	public Model getModel(Identifiable<?> e)
	{
		List<Model> mdefs = getModels(e);
		if (mdefs.isEmpty())
			return null;
		
		return mdefs.get(0);
	}

	public List<Model> getModels(Identifiable<?> e)
	{
		Optional<Model> idMdef = getDynamicModelForId(ModelicaUtil.normalizedIdentifier(e.getId()));
		Stream<Optional<Model>> assocs = getDynamicModelForAssociation(e);
		List<Model> assocMdefs = new ArrayList<>();
		assocs.forEach(a -> {
			if (a.isPresent())
				assocMdefs.add(a.get());
		});
		
		Optional<Model> typeMdef = getDynamicModelForStaticType(StaticType.from(e));

		List<Model> mdefs = new ArrayList<>();
		if (idMdef.isPresent())
			mdefs.add(idMdef.get());
		mdefs.addAll(assocMdefs);
		if (typeMdef.isPresent())
			mdefs.add(typeMdef.get());

		return mdefs;
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

	public Stream<Optional<Model>> getDynamicModelForAssociation(Identifiable<?> e)
	{
		Stream<Association> association = associations.findAssociations(e);
		Stream<Optional<Model>> mdefs = association.map(a -> 
			Optional.ofNullable(dynamicModelsForAssociation.get(a.getId())));
		return mdefs.map(opt -> 
			opt.map(m -> m.stampOrigin("association")));
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
