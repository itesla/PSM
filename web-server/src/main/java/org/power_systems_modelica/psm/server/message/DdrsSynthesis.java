package org.power_systems_modelica.psm.server.message;

import java.io.Serializable;
import java.util.List;

import org.power_systems_modelica.psm.dao.Ddr;

public class DdrsSynthesis implements Serializable
{
	private List<Ddr> ddrs;

	public DdrsSynthesis(List<Ddr> ddrs)
	{
		this.ddrs = ddrs;
	}

	public List<Ddr> getDdrs()
	{
		return ddrs;
	}

	public void setDdrs(List<Ddr> ddrs)
	{
		this.ddrs = ddrs;
	}
}
