package org.power_systems_modelica.psm.server;

public class WorkflowInfo
{
	public static int	STATUS_UNDEFINED	= -1;
	public static int	STATUS_IDLE			= 0;
	public static int	STATUS_RUNNING		= 1;
	public static int	STATUS_STOPPED		= 2;
	public static int	STATUS_TERMINATED	= 3;

	public WorkflowInfo(String id)
	{
		worflowId = id;
		status = STATUS_IDLE;
	}

	public String getWorflowId()
	{
		return worflowId;
	}

	public void setWorflowId(String worflowId)
	{
		this.worflowId = worflowId;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	private String	worflowId;
	private int		status	= STATUS_UNDEFINED;
}
