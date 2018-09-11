package org.power_systems_modelica.psm.workflow.psm;

/*
 * #%L
 * Power systems on Modelica workflow
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.power_systems_modelica.psm.workflow.Workflow.ResultsScope.SCOPE_GLOBAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.builder.UnresolvedRef;
import org.power_systems_modelica.psm.modelica.events.ModelicaEventAdder;
import org.power_systems_modelica.psm.workflow.WorkflowTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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
		network = config.getParameter("network");
		modelicaDocument = config.getParameter("modelicaDocument");
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
			Network n = (Network) workflow.getResults(network);
			ModelicaDocument mo = (ModelicaDocument) workflow.getResults(modelicaDocument);

			DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create(
					ddrType,
					ddrLocation);
			ddr.connect();
			List<Event> events = eventsFrom(eventData, n);

			ModelicaEventAdder adder = new ModelicaEventAdder(mo, ddr, n, events);
			Collection<UnresolvedRef> unresolved = new ArrayList<>();
			ModelicaDocument moe = adder.addEvents(unresolved);
			publish(SCOPE_GLOBAL, "moWithEvents", moe);
			succeded();
		}
		catch (Exception x)
		{
			failed(x);
		}
	}

	public static List<Event> eventsFrom(String eventData, Network network)
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
	
	public static String eventsToString(List<Event> events) {
		String eventsString = new StringBuilder().toString();
		
		
		for(Event event : events) {
			String evString = new StringBuilder().append(event.getEvent()).append(",")
					.append(event.getElement().getId()).append(",").toString();
			
			String params = event.getParameters().entrySet().stream()
					.map(entry -> entry.getKey() + "=" + entry.getValue())
					.collect(Collectors.joining(","));
			
			eventsString += new StringBuilder().append(evString).append(params).append("\n").toString();
		}
		
		return eventsString;
	}

	@Override
	public void cancel()
	{
		// TODO Auto-generated method stub
	}

	private String				ddrType;
	private String				ddrLocation;
	private String				eventData;
	private String				network;
	private String				modelicaDocument;

	private static final Logger	LOG	= LoggerFactory.getLogger(ModelicaEventAdder.class);
}
