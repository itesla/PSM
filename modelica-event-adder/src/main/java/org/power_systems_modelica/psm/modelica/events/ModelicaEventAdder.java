package org.power_systems_modelica.psm.modelica.events;

import java.util.Collection;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelicaEventAdder
{
	public ModelicaEventAdder(DynamicDataRepository ddr, ModelicaDocument mo)
	{
		this.ddr = ddr;
		this.original = mo;
	}

	public ModelicaDocument addEvents(Collection<Event> evs)
	{
		// FIXME working on it ... add events to a Modelica network model
		ModelicaDocument moWithEvents = original.shallowCopy();
		evs.forEach(ev -> addEvent(ev, moWithEvents));
		return moWithEvents;
	}

	private void addEvent(Event ev, ModelicaDocument mo)
	{
		// Adding an event on an element to a network model means
		// replacing the dynamic model of the element by a different dynamic model
		// obtained from the dynamic data repository from the event type

		ModelicaModel m = ddr.getModelicaModelForEvent(ev.getType(), ev.getElement());
		if (m == null)
		{
			LOG.warn("No dynamic model found for event type {}", ev.getType());
			return;
		}

		String id = ev.getElement().getId();

		m.setStaticId(id);
		// FIXME We have to resolve references in the ModelicaModel m using the list of values for parameters in the event
		m = resolveReferences(m, ev);

		// FIXME Request the ModelicaDocument to replace the old model with the new one
		mo.replaceModelForStaticId(id, m);
	}

	private ModelicaModel resolveReferences(ModelicaModel m, Event ev)
	{
		// FIXME reuse code from network model builder
		throw new RuntimeException(
				"resolveReferences in ModelicaModel from params given with the Event");
	}

	private final DynamicDataRepository	ddr;
	private final ModelicaDocument		original;

	private static final Logger			LOG	= LoggerFactory.getLogger(ModelicaEventAdder.class);
}
