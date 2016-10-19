package org.power_systems_modelica.psm.gui.service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.WorkflowParameters;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.model.WorkflowResultItem;
import org.power_systems_modelica.psm.gui.service.Workflow.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkflowService {
	
	public enum LoadflowEngine {
		HADES(0), HELMFLOW(1);
		
		private int value;
		
		private LoadflowEngine(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}

	public enum DsEngine {
		DYMOLA(0), OPENMODELICA(1);
		
		private int value;
		
		private DsEngine(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}

	public static Workflow getWorkflow() {
		
		return w;
	}

	public static ObservableList<LoadflowEngine> getLoadflowEngines() {
		
		ObservableList<LoadflowEngine> engines = FXCollections.observableArrayList();
		engines.add(LoadflowEngine.HADES);
		engines.add(LoadflowEngine.HELMFLOW);
		
		return engines;
	}

	public static ObservableList<DsEngine> getDsEngines() {
		
		ObservableList<DsEngine> engines = FXCollections.observableArrayList();
		engines.add(DsEngine.DYMOLA);
		engines.add(DsEngine.OPENMODELICA);
		
		return engines;
	}

	public static Workflow startWorkflow(Catalog ctlg, Case cs, Ddr ddr, LoadflowEngine le,
			boolean onlyMainConnectedComponent, ObservableList events, DsEngine dse) {
		
		WorkflowParameters wp = new WorkflowParameters(DateTime.now().toString());
		wp.setCatalog(ctlg);
		wp.setCase(cs);
		wp.setDdr(ddr);
		wp.setEngineLoadflow(le);
		wp.setMainConnectedComponentOnly(onlyMainConnectedComponent);
		wp.setEvents(events);
		wp.setEngineDS(dse);

		w.setName(wp.getCreated());
		w.setParameters(wp);
		
		double n = rnd.nextDouble();
		LOG.debug("Random " + n);
		int status = (int) Math.round(n * 4.0);
		LOG.debug("status " + status);
		w.setStatus(Status.get(status));
		
		double p = rnd.nextDouble();
		w.setProgress(p);
		
		return w;
	}
	
	public static ObservableList<WorkflowResult> getWorkflowResult(String id) {
		
		ObservableList<WorkflowResult> results = FXCollections.observableArrayList();
		WorkflowResult r;
		int i = 0;
		while(i < 25) {
			r = new WorkflowResult();
			r.setId("" + i);
			r.setWorkflow(id);
			
			int j = 0;
			List<WorkflowResultItem> items = new CopyOnWriteArrayList<>();
			WorkflowResultItem it;
			while(j < 25) {
				it = new WorkflowResultItem();
				it.setX(j);
				it.setY(((j + rnd.nextDouble()) + ((j + rnd.nextDouble()) * (j + rnd.nextDouble())))/600.0);
				items.add(it);
				j++;
			}
			r.setResult(items);
			results.add(r);
			i++;
		}
		
		return results;
	}

	private static Random rnd = new Random();
	private static final Workflow w = new Workflow(); 
		
	private static final Logger LOG = LoggerFactory.getLogger(WorkflowService.class);
}
