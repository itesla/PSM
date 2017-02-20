package org.power_systems_modelica.psm.gui.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.Validation;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

public class SwtoswValidationController implements MainChildrenController
{

	@Override
	public void handleMainAction()
	{
		handleValidateAction();
	}

	@Override
	public void handleMenuAction(String action)
	{
		switch (action)
		{
		case "Clean":
			handleCleanWorkflow();
			break;
		}

	}

	@Override
	public String getMainAction()
	{
		return "Validate";
	}

	@Override
	public List<String> getMenuActions()
	{
		List<String> actions = new ArrayList();
		actions.add("Clean");
		return actions;
	}

	@Override
	public List<SummaryLabel> getSummaryLabels()
	{
		return null;
	}

	@Override
	public ObservableValue<? extends Boolean> disableBackground()
	{
		return new SimpleBooleanProperty(false);
	}

	@Override
	public Button getDefaultEnterButton()
	{
		return null;
	}

	public void handleValidateAction()
	{
		String mappingPath = mappingFile.getText();
		if (mappingPath.equals(""))
		{
			UtilsFX.showWarning("Warning", "Select a name mapping");
			return;
		}
		String expectedPath = expectedFile.getText();
		if (expectedPath.equals(""))
		{
			UtilsFX.showWarning("Warning", "Select an expected case");
			return;
		}
		String casePath = caseFile.getText();
		if (casePath.equals(""))
		{
			UtilsFX.showWarning("Warning", "Select a case");
			return;
		}

		String stepSizeV = stepSize.getText();
		try
		{
			Double.valueOf(stepSizeV);
		}
		catch (NumberFormatException e)
		{
			UtilsFX.showWarning("Warning", "Select a step size");
			return;
		}

		startSwtoswValidation(mappingPath, expectedPath, casePath, stepSizeV);
		mainService.getPrimaryStage().getScene().setCursor(Cursor.WAIT);
	}

	public void handleCleanWorkflow()
	{
		mappingFile.setText("");
		expectedFile.setText("");
		caseFile.setText("");

		stepSize.setText(STEPSIZE);
		thRmse.setText(Double.parseDouble(THRMSE) * 100.0 + " %");
		thRd.setText(Double.parseDouble(THRD) * 100.0 + " %");
		thAd.setText(Double.parseDouble(THAD) * 100.0 + " %");

		validationTable.getItems().clear();
	}

