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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class Validation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public Validation()
	{
		this.name = new SimpleStringProperty();
		this.rmse = new SimpleStringProperty();
		this.rd = new SimpleStringProperty();
		this.ad = new SimpleStringProperty();
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

	public String getRmse()
	{
		return rmse.get();
	}

	public StringProperty rmseProperty()
	{
		return rmse;
	}

	public void setRmse(String rmse)
	{
		this.rmse.set(rmse);
	}

	public String getRd()
	{
		return rd.get();
	}

	public StringProperty rdProperty()
	{
		return rd;
	}

	public void setRd(String rd)
	{
		this.rd.set(rd);
	}

	public String getAd()
	{
		return ad.get();
	}

	public StringProperty adProperty()
	{
		return ad;
	}

	public void setAd(String ad)
	{
		this.ad.set(ad);
	}

	public boolean isSelectable()
	{
		return selectable;
	}

	public void setSelectable(boolean selectable)
	{
		this.selectable = selectable;
	}

	private StringProperty	name;
	private StringProperty	rmse;
	private StringProperty	rd;
	private StringProperty	ad;
	private boolean			selectable	= true;
}
