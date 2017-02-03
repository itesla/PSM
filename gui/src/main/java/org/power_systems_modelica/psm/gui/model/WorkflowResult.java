package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowResult implements Serializable
{
	public WorkflowResult()
	{
		super();
		allBusesValues = FXCollections.observableArrayList();
		dsValues = new HashMap<String, List<DsData>>();
		models = FXCollections.observableArrayList();
		validation = FXCollections.observableArrayList();
		exceptions = FXCollections.observableArrayList();
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public ObservableList<BusData> getAllBusesValues()
	{
		return allBusesValues;
	}

	public void setAllBusesValues(List<BusData> allBusesValues)
	{
		this.allBusesValues.clear();
		if (allBusesValues != null)
			this.allBusesValues.addAll(allBusesValues);
	}

	public void setDsValues(Map<String, List<DsData>> values)
	{
		dsValues = values;
	}

	public Map<String, List<DsData>> getDsValues()
	{
		return dsValues;
	}

	public ObservableList<ElementModel> getModels()
	{
		return models;
	}

	public void setModels(List<ElementModel> models) 
	{
		this.models.clear();
		if (models != null)
			this.models.addAll(models);
	}
	
	public ObservableList<Validation> getValidation()
	{
		return validation;
	}

	public void setValidation(ObservableList<Validation> list)
	{
		validation.clear();
		if (list != null)
			validation.addAll(list);
	}

	public ObservableList<Exception> getExceptions()
	{
		return exceptions;
	}

	public void setExceptions(List<Exception> list)
	{
		exceptions.clear();
		if (list != null)
			exceptions.addAll(list);
	}

	/**
	 * sample function to obtain data by variable public void getBusesVoltages() { List<Float> valuesV = allBusesValues.stream() .map(bv -> bv.getData().get("V")[0]) .collect(Collectors.toList()); }
	 **/

	/**
	 * sample function to obtain data by variable/id public void getBusesVoltages(int id) { List<Float> valuesV = allBusesValues.stream() .map(bv -> bv.getData().get("V")[id]) .collect(Collectors.toList()); }
	 **/

	private String							id;

	private ObservableList<BusData>			allBusesValues;
	private Map<String, List<DsData>>		dsValues;
	private ObservableList<ElementModel>	models;
	private ObservableList<Validation> 		validation;
	private ObservableList<Exception> 		exceptions;
}
