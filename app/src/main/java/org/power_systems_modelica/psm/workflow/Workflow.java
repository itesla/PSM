package org.power_systems_modelica.psm.workflow;

import java.util.List;

public class Workflow {

	private String name;
	private List<WorkflowResult> result;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WorkflowResult> getResult() {
		return result;
	}

	public void setResult(List<WorkflowResult> result) {
		this.result = result;
	}

}