	@FXML
	public void handleSelectMappingFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				PathUtils.DATA_TEST.toString());

		if (file != null)
		{
			mappingFile.setText(file);
		}
	}

	@FXML
	public void handleSelectExpectedFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				PathUtils.DATA_TEST.toString());

		if (file != null)
		{
			expectedFile.setText(file);
		}
	}

	@FXML
	public void handleSelectCaseFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				PathUtils.DATA_TEST.toString());

		if (file != null)
		{
			caseFile.setText(file);
		}
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		this.w = w;
		mainService.getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);

		if (w.getState().equals(ProcessState.SUCCESS))
		{
			WorkflowResult result = WorkflowServiceConfiguration
					.getSwtoswValidationResult("" + w.getId());
			validationTable.setItems(result.getValidation());
		}
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
		handleCleanWorkflow();
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
	}

	@Override
	public void setDefaultInit()
	{
	}

	private void startSwtoswValidation(String mappingPath, String expectedPath, String casePath,
			String stepSize)
	{
		try
		{
			Workflow w = WorkflowServiceConfiguration.createSwtoswValidation(mappingPath,
					expectedPath, casePath,
					stepSize);
			Task<?> task = TaskService.createTask(w,
					() -> mainService.getMainApp().showSwtoswValidationResults(w));
			mainService.setSwtoswValidationTask(task);
			TaskService.startTask(task);
		}
		catch (WorkflowCreationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void initialize()
	{
		backButton.setVisible(false);
		backButton.setDisable(true);

		Properties p = PathUtils.getGUIProperties();
		STEPSIZE = p.getProperty("swtoswValidation.source.stepSize");
		THRMSE = p.getProperty("swtoswValidation.validation.thrmse");
		THRD = p.getProperty("swtoswValidation.validation.thrd");
		THAD = p.getProperty("swtoswValidation.validation.thad");

		mappingFileSelector.graphicProperty().bind(
				Bindings.when(expectedFileSelector.hoverProperty())
						.then(new ImageView(whiteSelectImage))
						.otherwise(new ImageView(selectImage)));

		expectedFileSelector.graphicProperty().bind(
				Bindings.when(expectedFileSelector.hoverProperty())
						.then(new ImageView(whiteSelectImage))
						.otherwise(new ImageView(selectImage)));

		caseFileSelector.graphicProperty().bind(
				Bindings.when(caseFileSelector.hoverProperty())
						.then(new ImageView(whiteSelectImage))
						.otherwise(new ImageView(selectImage)));

		initializeTextField(thRmse);
		initializeTextField(thRd);
		initializeTextField(thAd);

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		rmseColumn.setCellValueFactory(cellData -> cellData.getValue().rmseProperty());
		rmseColumn.setCellFactory(column -> {
			return new TableCell<Validation, String>()
			{
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						double pct = Double.parseDouble(item.toString()) * 100.0;
						setText(new DecimalFormat("0.0###").format(pct) + " %");
						try
						{
							if (pct <= Double.parseDouble(thRmse.getText().replaceAll("%", "")))
							{
								setStyle("-fx-background-color: " + validValue);
							}
							else
							{
								setStyle("-fx-background-color: " + notValidValue);
							}
						}
						catch (NumberFormatException e)
						{
							setStyle("");
						}
					}
				}
			};
		});
		rdColumn.setCellValueFactory(cellData -> cellData.getValue().rdProperty());
		rdColumn.setCellFactory(column -> {
			return new TableCell<Validation, String>()
			{
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						double pct = Double.parseDouble(item.toString()) * 100.0;
						setText(new DecimalFormat("0.0###").format(pct) + " %");
						try
						{
							if (pct <= Double.parseDouble(thRd.getText().replaceAll("%", "")))
							{
								setStyle("-fx-background-color: " + validValue);
							}
							else
							{
								setStyle("-fx-background-color: " + notValidValue);
							}
						}
						catch (NumberFormatException e)
						{
							setStyle("");
						}
					}
				}
			};
		});
		adColumn.setCellValueFactory(cellData -> cellData.getValue().adProperty());
		adColumn.setCellFactory(column -> {
			return new TableCell<Validation, String>()
			{
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						setText(null);
						setStyle("");
					}
					else
					{
						double pct = Double.parseDouble(item.toString()) * 100.0;
						setText(new DecimalFormat("0.0###").format(pct) + " %");
						try
						{
							if (pct <= Double.parseDouble(thAd.getText().replaceAll("%", "")))
							{
								setStyle("-fx-background-color: " + validValue);
							}
							else
							{
								setStyle("-fx-background-color: " + notValidValue);
							}
						}
						catch (NumberFormatException e)
						{
							setStyle("");
						}
					}
				}
			};
		});
	}

	private void initializeTextField(TextField tf)
	{
		StringConverter<Double> sc = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                if (object != null)
                    return Double.toString(object.doubleValue() * 100.0) + " %";
                else
                    return "";
            }

            @Override
            public Double fromString(String string) {
                Double d = Double.parseDouble(string.replaceAll("%", ""))/100.0;
                return d;
            }
        };

		tf.setTextFormatter(new TextFormatter<>(sc));
		tf.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && oldValue != newValue)
			{
				validationTable.refresh();
			}
		});
	}

	@FXML
	private void handleSelectValidation(MouseEvent event)
	{

		if (event.getClickCount() == 2 && backButton.isDisabled())
		{
			Validation v = validationTable.getSelectionModel().getSelectedItem();

			backButton.setVisible(true);
			backButton.setDisable(false);

			WorkflowResult result = WorkflowServiceConfiguration.getSwtoswValidationResult(
					"" + w.getId(),
					v.getName());
			validationTable.getItems().clear();
			validationTable.setItems(result.getValidation());
		}
	}

	@FXML
	private void handleBackAction()
	{
		backButton.setVisible(false);
		backButton.setDisable(true);

		WorkflowResult result = WorkflowServiceConfiguration
				.getSwtoswValidationResult("" + w.getId());
		validationTable.getItems().clear();
		validationTable.setItems(result.getValidation());
	}

	@FXML
	private TextField						mappingFile;
	@FXML
	private Button							mappingFileSelector;
	@FXML
	private TextField						expectedFile;
	@FXML
	private Button							expectedFileSelector;
	@FXML
	private TextField						caseFile;
	@FXML
	private Button							caseFileSelector;
	@FXML
	private TextField						stepSize;

	@FXML
	private Button							backButton;
	@FXML
	private TextField						thRmse;
	@FXML
	private TextField						thRd;
	@FXML
	private TextField						thAd;

	@FXML
	private TableView<Validation>			validationTable;
	@FXML
	private TableColumn<Validation, String>	nameColumn;
	@FXML
	private TableColumn<Validation, String>	rmseColumn;
	@FXML
	private TableColumn<Validation, String>	rdColumn;
	@FXML
	private TableColumn<Validation, String>	adColumn;

	private Image							selectImage			= new Image(
			getClass().getResourceAsStream("/img/select.png"));
	private Image							whiteSelectImage	= new Image(
			getClass().getResourceAsStream("/img/white-select.png"));

	private Workflow						w;
	private GuiFileChooser					fileChooser;
	private MainService						mainService;

	private String							STEPSIZE			= "0.0001";
	private String							TOLERANCE			= "0.05";
	private String							THRMSE				= "0.001";
	private String							THRD				= "0.05";
	private String							THAD				= "0.05";

	private final String					validValue			= "#AAEEA7";
	private final String					notValidValue		= "#FAA98E";
}
