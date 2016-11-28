package org.power_systems_modelica.psm.gui.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.DsEngine;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration.LoadflowEngine;

public class WorkflowParameters implements Serializable {
	
	public WorkflowParameters(String created) {
    	this.created = created;
    }
    
    public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Catalog getCatalog() {
		return ctlg;
	}

	public void setCatalog(Catalog ctlg) {
		this.ctlg = ctlg;
	}

	public Case getCase() {
		return cs;
	}

	public void setCase(Case cs) {
		this.cs = cs;
	}

	public Ddr getDdr() {
		return ddr;
	}

	public void setDdr(Ddr ddr) {
		this.ddr = ddr;
	}

	public LoadflowEngine getEngineLoadflow() {
		return engineLoadflow;
	}

	public void setEngineLoadflow(LoadflowEngine engineLoadflow) {
		this.engineLoadflow = engineLoadflow;
	}

	public boolean isMainConnectedComponentOnly() {
		return mainConnectedComponentOnly;
	}

	public void setMainConnectedComponentOnly(boolean mainConnectedComponentOnly) {
		this.mainConnectedComponentOnly = mainConnectedComponentOnly;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public DsEngine getEngineDS() {
		return engineDS;
	}

	public void setEngineDS(DsEngine engineDS) {
		this.engineDS = engineDS;
	}
	
	public boolean isEnforceGeneratorsReactiveLimits() {
		return enforceGeneratorsReactiveLimits;
	}

	public void enforceGeneratorsReactiveLimits(boolean generatorsReactiveLimits) {
		this.enforceGeneratorsReactiveLimits = generatorsReactiveLimits;
	}

	@Override
	public String toString() {
		
		String txt = "Created: " + created;
		txt += ", ";
		txt += "Catalog: " + ctlg.getName();
		txt += ", ";
		txt += "Case: " + cs.getName();
		txt += ", ";
		txt += "DDR: " + ddr.getName();
		txt += ", ";
		txt += "Loadflow Engine: " + engineLoadflow.name();
		txt += ", ";
		txt += "Only main connected component: " + (mainConnectedComponentOnly?"YES":"NO");
		txt += ", ";
		txt += "Dynamic Simulation Engine: " + engineDS.name();
		txt += ", ";
		txt += "Events: " + events.toString();
		
		return txt;
	}
	
	private String created;
	
	//Description
	private Catalog ctlg;
	private Case cs;
	private Ddr ddr;
	
	//Loadflow
	private LoadflowEngine engineLoadflow;
	
	//Modelica network
	private boolean mainConnectedComponentOnly;
	
	//Events
	private List<Event> events = new CopyOnWriteArrayList<>();
	
	//Dynamic simulation
	private DsEngine engineDS;

	//Loadflows parameters
    private boolean enforceGeneratorsReactiveLimits;
}
