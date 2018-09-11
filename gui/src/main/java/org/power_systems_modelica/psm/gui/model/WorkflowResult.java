package org.power_systems_modelica.psm.gui.model;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.power_systems_modelica.psm.workflow.psm.ModelicaNetworkBuilderTask.ElementModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class WorkflowResult implements Serializable
{
	private static final long serialVersionUID = 1L;
	
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

	public void setValidation(List<Validation> list)
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
	
	public double getCalculatedStepSize()
	{
		return calculatedStepSize;
	}

	public void setCalculatedStepSize(double calculatedStepSize)
	{
		this.calculatedStepSize = calculatedStepSize;
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
	private double							calculatedStepSize = 0.0;
	private ObservableList<Exception> 		exceptions;
}
