package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Validation implements Serializable
{

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
