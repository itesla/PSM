package org.power_systems_modelica.psm.workflow;

import java.io.Serializable;

public class StatusSynthesis implements Serializable {

	public static int STATUS_UNDEFINED=-1;
	public static int STATUS_IDLE=0;
	public static int STATUS_RUNNING=1;
	public static int STATUS_STOPPED=2;
	public static int STATUS_TERMINATED=3;
	
	private String workflowId;
	private int status;

	public StatusSynthesis(String workflowId, int status)
	{
		this.workflowId=workflowId;
		this.status=status;
	}
	
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
