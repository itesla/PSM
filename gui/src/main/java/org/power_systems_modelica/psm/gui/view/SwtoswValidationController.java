package org.power_systems_modelica.psm.gui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.model.Validation;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.ProcessState;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

	@FXML
	private void initialize()
	{
		Properties p = PathUtils.getGUIProperties();
		STEPSIZE = p.getProperty("swtoswValidation.source.stepSize");
		TOLERANCE = p.getProperty("swtoswValidation.validation.tolerance");
		THRMSE = p.getProperty("swtoswValidation.validation.thrmse");
		THRD = p.getProperty("swtoswValidation.validation.thrd");
		THAD = p.getProperty("swtoswValidation.validation.thad");

		expectedFileSelector.graphicProperty().bind(
				Bindings.when(expectedFileSelector.hoverProperty())
						.then(new ImageView(whiteSelectImage))
						.otherwise(new ImageView(selectImage)));

		caseFileSelector.graphicProperty().bind(
				Bindings.when(caseFileSelector.hoverProperty())
						.then(new ImageView(whiteSelectImage))
						.otherwise(new ImageView(selectImage)));

		tolerance.textProperty().addListener((observable, oldValue, newValue) -> {
			setTextfieldColor(pctRmse, newValue);
			setTextfieldColor(pctRd, newValue);
			setTextfieldColor(pctAd, newValue);
		});

		thRmse.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && oldValue != newValue)
			{
				validationTable.refresh();
			}
		});
		thRd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && oldValue != newValue)
			{
				validationTable.refresh();
			}
		});
		thAd.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null && oldValue != newValue)
			{
				validationTable.refresh();
			}
		});

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
						setText(item.toString());
						if (Double.valueOf(item.toString()) <= Double.valueOf(thRmse.getText()))
						{
							setStyle("-fx-background-color: " + validValue);
						}
						else
						{
							setStyle("-fx-background-color: " + notValidValue);
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
						setText(item.toString());
						if (Double.valueOf(item.toString()) <= Double.valueOf(thRd.getText()))
						{
							setStyle("-fx-background-color: " + validValue);
						}
						else
						{
							setStyle("-fx-background-color: " + notValidValue);
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
						setText(item.toString());
						if (Double.valueOf(item.toString()) <= Double.valueOf(thAd.getText()))
						{
							setStyle("-fx-background-color: " + validValue);
						}
						else
						{
							setStyle("-fx-background-color: " + notValidValue);
						}
					}
				}
			};
		});
	}

	public void handleValidateAction()
	{
		String expectedPath = expectedFile.getText();
		if (expectedPath.equals(""))
		{
			Utils.showWarning("Warning", "Select an expected case");
			return;
		}
		String casePath = caseFile.getText();
		if (casePath.equals(""))
		{
			Utils.showWarning("Warning", "Select a case");
			return;
		}

		String stepSizeV = stepSize.getText();
		try
		{
			Double.valueOf(stepSizeV);
		}
		catch (NumberFormatException e)
		{
			Utils.showWarning("Warning", "Select a step size");
			return;
		}

		mainService.startSwtoswValidation(expectedPath, casePath, stepSizeV);
	}

	public void handleCleanWorkflow()
	{
		expectedFile.setText("");
		caseFile.setText("");

		stepSize.setText(STEPSIZE);
		tolerance.setText(TOLERANCE);
		pctRmse.setText("");
		pctRd.setText("");
		pctAd.setText("");
		thRmse.setText(THRMSE);
		thRd.setText(THRD);
		thAd.setText(THAD);

		validationTable.getItems().clear();
	}

	@FXML
	public void handleSelectExpectedFileAction()
	{
		String file = PathUtils.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				PathUtils.DATA_TEST.toString());

		if (file != null)
		{
			expectedFile.setText(file);
		}
	}

	@FXML
	public void handleSelectCaseFileAction()
	{
		String file = PathUtils.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				PathUtils.DATA_TEST.toString());

		if (file != null)
		{
			caseFile.setText(file);
		}
	}

	public void setWorkflow(Workflow w)
	{
		if (w.getState().equals(ProcessState.SUCCESS))
		{
			WorkflowResult result = mainService.getSwtoswValidationResult("" + w.getId());
			validationTable.setItems(result.getValidation());

			String[] symmary = result.getSummaryValidation();
			String tl = tolerance.getText();
			pctRmse.setText(symmary[0]);
			setTextfieldColor(pctRmse, tl);
			pctRd.setText(symmary[1]);
			setTextfieldColor(pctRd, tl);
			pctAd.setText(symmary[2]);
			setTextfieldColor(pctAd, tl);
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

	private void setTextfieldColor(TextField tf, String value)
	{
		if (tf.getText().equals(""))
		{
			tf.setStyle("-fx-background-color: white");

		}
		else if (Double.valueOf(value) >= Double.valueOf(tf.getText()))
		{
			tf.setStyle("-fx-background-color: " + validValue);
		}
		else
		{
			tf.setStyle("-fx-background-color: " + notValidValue);
		}
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
	private TextField						tolerance;
	@FXML
	private TextField						pctRmse;
	@FXML
	private TextField						pctRd;
	@FXML
	private TextField						pctAd;
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
