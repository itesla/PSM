package org.power_systems_modelica.psm.modelica.events;

/*
 * #%L
 * Add events to existing Modelica network models
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collection;
import java.util.Iterator;

import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.dd.ModelForEvent.Injection;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.DynamicNetworkReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.MapReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.ModelicaNetworkBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaEventAdder extends ModelicaNetworkBuilder
{
	public ModelicaEventAdder(ModelicaDocument mo, DynamicDataRepository ddr, Network network,
			Collection<Event> events)
	{
		super(ddr, network);
		this.original = mo;
		this.events = events;
	}

	public ModelicaDocument addEvents(Collection<UnresolvedRef> unresolved)
	{
		String systemIdWithEvents = original.getSystemModel().getId() + "_withEvents";
		ModelicaDocument moWithEvents = original.copy(systemIdWithEvents);
		setModelicaDocument(moWithEvents);
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));
		
		// events.forEach(ev -> addEvent(ev, moWithEvents, unresolved));
		int kk = 0;
		for (Iterator<Event> k = events.iterator(); k.hasNext(); kk++)
		{
			Event ev = k.next();
			ev.setInstance("" + kk);
			addEvent(ev, moWithEvents, unresolved);
		}
		
		setAllDynamicModelsAdded(true);
		removeResolver("DYNN");
		return moWithEvents;
	}

	private void addEvent(
			Event ev,
			ModelicaDocument mo,
			Collection<UnresolvedRef> unresolved)
	{
		// Adding an event on an element of the network model means:
		// - First obtain a dynamic model for the event type from the dynamic data repository
		// - Then insert the event model in the system, according to its specific type of insertion
		// The kind of insertion of an event in the system model can be one of two possibilities:
		// - Add. Old dynamic model for the element is preserved. Event model is connected to original model connectors (fault in a bus)
		// - Replace. Previous dynamic model is completely replaced by event model. Elements connected to original model are now connected to event model (fault in a line)

		ModelicaModel m = getDdr().getModelicaModelForEvent(ev);
		if (m == null)
		{
			LOG.warn("No dynamic model found for event {}", ev.getEvent());
			return;
		}
		ModelicaModel mp = getDynamicModelFor(ev.getElement().getId());
		registerResolver(DynamicDataRepository.EVENT_PARAMS_DATA_SOURCE,
				new MapReferenceResolver(ev.getParameters()));
		registerResolver(DynamicDataRepository.PREVIOUS_DYNAMIC_MODEL,
				new PreviousDynamicModelReferenceResolver(mp));

		Injection injection = getDdr().getEventInjection(ev.getEvent());
		switch (injection)
		{
		case ADD:
			addDynamicModel(m);
			addInterconnections(m, unresolved);
			break;
		case REPLACE:
			removeDynamicModel(mp);
			addDynamicModel(m);
			addInterconnections(m, unresolved);
			break;
		}

		removeResolver(DynamicDataRepository.EVENT_PARAMS_DATA_SOURCE);
	}

	private final ModelicaDocument	original;
	private final Collection<Event>	events;

	private static final Logger		LOG	= LoggerFactory.getLogger(ModelicaEventAdder.class);
}
