package org.power_systems_modelica.psm.workflow.psm;

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.events.Event;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.workflow.WorkflowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class ModelicaEventAdderTask extends WorkflowTask
{
	public ModelicaEventAdderTask(String id)
	{
		super(id);
	}

	@Override
	public String getName()
	{
		return "Modelica network event adder";
	}

	@Override
	public void configure(Configuration config)
	{
		ddrType = config.getParameter("ddrType");
		ddrLocation = config.getParameter("ddrLocation");
		eventData = config.getParameter("events");
	}

	@Override
	public void run()
	{
		running();
		try
		{
			Network n = (Network) workflow.getResults("network");
			ModelicaDocument mo = (ModelicaDocument) workflow.getResults("mo");

			DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create(
					ddrType,
					ddrLocation);
			ddr.connect();
			List<Event> events = eventsFrom(eventData, n);

			ModelicaEventAdder adder = new ModelicaEventAdder(mo, ddr, n, events);
			ModelicaDocument moe = adder.addEvents();
			publish(SCOPE_GLOBAL, "moWithEvents", moe);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	private static List<Event> eventsFrom(String eventData, Network network)
	{
		List<Event> events = new ArrayList<>();
		String[] records = eventData.split("\n");
		for (String r : records)
		{
			String[] fields = r.split(",");
			String event = fields[0];
			String idelem = fields[1];
			Stream<String> paramss = Arrays.stream(fields, 2, fields.length);

			Identifiable<?> element = network.getIdentifiable(idelem);
			if (element == null)
			{
				LOG.error("Element not found in IIDM network {}", idelem);
				continue;
			}
			Map<String, Object> params = paramss
					.map(ps -> ps.split("="))
					.collect(Collectors.toMap(p -> p[0], p -> p[1]));

			events.add(new Event(event, element, params));
		}
		return events;
	}

	private String				ddrType;
	private String				ddrLocation;
	private String				eventData;

	private static final Logger	LOG	= LoggerFactory.getLogger(ModelicaEventAdder.class);
}
