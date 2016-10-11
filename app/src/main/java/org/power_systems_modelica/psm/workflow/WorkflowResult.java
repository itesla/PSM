package org.power_systems_modelica.psm.workflow;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class WorkflowResult implements Serializable {

	private String workflow;
	private String id;

	private List<WorkflowResultItem> result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public List<WorkflowResultItem> getResult() {
		return result;
	}

	public void setResult(List<WorkflowResultItem> result) {
		this.result = result;
	}

}
