package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkflowResult implements Serializable {


	public WorkflowResult() {
		super();
		allBusesValues = new ArrayList<BusData>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	
	public List<BusData> getAllBusesValues() {
		return allBusesValues;
	}

	public void setAllBusesValues(List<BusData> allBusesValues) {
		this.allBusesValues = allBusesValues;
	}
	
	public void getBusesVoltages() {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("V")[0])
				.collect(Collectors.toList());
	}

	public void getBusesVoltages(int id) {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("V")[id])
				.collect(Collectors.toList());
	}

	public void getBusesAngles() {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("A")[0])
				.collect(Collectors.toList());
	}

	public void getBusesAngles(int id) {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("A")[id])
				.collect(Collectors.toList());
	}

	public void getBusesActives() {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("P")[0])
				.collect(Collectors.toList());
	}

	public void getBusesActives(int id) {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("P")[id])
				.collect(Collectors.toList());
	}

	public void getBusesReactives() {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("Q")[0])
				.collect(Collectors.toList());
	}

	public void getBusesReactives(int id) {
		List<Float> valuesV = allBusesValues.stream()
				.map(bv -> bv.getData().get("Q")[id])
				.collect(Collectors.toList());
	}

	public void setDsValues(Map<String, List<DsData>> values) {
		dsValues = values;
	}

	public Map<String, List<DsData>> getDsValues() {
		return dsValues;
	}

	private String workflow;
	private String id;

	private List<BusData> allBusesValues;
	private Map<String, List<DsData>> dsValues;
}
