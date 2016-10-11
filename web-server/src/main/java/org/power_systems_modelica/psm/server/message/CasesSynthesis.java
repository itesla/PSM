package org.power_systems_modelica.psm.server.message;

import java.io.Serializable;
import java.util.List;

import org.power_systems_modelica.psm.dao.Case;

public class CasesSynthesis implements Serializable
{
	public CasesSynthesis(List<Case> cases)
	{
		this.cases = cases;
	}

	public List<Case> getCatalogs()
	{
		return cases;
	}

	public void setCatalogs(List<Case> cases)
	{
		this.cases = cases;
	}

	private List<Case> cases;
}
