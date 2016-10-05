package org.power_systems_modelica.psm.modelica.events;

import java.util.Collection;
import java.util.List;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
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
		evs.forEach(ev -> addEvent(ev, moWithEvents));
		return moWithEvents;
	}

	private void addEvent(Event ev, ModelicaDocument mo)
	{
		// Adding an event on an element of the network model means:
		// - First obtain a dynamic model for the event type from the dynamic data repository
		// - Then insert the event model in the system, according to specific type of insertion
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
		List<ModelicaConnect> connects = identifyPreviousInterconnections(mo, ev.getElement());

		// Build the new model and add it to the system
		String id = ev.getElement().getId();
		m.setStaticId(id);
		mo.getSystemModel().getDeclarations().addAll(resolveReferences(m.getDeclarations(), m, ev));
		mo.getSystemModel().getEquations().addAll(m.getEquations());

		// Interconnect the new model with the rest of the system
		switch (m.getInjection())
		{
		case ADD:
			break;
		case REPLACE:
			break;
		case INTERPOSE:
			break;
		}
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
