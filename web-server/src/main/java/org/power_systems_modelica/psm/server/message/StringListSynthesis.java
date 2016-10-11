package org.power_systems_modelica.psm.server.message;

import java.io.Serializable;
import java.util.List;

public class StringListSynthesis implements Serializable
{
	public StringListSynthesis(List<String> list)
	{
		this.list = list;
	}

	public List<String> getList()
	{
		return list;
	}

	public void setList(List<String> list)
	{
		this.list = list;
	}

	private List<String> list;
}
