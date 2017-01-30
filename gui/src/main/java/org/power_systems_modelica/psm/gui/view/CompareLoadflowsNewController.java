package org.power_systems_modelica.psm.gui.view;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class CompareLoadflowsNewController implements MainChildrenController
{
	@Override
	public void handleMainAction()
	{

		handleStartWorkflow();
	}

	@Override
	public void handleMenuAction(String action)
	{

	}

	@Override
	public String getMainAction()
	{

		return "Compare";
	}

	@Override
	public List<String> getMenuActions()
	{

		return null;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{

		return null;
	}

	@FXML
	private void initialize()
	{
		Properties p = PathUtils.getGUIProperties();
		EGRL = p.getProperty("compareLoadflows.loadflow.enforceGeneratorsReactiveLimits");
		UHRAIS = p.getProperty("compareLoadflows.HELMflow.useHadesResultsAsInputState");

		enforceGeneratorsReactiveLimits.setSelected(Boolean.parseBoolean(EGRL));
		helmflowFromHadesResults.setSelected(Boolean.parseBoolean(UHRAIS));
		
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
		boolean helmflowFromHadesResultsValue = helmflowFromHadesResults.isSelected();
		mainService.startCompareLoadflows(cs, generatorsReactiveLimits,
				helmflowFromHadesResultsValue);
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

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		catalogSource.setItems(mainService.getCatalogs("cases"));
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
	}

	@Override
	public void setDefaultInit()
	{
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
	}

	@FXML
	private ComboBox<Catalog>	catalogSource;
	@FXML
	private ComboBox<Case>		caseSource;
	@FXML
	private CheckBox			enforceGeneratorsReactiveLimits;
	@FXML
	private CheckBox			helmflowFromHadesResults;

	private MainService			mainService;

	private String				EGRL	= "true";
	private String				UHRAIS	= "true";

	private static final Logger	LOG		= LoggerFactory
			.getLogger(CompareLoadflowsNewController.class);

}
