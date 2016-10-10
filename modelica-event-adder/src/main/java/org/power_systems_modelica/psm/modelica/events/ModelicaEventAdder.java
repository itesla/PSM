package org.power_systems_modelica.psm.modelica.events;

import java.util.Collection;
import java.util.List;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository.Injection;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.builder.DynamicNetworkReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.MapReferenceResolver;
import org.power_systems_modelica.psm.modelica.builder.ModelicaNetworkBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdder extends ModelicaNetworkBuilder
{
	public ModelicaEventAdder(ModelicaDocument mo, DynamicDataRepository ddr, Network network)
	{
		super(ddr, network);
		this.original = mo;
	}

	public ModelicaDocument addEvents(Collection<Event> evs)
	{
		ModelicaDocument moWithEvents = original.copy();
		
		// FIXME We are changing the state of this EventAdder, 
		// We should not allow other threads to invoke this method
		// until we are finished adding these events
		// Either declare this method as "synchronized" or force the builder to be used only for a given set of events
		DynamicNetworkReferenceResolver dynnr = new DynamicNetworkReferenceResolver(getNetwork(), moWithEvents);
		registerResolver("DYNN", dynnr);

		evs.forEach(ev -> addEvent(ev, moWithEvents, dynnr));
		
		removeResolver("DYNN");
		
		return moWithEvents;
	}

	private void addEvent(Event ev, ModelicaDocument mo, DynamicNetworkReferenceResolver dynnr)
	{
		// Adding an event on an element of the network model means:
		// - First obtain a dynamic model for the event type from the dynamic data repository
		// - Then insert the event model in the system, according to its specific type of insertion
		// The kind of insertion of an event in the system model can be one of three possibilities:
		// - Add. Old dynamic model for the element is preserved. Event model is connected to original model connectors (fault in a bus)
		// - Replace. Previous dynamic model is completely replaced by event model. Elements connected to original model are now connected to event model (fault in a line)
		// - Interpose. Previous dynamic model is connected with event model, and event model is connected with rest of the system (open a line, introducing breakers)

		ModelicaModel m = getDdr().getModelicaModelForEvent(ev.getId(), ev.getElement());
		if (m == null)
		{
			LOG.warn("No dynamic model found for event {}", ev.getId());
			return;
		}

		// Identify existing model interconnections with the rest of the system
		// FIXME only identify them if we have something to do with them ...
		List<ModelicaConnect> connects = identifyPreviousInterconnections(mo, ev.getElement());

		// Add the dynamic model for the event to the system
		mo.getSystemModel().addDeclarations(resolveReferences(m.getDeclarations(), m, ev));
		mo.getSystemModel().addEquations(m.getEquations());

		// And then interconnect the new model with the rest of the system
		Injection injection = getDdr().getInjectionForEvent(ev.getId());
		switch (injection)
		{
		case ADD:
			addConnections(m, mo);
			break;
		case REPLACE:
			// FIXME remove previous connections
			addConnections(m, mo);
			break;
		case INTERPOSE:
			// FIXME ???
			break;
		}
		
		// FIXME When adding we should be merging declarations and equations 
		// (add fault to a bus increases the dynamic model of the bus, is not a substitution)
		// problem: the dynamic model of the bus will have two connectors with pin "p" after adding a fault
		// to solve this, the pin for the bus fault could be declared as "used" (not "reusable")
		// in general, only pins of buses are "reusables" ???
		dynnr.addModel(m);
	}

	private List<ModelicaDeclaration> resolveReferences(
			List<ModelicaDeclaration> ds0,
			ModelicaModel m,
			Event ev)
	{
		registerResolver("EVENT", new MapReferenceResolver(ev.getParameters()));
		List<ModelicaDeclaration> ds = super.resolveReferences(ds0, m);
		removeResolver("EVENT");
		return ds;
	}

	private List<ModelicaConnect> identifyPreviousInterconnections(
			ModelicaDocument mo,
			Identifiable<?> element)
	{
		// FIXME null
		return null;
	}

	private final ModelicaDocument	original;

	private static final Logger		LOG	= LoggerFactory.getLogger(ModelicaEventAdder.class);
}
