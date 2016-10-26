package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class CompareLoadflowsNewController {

	@FXML
	private void initialize() {

		catalogSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Catalog>() {

			@Override
			public void changed(ObservableValue<? extends Catalog> observable, Catalog oldValue, Catalog newValue) {
				caseSource.setItems(mainApp.getCases(newValue.getName()));
				ddrSource.setItems(mainApp.getDdrs(newValue.getName()));
			}

		});
	}

	@FXML
	private void handleStartWorkflow() {
		LOG.debug("handleStartWorkflow");

		Catalog ctlg = (Catalog) catalogSource.getSelectionModel().getSelectedItem();
		Case cs = (Case) caseSource.getSelectionModel().getSelectedItem();
		Ddr ddr = (Ddr) ddrSource.getSelectionModel().getSelectedItem();

		boolean generatorsReactiveLimits = enforceGeneratorsReactiveLimits.isSelected();

		mainApp.startCompareLoadflows(ctlg, cs, ddr, generatorsReactiveLimits);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogSource.setItems(mainApp.getCatalogs());

	}

	@FXML
	private ComboBox catalogSource;
	@FXML
	private ComboBox caseSource;
	@FXML
	private ComboBox ddrSource;

	@FXML
	private CheckBox enforceGeneratorsReactiveLimits;

	private MainApp mainApp;

	private static final Logger LOG = LoggerFactory.getLogger(CompareLoadflowsNewController.class);
}
