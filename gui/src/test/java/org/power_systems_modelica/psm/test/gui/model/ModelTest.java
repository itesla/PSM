package org.power_systems_modelica.psm.test.gui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.Event;
import org.power_systems_modelica.psm.gui.model.EventParamGui;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.Validation;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;

public class ModelTest
{

	@Test
	public void workflowResult()
	{

		WorkflowResult wr = new WorkflowResult();
		wr.setId("id");

		List<BusData> buses = new ArrayList<>();
		for (int i = 1; i < 10; i++)
		{
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[2];
			float[] As = new float[2];
			float[] Ps = new float[2];
			float[] Qs = new float[2];

			Vs[0] = 0.9f;
			As[0] = 10.0f;
			Ps[0] = 30.5f;
			Qs[0] = -35.6f;
			Vs[1] = 1.19f;
			As[1] = 12.0f;
			Ps[1] = 35.5f;
			Qs[1] = 25.6f;
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);

			BusData bus = new BusData("id" + i, "name" + i, bvalues);
			float err = (bus.getData("V", 0) - bus.getData("V", 1))
					/ (bus.getData("V", 0) != 0.0f ? bus.getData("V", 0) : 1.0f);
			bus.setError("V", err);
			err = (bus.getData("A", 0) - bus.getData("A", 1))
					/ (bus.getData("A", 0) != 0.0f ? bus.getData("A", 0) : 1.0f);
			bus.setError("A", err);
			err = (bus.getData("P", 0) - bus.getData("P", 1))
					/ (bus.getData("P", 0) != 0.0f ? bus.getData("P", 0) : 1.0f);
			bus.setError("P", err);
			err = (bus.getData("Q", 0) - bus.getData("Q", 1))
					/ (bus.getData("Q", 0) != 0.0f ? bus.getData("Q", 0) : 1.0f);
			bus.setError("Q", err);

			buses.add(bus);
		}
		wr.setAllBusesValues(buses);

		Map<String, List<DsData>> values = new HashMap<>();
		List<DsData> dsData = new ArrayList<>();
		for (int i = 1; i < 10; i++)
		{
			DsData data = new DsData(i / 10.0, 1.0 - (i / 10.0));
			dsData.add(data);
		}
		values.put("dsData", dsData);
		wr.setDsValues(values);

		Validation v = new Validation();
		v.setName("validation1");
		v.setRmse("0.01");
		v.setRd("0.01");
		v.setAd("0.01");
		List<Validation> vs = new ArrayList<>();
		vs.add(v);
		wr.setValidation(vs);

		List<Exception> excs = new ArrayList<>();
		excs.add(new Exception("exception1"));
		wr.setExceptions(excs);

		assertEquals("id", wr.getId());
		assertEquals(9, wr.getAllBusesValues().size());
		BusData bus = wr.getAllBusesValues().get(0);
		assertEquals("id1", bus.getId());
		assertEquals("name1", bus.getName());
		assertNotNull(bus.nameProperty());
		assertNotNull(bus.getData());
		assertEquals(1.19f, bus.getData("V", 1), 0.0f);
		assertNotNull(bus.dataProperty("V", 1));
		assertEquals(-0.32f, bus.getError("V"), 0.01f);
		assertNotNull(bus.errorProperty("V"));
		assertEquals(0.32f, bus.getAbsError("V"), 0.01f);

		assertEquals(1, wr.getDsValues().size());
		DsData data = wr.getDsValues().get("dsData").get(1);
		assertEquals(0.2, data.getX(), 0.0);
		assertEquals(0.8, data.getY(), 0.0);

		assertEquals(0, wr.getModels().size());

		assertEquals(1, wr.getValidation().size());
		v = wr.getValidation().get(0);
		assertEquals("validation1", v.getName());
		assertEquals("0.01", v.getRmse());
		assertEquals("0.01", v.getRd());
		assertEquals("0.01", v.getAd());

		assertEquals(1, wr.getExceptions().size());
		Exception e = wr.getExceptions().get(0);
		assertEquals(new Exception("exception1").getMessage(), e.getMessage());
	}

	@Test
	public void summaryLabel()
	{
		SummaryLabel sl = new SummaryLabel("Label1", "value1", false, false);
		assertEquals("Label1", sl.getLabel());
		assertEquals("value1", sl.getValue());
		assertFalse(sl.isSecondColumn());
		assertFalse(sl.isMultipleColumns());
	}

	@Test
	public void event()
	{
		Event e1 = new Event();
		e1.setAction("BusFault");
		e1.setElement("Bus1");
		
		List<EventParamGui> params = new ArrayList<>();
		EventParamGui p = new EventParamGui();
		p.setName("startTime (s)");
		p.setUnit("s");
		p.setValue("1.0");
		params.add(p);
		e1.setParams(params);
		
		String ev = e1.toString();
		
		Event e2 = new Event();
		e2.fromString(ev);
		assertEquals("BusFault", e2.getAction());
		assertEquals("Bus1", e2.getElement());
		assertEquals(1, e2.getParams().size());
		EventParamGui p2 = e2.getParam("startTime"); 
		assertEquals("startTime", p2.getName());
		assertEquals("1.0", p2.getValue());
	}
}
