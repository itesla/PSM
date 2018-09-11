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
import java.util.Collection;
import java.util.Map;

import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class ConvertedCase implements Serializable
{
	private static final long serialVersionUID = 1L;

	public enum Format
	{
		CIM1(0), IIDM(1);

		private int value;

		private Format(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return value;
		}
	}

	public ConvertedCase()
	{
		this.name = new SimpleStringProperty();
		this.location = new SimpleStringProperty();
		this.ddrLocation = new SimpleStringProperty();
		this.loadflowEngine = new SimpleStringProperty();
		this.onlyMainConnectedComponent = new SimpleStringProperty();
		this.fullModelInitializationEgine = new SimpleStringProperty();

		// ddr = DynamicDataRepositoryMainFactory.create("DYD", newValue.getDdrLocation());
	}

	public String getName()
	{
		return name.get();
	}

	public StringProperty nameProperty()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public String getLocation()
	{
		return location.get();
	}

	public StringProperty locationProperty()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location.set(location);
	}

	public String getDdrLocation()
	{
		return ddrLocation.get();
	}

	public StringProperty ddrLocationProperty()
	{
		return ddrLocation;
	}

	public void setDdrLocation(String location)
	{
		this.ddrLocation.set(location);
		//With the DDR location the DynamicDataRepository is created and the converted case is connected to
		ddr = DynamicDataRepositoryMainFactory.create("DYD", this.ddrLocation.get());
		try
		{
			ddr.connect();
		}
		catch (ConnectionException e)
		{
			LOG.error(e.getMessage());
		}
	}

	public String getLoadflowEngine()
	{
		return loadflowEngine.get();
	}

	public StringProperty loadflowEngineProperty()
	{
		return loadflowEngine;
	}

	public void setLoadflowEngine(String engine)
	{
		this.loadflowEngine.set(engine);
	}

	public String getOnlyMainConnectedComponent()
	{
		return onlyMainConnectedComponent.get();
	}

	public StringProperty onlyMainConnectedComponentProperty()
	{
		return onlyMainConnectedComponent;
	}

	public void setOnlyMainConnectedComponent(String maincomponent)
	{
		this.onlyMainConnectedComponent.set(maincomponent);
	}

	public String getFullModelInitializationEgine()
	{
		return fullModelInitializationEgine.get();
	}

	public StringProperty fullModelInitializationEgineProperty()
	{
		return fullModelInitializationEgine;
	}

	public void setFullModelInitializationEgine(String engine)
	{
		this.fullModelInitializationEgine.set(engine);
	}

	public String toString()
	{
		return name.get();
	}

	public void setElementIdentifiersByEventType(Map<String, Collection<String>> elems)
	{
		this.elementIdentifiersByEventType = elems;
	}

	public Map<String, Collection<String>> getElementIdsByEventType()
	{
		return this.elementIdentifiersByEventType;
	}

	public DynamicDataRepository getDdr()
	{
		return ddr;
	}

	public void setDdr(DynamicDataRepository ddr)
	{
		this.ddr = ddr;
	}

	private StringProperty					name;
	private StringProperty					location;
	private StringProperty					ddrLocation;
	private StringProperty					loadflowEngine;
	private StringProperty					onlyMainConnectedComponent;
	private StringProperty					fullModelInitializationEgine;

	private Map<String, Collection<String>>	elementIdentifiersByEventType;
	private DynamicDataRepository			ddr	= null;

	private static final Logger				LOG	= LoggerFactory
			.getLogger(ConvertedCase.class);
}
