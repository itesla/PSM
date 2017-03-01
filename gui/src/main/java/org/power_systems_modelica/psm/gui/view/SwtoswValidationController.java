package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
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
		WorkflowResult result = WorkflowServiceConfiguration
				.getSwtoswValidationResult(THRMSE, THRD, THAD);

		validationTable.setItems(result.getValidation());
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
		STEPSIZE = Optional.ofNullable(p.getProperty("swtoswValidation.source.stepSize"))
				.orElse("0.0001");
		THRMSE = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thrmse"))
				.orElse("0.001");
		THRD = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thrd"))
				.orElse("0.05");
		THAD = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thad"))
				.orElse("0.05");

		try
		{
			Path defaultFile = PathUtils.DATA_TEST.resolve("cfg")
					.resolve("case-validation.properties");

			p = new Properties();
			InputStream is = Files.newInputStream(defaultFile);
			p.load(is);
			is.close();
			toleranceTh = Double
					.parseDouble(Optional.ofNullable(p.getProperty("toleranceTh"))
							.orElse("1.5"));
			absThreshold = Double
					.parseDouble(Optional.ofNullable(p.getProperty("absThreshold"))
							.orElse("0.001"));
			relThreshold = Double
					.parseDouble(Optional.ofNullable(p.getProperty("relThreshold"))
							.orElse("0.011"));
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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

		validationTable.setEditable(true);

		StringConverter<String> sc = new StringConverter<String>()
		{
			@Override
			public String toString(String object)
			{
				if (object != null)
				{
					Double d = Double.parseDouble(object);
					return new DecimalFormat("0.0###").format(d * 100.0) + " %";
				}
				else
					return "";
			}

			@Override
			public String fromString(String string)
			{
				Double d = Double.parseDouble(string.replaceAll("%", ""));
				return "" + d / 100.0;
			}
		};

		nameColumn.setText("");
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		nameColumn.setCellFactory(column -> {
			return new TableCell<Validation, String>()
			{
				protected void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
						setText(null);
					else
					{
						setText(item.toString());
						if (item.toString().equals(""))
						{
							setStyle("-fx-border-color: transparent");
							setStyle("-fx-table-cell-border-color: transparent");
						}
						else
							setStyle("");
					}
				}
			};
		});
		rmseColumn.setCellValueFactory(cellData -> cellData.getValue().rmseProperty());
		rmseColumn.setCellFactory(column -> {
			return new TextFieldTableCell<Validation, String>(new StringConverter<String>()
			{
				@Override
				public String toString(String object)
				{
					if (object != null)
					{
						Double d = Double.parseDouble(object);
						if (backButton.isVisible())
							return new DecimalFormat("0.0###E0").format(d);
						else
							return new DecimalFormat("0.0###").format(d * 100.0) + " %";
					}
					else
						return "";
				}

				@Override
				public String fromString(String string)
				{
					Double d = Double.parseDouble(string.replaceAll("%", ""));
					if (backButton.isVisible())
						return "" + d;
					else
						return "" + d / 100.0;
				}
			})
			{
				@Override
				public void startEdit()
				{
					Validation v = getTableView().getItems().get(getIndex());
					if (!v.getName().equals("Tolerance")) return;

					super.startEdit();
				}

				@Override
				public void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						Validation v = null;
						try
						{
							v = getTableView().getItems().get(getIndex());
						}
						catch (IndexOutOfBoundsException e)
						{
						}
						if (v != null && v.getName().equals(""))
						{
							setStyle("-fx-border-color: transparent");
							setStyle("-fx-table-cell-border-color: transparent");
						}
						else
							setStyle("");
					}
					else
					{
						Validation v = getTableView().getItems().get(getIndex());
						if (v.getName().equals("Total"))
						{
							Validation th = getTableView().getItems().get(0);
							try
							{
								if (Double.parseDouble(item.toString()) <= Double
										.parseDouble(th.getRmse()))
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
						if (backButton.isVisible())
						{
							Validation th = getTableView().getItems().get(0);
							try
							{
								if (Double.parseDouble(item.toString()) <= absThreshold)
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
				}
			};
		});
		rmseColumn.setOnEditCommit(new EventHandler<CellEditEvent<Validation, String>>()
		{

			@Override
			public void handle(CellEditEvent<Validation, String> t)
			{
				Validation v = validationTable.getSelectionModel().getSelectedItem();
				if (!v.getName().equals("Tolerance")) return;

				// update value
				((Validation) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setRmse(t.getNewValue());

				THRMSE = t.getNewValue();
				validationTable.refresh();
			}

		});
		rdColumn.setCellValueFactory(cellData -> cellData.getValue().rdProperty());
		rdColumn.setCellFactory(column -> {
			return new TextFieldTableCell<Validation, String>(sc)
			{
				@Override
				public void startEdit()
				{
					Validation v = getTableView().getItems().get(getIndex());
					if (!v.getName().equals("Tolerance")) return;

					super.startEdit();
				}

				@Override
				public void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						Validation v = null;
						try
						{
							v = getTableView().getItems().get(getIndex());
						}
						catch (IndexOutOfBoundsException e)
						{
						}
						if (v != null && v.getName().equals(""))
						{
							setStyle("-fx-border-color: transparent");
							setStyle("-fx-table-cell-border-color: transparent");
						}
						else
							setStyle("");
					}
					else
					{
						Validation v = getTableView().getItems().get(getIndex());
						if (v.getName().equals("Total"))
						{
							Validation th = getTableView().getItems().get(0);
							try
							{
								if (Double.parseDouble(item.toString()) <= Double
										.parseDouble(th.getRd()))
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
				}
			};
		});
		rdColumn.setOnEditCommit(new EventHandler<CellEditEvent<Validation, String>>()
		{

			@Override
			public void handle(CellEditEvent<Validation, String> t)
			{
				Validation v = validationTable.getSelectionModel().getSelectedItem();
				if (!v.getName().equals("Tolerance")) return;

				// update value
				((Validation) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setRd(t.getNewValue());

				THRD = t.getNewValue();
				validationTable.refresh();
			}

		});
		adColumn.setCellValueFactory(cellData -> cellData.getValue().adProperty());
		adColumn.setCellFactory(column -> {
			return new TextFieldTableCell<Validation, String>(sc)
			{
				@Override
				public void startEdit()
				{
					Validation v = getTableView().getItems().get(getIndex());
					if (!v.getName().equals("Tolerance")) return;

					super.startEdit();
				}

				@Override
				public void updateItem(String item, boolean empty)
				{
					super.updateItem(item, empty);

					if (item == null || empty)
					{
						Validation v = null;
						try
						{
							v = getTableView().getItems().get(getIndex());
						}
						catch (IndexOutOfBoundsException e)
						{
						}
						if (v != null && v.getName().equals(""))
						{
							setStyle("-fx-border-color: transparent");
							setStyle("-fx-table-cell-border-color: transparent");
						}
						else
							setStyle("");
					}
					else
					{
						Validation v = getTableView().getItems().get(getIndex());
						if (v.getName().equals("Total"))
						{
							Validation th = getTableView().getItems().get(0);
							try
							{
								if (Double.parseDouble(item.toString()) <= Double
										.parseDouble(th.getAd()))
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
				}
			};
		});
		adColumn.setOnEditCommit(new EventHandler<CellEditEvent<Validation, String>>()
		{

			@Override
			public void handle(CellEditEvent<Validation, String> t)
			{
				Validation v = validationTable.getSelectionModel().getSelectedItem();
				if (!v.getName().equals("Tolerance")) return;

				// update value
				((Validation) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setAd(t.getNewValue());

				THAD = t.getNewValue();
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
			if (!v.isSelectable()) return;

			backButton.setVisible(true);
			backButton.setDisable(false);
			DecimalFormat df = new DecimalFormat("0.0###E0");
			rmseColumn.setText("RMSE (" + df.format(absThreshold) + ")");
			rdColumn.setText("% of points > th (" + df.format(relThreshold) + ")");
			adColumn.setText("% of points > th (" + df.format(absThreshold) + ")");

			WorkflowResult result = WorkflowServiceConfiguration.getSwtoswValidationResult(
					THRMSE, THRD, THAD,
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
		rmseColumn.setText("RMSE");
		rdColumn.setText("RD");
		adColumn.setText("AD");

		WorkflowResult result = WorkflowServiceConfiguration
				.getSwtoswValidationResult(THRMSE, THRD, THAD);
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

	private String							STEPSIZE;
	private String							THRMSE;
	private String							THRD;
	private String							THAD;
	private Double							thRmseV;
	private Double							thRdV;
	private Double							thAdV;

	private double							toleranceTh;
	private double							absThreshold;
	private double							relThreshold;

	private final String					validValue			= "#AAEEA7";
	private final String					notValidValue		= "#FAA98E";
}
