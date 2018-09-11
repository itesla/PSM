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

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
public class SummaryLabel
{
	public SummaryLabel(String label, String value, boolean secondColumn, boolean multipleColumns)
	{
		super();
		this.label = label;
		this.value = value;
		this.secondColumn = secondColumn;
		this.multipleColumns = multipleColumns;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean isSecondColumn()
	{
		return secondColumn;
	}

	public void setSecondColumn(boolean secondColumn)
	{
		this.secondColumn = secondColumn;
	}

	public boolean isMultipleColumns()
	{
		return multipleColumns;
	}

	public void setMultipleColumns(boolean multipleColumns)
	{
		this.multipleColumns = multipleColumns;
	}

	private String	label;
	private String	value;
	private boolean	secondColumn;
	private boolean	multipleColumns;
}
