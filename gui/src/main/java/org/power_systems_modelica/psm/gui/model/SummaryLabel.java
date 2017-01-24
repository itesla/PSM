package org.power_systems_modelica.psm.gui.model;

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
