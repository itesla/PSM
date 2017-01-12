package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class CompareLoadflowsNewController
{
	@FXML
	private void initialize()
	{
		catalogSource.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Catalog>()
				{
					@Override
					public void changed(ObservableValue<? extends Catalog> observable,
							Catalog oldValue, Catalog newValue)
					{
						caseSource.setItems(mainService.getCases(newValue.getName()));
					}
				});
	}

	@FXML
	private void handleStartWorkflow()
	{
		LOG.debug("handleStartWorkflow");
		Case cs = (Case) caseSource.getSelectionModel().getSelectedItem();
		if (cs == null)
		{
			Utils.showWarning("Warning", "Select a case");
			return;
		}
		boolean generatorsReactiveLimits = enforceGeneratorsReactiveLimits.isSelected();
		mainService.startCompareLoadflows(cs, generatorsReactiveLimits);
	}

	public void setCase(Case c)
	{
		ObservableList<Catalog> catalogs = mainService.getCatalogs("cases");

		FilteredList<Catalog> filteredCatalogs = new FilteredList<Catalog>(catalogs,
				catalog -> c.getLocation().contains(catalog.getLocation()));

		filteredCatalogs.forEach(catalog -> {
			catalogSource.getSelectionModel().select(catalog);
		});

		caseSource.getSelectionModel().select(c);
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		catalogSource.setItems(mainService.getCatalogs("cases"));
	}

	@FXML
	private ComboBox<Catalog>	catalogSource;
	@FXML
	private ComboBox<Case>		caseSource;
	@FXML
	private CheckBox			enforceGeneratorsReactiveLimits;
	private MainService			mainService;

	private static final Logger	LOG	= LoggerFactory.getLogger(CompareLoadflowsNewController.class);
}
