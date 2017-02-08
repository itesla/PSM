package org.power_systems_modelica.psm.gui.view;

import java.io.Serializable;
import java.util.List;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

public interface MainChildrenController
{

	void handleMainAction();

	void handleMenuAction(String action);

	String getMainAction();

	List<String> getMenuActions();

	List<SummaryLabel> getSummaryLabels();
	
	void setMainService(MainService mainService);

	void setFileChooser(GuiFileChooser fileChooser);

	void setDefaultInit();
	
	void setWorkflow(Workflow w, Object...objects);

	ObservableValue<? extends Boolean> disableBackground();
}
