package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.List;

public class Annotation
{
	public Annotation(String item)
	{
		this.items.add(item);
	}

	public boolean isEmpty()
	{
		return items.size() == 0;
	}

	public void reset()
	{
		this.items = new ArrayList<>();
	}

	public void add(String item)
	{
		items.add(item);
	}

	public List<String> getItems()
	{
		return items;
	}

	private List<String> items = new ArrayList<>();
}