package org.power_systems_modelica.psm.gui.view;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.model.Validation;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.WorkflowServiceConfiguration;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.workflow.TaskDefinition;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;
import org.power_systems_modelica.psm.workflow.psm.CaseValidationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.charts.Legend;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class SwtoswValidationController extends MainChildrenController
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
		List<String> actions = new ArrayList<String>();
		actions.add("Clean");
		return actions;
	}

	public void handleValidateAction()
	{
		if (backButton.isVisible())
		{
			handleBackAction();
			if (backButton.isVisible())
				handleBackAction();
		}

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
		if (backButton.isVisible())
		{
			handleBackAction();
			if (backButton.isVisible())
				handleBackAction();
		}

		mappingFile.setText("");
		expectedFile.setText("");
		caseFile.setText("");

		stepSize.setText(STEPSIZE);

		validationTable.getItems().clear();

		WorkflowServiceConfiguration.resetSwtoswValidation();

		setWorkflow(null);
	}

	@FXML
	public void handleSelectMappingFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				lastInputPath);

		if (file != null)
		{
			mappingFile.setText(file);
			lastInputPath = Paths.get(file).getParent().toString();
		}
	}

	@FXML
	public void handleSelectExpectedFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				lastInputPath);

		if (file != null)
		{
			expectedFile.setText(file);
			lastInputPath = Paths.get(file).getParent().toString();
		}
	}

	@FXML
	public void handleSelectCaseFileAction()
	{
		String file = PathUtilsFX.selectCsvFile(fileChooser, mainService.getPrimaryStage(),
				lastInputPath);

		if (file != null)
		{
			caseFile.setText(file);
			lastInputPath = Paths.get(file).getParent().toString();
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void handleShowRmse()
	{
		if (rmseCheckbox.isSelected())
		{
			adChart.getData().add(rmseValues);
			Node line = rmseValues.getNode().lookup(".chart-series-line");
			line.setStyle("-fx-stroke: green;-fx-background-color: green;-fx-stroke-dash-array: 0 12 2 0;");
		}
		else
			adChart.getData().removeAll(rmseValues);
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void handleShowLimit()
	{
		if (limitCheckbox.isSelected())
		{
			adChart.getData().add(thAdValues);
			Node line = thAdValues.getNode().lookup(".chart-series-line");
			line.setStyle("-fx-stroke: blue;-fx-background-color: blue;-fx-stroke-dash-array: 2 12 12 2;");
			rdChart.getData().add(thRdValues);
		}
		else
		{
			adChart.getData().removeAll(thAdValues);
			rdChart.getData().removeAll(thRdValues);
		}
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
		if (w != null)
		{
			for (TaskDefinition td : w.getConfiguration().getTaskDefinitions())
			{

				if (td.getTaskClass().equals(CaseValidationTask.class))
				{
					String namesPath = td.getTaskConfiguration().getParameter("pathNamesMapping");
					refPath = td.getTaskConfiguration().getParameter("pathRefData");
					modPath = td.getTaskConfiguration().getParameter("pathModelicaData");
					step = td.getTaskConfiguration().getParameter("stepSize");

					mappingFile.setText(namesPath);
					expectedFile.setText(refPath);
					caseFile.setText(modPath);
					stepSize.setText(step);
				}
			}
		}

		mainService.getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
		WorkflowResult result = WorkflowServiceConfiguration
				.getSwtoswValidationResult(THRMSE, THRD, THAD);

		if (result.getCalculatedStepSize() != 0.0)
		{
			String userStepSize = stepSize.getText();
			String calcStepSize = new DecimalFormat("0.0###", otherSymbols)
					.format(result.getCalculatedStepSize());
			UtilsFX.showWarning("Warning", "The selected step size (" + userStepSize
					+ ") is smaller than the reference data step size (" + calcStepSize
					+ ").\nResults has been calculated using " + calcStepSize);
			stepSize.setText(calcStepSize);
		}

		validationTable.setItems(result.getValidation());
	}

	@Override
	public void setMainService(MainService mainService)
	{
		super.setMainService(mainService);

		handleCleanWorkflow();
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
		this.fileChooser = fileChooser;
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
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@FXML
	private void initialize()
	{
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(',');

		backButton.setVisible(false);
		backButton.setDisable(true);

		limitCheckbox.setVisible(false);
		limitCheckbox.setDisable(true);

		adChart.setCreateSymbols(false);
		yADAxis.setForceZeroInRange(false);
		yADAxis.setAutoRanging(true);

		adChart.setVisible(false);
		adChart.setDisable(true);
		for (Node n : adChart.getChildrenUnmodifiable())
		{
			if (n instanceof Legend)
			{
				final Legend legend = (Legend) n;

				// remove the legend
				legend.getChildrenUnmodifiable().addListener(new ListChangeListener<Object>()
				{
					@Override
					public void onChanged(Change<?> arg0)
					{
						for (Node node : legend.getChildrenUnmodifiable())
						{
							if (node instanceof Label)
							{
								final Label label = (Label) node;
								label.getChildrenUnmodifiable()
										.addListener(new ListChangeListener<Object>()
										{
											@Override
											public void onChanged(Change<?> arg0)
											{
												Node n = label.getGraphic();
												if (label.getText().equals("Threshold"))
												{
													n.setStyle(
															"-fx-stroke: blue;-fx-background-color: blue;-fx-stroke-dash-array: 2 12 12 2;");
												}
												else if (label.getText().equals("RMSE"))
												{
													n.setStyle(
															"-fx-stroke: green;-fx-background-color: green;-fx-stroke-dash-array: 0 12 2 0;");
												}
											}

										});
							}
						}
					}
				});
			}
		}
		rdChart.setCreateSymbols(false);
		yRDAxis.setForceZeroInRange(false);
		yRDAxis.setAutoRanging(true);

		validationPane.setTopAnchor(rdChart, 44.0);
		rdChart.setVisible(false);
		rdChart.setDisable(true);

		valueChart.setCreateSymbols(false);
		yValueAxis.setForceZeroInRange(false);
		yValueAxis.setAutoRanging(true);

		validationPane.setTopAnchor(valueChart, 44.0);
		valueChart.setVisible(false);
		valueChart.setDisable(true);

		Properties p = PathUtils.getGUIProperties();
		STEPSIZE = Optional.ofNullable(p.getProperty("swtoswValidation.source.stepSize"))
				.orElse("0.0001");
		THRMSE = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thrmse"))
				.orElse("0.001");
		THRD = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thrd"))
				.orElse("0.05");
		THAD = Optional.ofNullable(p.getProperty("swtoswValidation.validation.thad"))
				.orElse("0.05");

		ACTIVEEQUISDISTANTMODE = Boolean.getBoolean(Optional
				.ofNullable(p.getProperty("swtoswValidation.chart.mode.activeEquidistantMode"))
				.orElse("false"));
		MAXVALUESDISPLAYED = Integer.parseInt(Optional.ofNullable(
				p.getProperty("swtoswValidation.chart.equidistantValues.maxValuesDisplayed"))
				.orElse("5000"));
		DIFTOLERANCE = Double.parseDouble(Optional
				.ofNullable(p.getProperty("swtoswValidation.chart.differenceValues.tolerance"))
				.orElse("0.05"));
		try
		{
			Path defaultFile = PathUtils.CONFIG
					.resolve("case-validation.properties");

			p = new Properties();
			InputStream is = Files.newInputStream(defaultFile);
			p.load(is);
			is.close();

			absThreshold = Double
					.parseDouble(Optional.ofNullable(p.getProperty("absThreshold"))
							.orElse("0.001"));
			relThreshold = Double
					.parseDouble(Optional.ofNullable(p.getProperty("relThreshold"))
							.orElse("0.011"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		mappingFileSelector.graphicProperty().bind(
				Bindings.when(mappingFileSelector.hoverProperty())
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
					return new DecimalFormat("0.0###", otherSymbols).format(d * 100.0) + " %";
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
							return new DecimalFormat("0.0###E0", otherSymbols).format(d);
						else
							return new DecimalFormat("0.0###", otherSymbols).format(d * 100.0)
									+ " %";
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

		Label rmseLabel = new Label("RMSE");
		rmseColumn.setGraphic(rmseLabel);

		Label rdLabel = new Label("Relative differences");
		rdColumn.setGraphic(rdLabel);

		Label adLabel = new Label("Absolute differences");
		adColumn.setGraphic(adLabel);
	}

	@FXML
	private void handleSelectValidation(MouseEvent event)
	{
		if (event.getClickCount() < 2)
			return;

		if (backButton.isDisabled())
		{
			Validation v = validationTable.getSelectionModel().getSelectedItem();
			if (!v.isSelectable()) return;

			backButton.setVisible(true);
			backButton.setDisable(false);
			DecimalFormat df = new DecimalFormat("0.0###E0", otherSymbols);

			Label rmseLabel = new Label("RMSE");
			rmseLabel.setTooltip(
					new Tooltip("Colored in green RMSE <= " + df.format(absThreshold) + "\n"
							+ "Colored in red RMSE > " + df.format(absThreshold)));
			rmseColumn.setGraphic(rmseLabel);

			Label rdLabel = new Label("Relative differences > threshold");
			rdLabel.setTooltip(new Tooltip("Threshold = " + df.format(relThreshold)));
			rdColumn.setGraphic(rdLabel);

			Label adLabel = new Label("Absolute differences > threshold");
			adLabel.setTooltip(new Tooltip("Threshold = " + df.format(absThreshold)));
			adColumn.setGraphic(adLabel);

			WorkflowResult result = WorkflowServiceConfiguration.getSwtoswValidationResult(
					THRMSE, THRD, THAD,
					v.getName());
			validationTable.getItems().clear();
			validationTable.setItems(result.getValidation());
		}
		else
		{
			Validation v = validationTable.getSelectionModel().getSelectedItem();
			if (!v.isSelectable()) return;

			Element e = WorkflowServiceConfiguration.getSwtoswValidationElement(v.getName());
			if (e == null) return;

			ObservableList<XYChart.Series<Number, Number>> displayedValuesSeries = FXCollections
					.observableArrayList();
			ObservableList<XYChart.Series<Number, Number>> displayedAdSeries = FXCollections
					.observableArrayList();
			ObservableList<XYChart.Series<Number, Number>> displayedRdSeries = FXCollections
					.observableArrayList();

			Task<?> task = new Task<Void>()
			{

				@Override
				protected Void call() throws Exception
				{

					Map<Double, ComparisionData> values = sortByValue(WorkflowServiceConfiguration
							.calculateElementValues(refPath, modPath, step, e));
					if (values == null) throw new Exception("Values not found");

					xyLastValue = null;
					xyLastAddedValue = null;

					XYChart.Series<Number, Number> adValues = new XYChart.Series<>();
					thAdValues = new XYChart.Series<>();
					rmseValues = new XYChart.Series<>();

					XYChart.Series<Number, Number> rdValues = new XYChart.Series<>();
					thRdValues = new XYChart.Series<>();

					XYChart.Series<Number, Number> refValues = new XYChart.Series<>();
					XYChart.Series<Number, Number> actValues = new XYChart.Series<>();

					for (ComparisionData xyValue : values.values())
					{

						if (!showPoint(values, xyValue))
						{
							xyLastValue = new ComparisionData(xyValue);
							continue;
						}

						if (showLastValue())
						{

							adValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(),
											xyLastValue.getAbsDif()));
							thAdValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(), absThreshold));
							rmseValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(), e.getAbsRmes()));

							rdValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(),
											xyLastValue.getRelDif()));
							thRdValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(), relThreshold));

							refValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(),
											xyLastValue.getRefData()));
							actValues.getData()
									.add(new XYChart.Data<>(xyLastValue.getTime(),
											xyLastValue.getModelicaData()));
						}

						adValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(),
										xyValue.getAbsDif()));
						thAdValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(), absThreshold));
						rmseValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(), e.getAbsRmes()));

						rdValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(),
										xyValue.getRelDif()));
						thRdValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(), relThreshold));

						refValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(), xyValue.getRefData()));
						actValues.getData()
								.add(new XYChart.Data<>(xyValue.getTime(),
										xyValue.getModelicaData()));

						xyLastValue = new ComparisionData(xyValue);
						xyLastAddedValue = new ComparisionData(xyValue);
					}

					LOG.info("Displayed points: " + adValues.getData().size());

					adValues.setName("Absolute differences");
					thAdValues.setName("Threshold");
					rmseValues.setName("RMSE");

					rdValues.setName("Relative differences");
					thRdValues.setName("Threshold");

					refValues.setName("Reference data");
					actValues.setName("Case data");

					displayedAdSeries.add(adValues);
					if (limitCheckbox.isSelected())
						displayedAdSeries.add(thAdValues);
					if (rmseCheckbox.isSelected())
						displayedAdSeries.add(rmseValues);

					displayedRdSeries.add(rdValues);
					if (limitCheckbox.isSelected())
						displayedRdSeries.add(thRdValues);

					displayedValuesSeries.add(refValues);
					displayedValuesSeries.add(actValues);

					return null;
				}
			};
			task.setOnSucceeded(new EventHandler<WorkerStateEvent>()
			{
				@SuppressWarnings("static-access")
				@Override
				public void handle(WorkerStateEvent t)
				{
					adChart.getData().addAll(displayedAdSeries);
					rdChart.getData().addAll(displayedRdSeries);
					valueChart.getData().addAll(displayedValuesSeries);

					validationTable.setVisible(false);
					validationTable.setDisable(true);

					rmseCheckbox.setVisible(true);
					rmseCheckbox.setDisable(false);

					limitCheckbox.setVisible(true);
					limitCheckbox.setDisable(false);

					adChart.setTitle("Absolute differences");
					adChart.setVisible(true);
					adChart.setDisable(false);

					validationPane.setTopAnchor(rdChart, 480.0);
					rdChart.setTitle("Relative differences");
					rdChart.setVisible(true);
					rdChart.setDisable(false);

					validationPane.setTopAnchor(valueChart, 920.0);
					valueChart.setTitle(v.getName());
					valueChart.setVisible(true);
					valueChart.setDisable(false);

					mainService.getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
				}
			});

			Thread t = new Thread(task);
			t.start();

			mainService.getPrimaryStage().getScene().setCursor(Cursor.WAIT);
		}
	}

	private Map<Double, ComparisionData> sortByValue(Map<Double, ComparisionData> unsortMap)
	{
		if (unsortMap == null) return null;

		// 1. Convert Map to List of Map
		List<Map.Entry<Double, ComparisionData>> list = new LinkedList<Map.Entry<Double, ComparisionData>>(
				unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<Double, ComparisionData>>()
		{
			public int compare(Map.Entry<Double, ComparisionData> o1,
					Map.Entry<Double, ComparisionData> o2)
			{
				return Double.compare(o1.getValue().getTime(), o2.getValue().getTime());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
		double avgValue = 0.0;
		double minValue = Double.POSITIVE_INFINITY;
		Map<Double, ComparisionData> sortedMap = new LinkedHashMap<Double, ComparisionData>();
		for (Map.Entry<Double, ComparisionData> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());

			avgValue += entry.getValue().getAbsDif();
			if (minValue > entry.getValue().getAbsDif())
				minValue = entry.getValue().getAbsDif();
		}

		avgValue /= list.size();
		return sortedMap;
	}

	private boolean showPoint(Map<Double, ComparisionData> values, ComparisionData xyValue)
	{
		if (!ACTIVEEQUISDISTANTMODE)
			return showDifferentPoint(values, xyValue);
		return showEquidistantPoint(values);
	}

	private boolean showLastValue()
	{
		if (!ACTIVEEQUISDISTANTMODE)
			return showLastDifferentPoint();
		return false;
	}

	private boolean showLastDifferentPoint()
	{
		return (xyLastValue != null && xyLastValue.getTime() != xyLastAddedValue.getTime());
	}

	private boolean showDifferentPoint(Map<Double, ComparisionData> values, ComparisionData xyValue)
	{
		boolean showPoint = true;
		if (xyLastAddedValue != null)
		{
			showPoint = false;
			if (Math.abs((xyValue.getAbsDif() - xyLastAddedValue.getAbsDif())
					/ xyLastAddedValue.getAbsDif()) > DIFTOLERANCE)
				showPoint = true;
			else if (Math.abs((xyValue.getRelDif() - xyLastAddedValue.getRelDif())
					/ xyLastAddedValue.getRelDif()) > DIFTOLERANCE)
				showPoint = true;
			else if (Math.abs((xyValue.getRefData() - xyLastAddedValue.getRefData())
					/ xyLastAddedValue.getRefData()) > DIFTOLERANCE)
				showPoint = true;
			else if (Math.abs((xyValue.getModelicaData() - xyLastAddedValue.getModelicaData())
					/ xyLastAddedValue.getModelicaData()) > DIFTOLERANCE)
				showPoint = true;
		}

		if (!showPoint)
			return false;

		return true;
	}

	private boolean showEquidistantPoint(Map<Double, ComparisionData> values)
	{
		if (totalValues == -1)
			totalValues = values.values().size();

		if (rejectValue == -1)
			rejectValue = totalValues / MAXVALUESDISPLAYED;

		if (rejectValue > 1)
		{
			rejectValue--;
			return false;
		}

		rejectValue = totalValues / MAXVALUESDISPLAYED;
		return true;
	}

	@SuppressWarnings("static-access")
	@FXML
	private void handleBackAction()
	{
		if (validationTable.isVisible())
		{
			backButton.setVisible(false);
			backButton.setDisable(true);

			Label rmseLabel = new Label("RMSE");
			rmseColumn.setGraphic(rmseLabel);

			Label rdLabel = new Label("Relative differences");
			rdColumn.setGraphic(rdLabel);

			Label adLabel = new Label("Absolute differences");
			adColumn.setGraphic(adLabel);

			WorkflowResult result = WorkflowServiceConfiguration
					.getSwtoswValidationResult(THRMSE, THRD, THAD);
			validationTable.getItems().clear();
			validationTable.setItems(result.getValidation());
		}
		else
		{
			validationTable.setVisible(true);
			validationTable.setDisable(false);

			rmseCheckbox.setVisible(false);
			rmseCheckbox.setDisable(true);

			limitCheckbox.setVisible(false);
			limitCheckbox.setDisable(true);

			adChart.getData().clear();
			adChart.setVisible(false);
			adChart.setDisable(true);

			validationPane.setTopAnchor(rdChart, 44.0);
			rdChart.getData().clear();
			rdChart.setVisible(false);
			rdChart.setDisable(true);

			validationPane.setTopAnchor(valueChart, 44.0);
			valueChart.getData().clear();
			valueChart.setVisible(false);
			valueChart.setDisable(true);

		}
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
	private AnchorPane						validationPane;
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

	@FXML
	private CheckBox						rmseCheckbox;
	@FXML
	private CheckBox						limitCheckbox;
	@FXML
	private LineChart<Number, Number>		adChart;
	@FXML
	private NumberAxis						xADAxis;
	@FXML
	private NumberAxis						yADAxis;
	@FXML
	private LineChart<Number, Number>		rdChart;
	@FXML
	private NumberAxis						xRDAxis;
	@FXML
	private NumberAxis						yRDAxis;
	@FXML
	private LineChart<Number, Number>		valueChart;
	@FXML
	private NumberAxis						xValueAxis;
	@FXML
	private NumberAxis						yValueAxis;

	private Image							selectImage			= new Image(
			getClass().getResourceAsStream("/img/select.png"));
	private Image							whiteSelectImage	= new Image(
			getClass().getResourceAsStream("/img/white-select.png"));

	private GuiFileChooser					fileChooser;

	private String							STEPSIZE;
	private String							THRMSE;
	private String							THRD;
	private String							THAD;

	private String							refPath				= "";
	private String							modPath				= "";
	private String							step				= "";

	private double							absThreshold;
	private double							relThreshold;
	private XYChart.Series<Number, Number>	rmseValues;
	private XYChart.Series<Number, Number>	thAdValues;
	private XYChart.Series<Number, Number>	thRdValues;

	private final DecimalFormatSymbols		otherSymbols		= new DecimalFormatSymbols(
			Locale.getDefault());

	private final String					validValue			= "#AAEEA7";
	private final String					notValidValue		= "#FAA98E";
	private String							lastInputPath		= PathUtils.DATA_TEST.toString();

	private boolean							ACTIVEEQUISDISTANTMODE;

	private int								MAXVALUESDISPLAYED;
	private int								totalValues			= -1;
	private int								rejectValue			= -1;

	private double							DIFTOLERANCE;
	private ComparisionData					xyLastValue			= null;
	private ComparisionData					xyLastAddedValue	= null;

	private static final Logger				LOG					= LoggerFactory
			.getLogger(SwtoswValidationController.class);
}
