package org.power_systems_modelica.psm.ddr.dyd;

public class Connection
{
	public Connection(String id1, String var1, String id2, String var2)
	{
		this.id1 = id1;
		this.var1 = var1;
		this.id2 = id2;
		this.var2 = var2;
	}

	public String getId1()
	{
		return id1;
	}

	public String getVar1()
	{
		return var1;
	}

	public String getId2()
	{
		return id2;
	}

	public String getVar2()
	{
		return var2;
	}

	private final String	id1;
	private final String	var1;
	private final String	id2;
	private final String	var2;
}
