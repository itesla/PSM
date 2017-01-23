package org.power_systems_modelica.psm.gui.view;

import java.util.List;

public interface MainChildrenController {
	
	void handleMainAction();
	void handleMenuAction(String action);
	String getMainAction();
	List<String> getMenuActions();
	List<String> getSummaryLabels();
}
