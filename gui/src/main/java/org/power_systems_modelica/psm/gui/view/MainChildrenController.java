package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;

public interface MainChildrenController
{

	void handleMainAction();

	void handleMenuAction(String action);

	String getMainAction();

	List<String> getMenuActions();

	List<SummaryLabel> getSummaryLabels();
}
