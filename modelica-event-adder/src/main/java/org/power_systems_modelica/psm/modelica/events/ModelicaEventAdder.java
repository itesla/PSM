package org.power_systems_modelica.psm.modelica.events;

import java.util.ArrayList;
import java.util.Collection;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.DynamicNetworkReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.MapReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.ModelicaNetworkBuilder;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRefsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdder extends ModelicaNetworkBuilder
{
	public ModelicaEventAdder(ModelicaDocument mo, DynamicDataRepository ddr, Network network,
			Collection<Event> events)
	{
		super(ddr, network);
		this.original = mo;
		this.events = events;
	}

	public ModelicaDocument addEvents() throws UnresolvedRefsException
	{
		Collection<UnresolvedRef> unresolved = new ArrayList<>();

		String systemIdWithEvents = original.getSystemModel().getId() + "_withEvents";
		ModelicaDocument moWithEvents = original.copy(systemIdWithEvents);
		setModelicaDocument(moWithEvents);
		registerResolver("DYNN", new DynamicNetworkReferenceResolver(getNetwork(), this));
		events.forEach(ev -> addEvent(ev, moWithEvents, unresolved));
		setAllDynamicModelsAdded(true);
		removeResolver("DYNN");

		if (!unresolved.isEmpty()) throw new UnresolvedRefsException(unresolved);

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

		ModelicaModel m = getDdr().getModelicaModelForEvent(ev.getId(), ev.getElement());
		if (m == null)
		{
			LOG.warn("No dynamic model found for event {}", ev.getId());
			return;
		}
		ModelicaModel mp = getDynamicModelFor(ev.getElement().getId());
		registerResolver(DynamicDataRepository.EVENT_PARAMS_DATA_SOURCE,
				new MapReferenceResolver(ev.getParameters()));
		registerResolver(DynamicDataRepository.PREVIOUS_DYNAMIC_MODEL,
				new PreviousDynamicModelReferenceResolver(mp));

		Injection injection = getDdr().getEventInjection(ev.getId());
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
