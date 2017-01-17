package org.power_systems_modelica.psm.gui.view;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.List;

import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;

import com.google.common.collect.Iterables;

import eu.itesla_project.iidm.network.Network;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class CasesOverviewController implements MainChildrenController {

	@Override
	public void handleMainAction() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void handleMenuAction(String action)
	{
		
	}

	@Override
	public String getMainAction() {

		return null;
	}

	@Override
	public List<String> getMenuActions() {

		return null;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		return null;
	}
	
	@FXML
	private void initialize() {

		summaryPane.setVisible(false);
		Utils.setDragablePane(summaryPane);

		nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

		nameCaseColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCaseColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCaseColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
		formatCaseColumn.setCellValueFactory(cellData -> cellData.getValue().formatProperty());
		sizeCaseColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());

		catalogs.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				String catalogName = (String) nameCatalogColumn.getCellObservableValue((int) newSelection).getValue();
				cases.setItems(mainService.getCases(catalogName));
			}
		});

		cases.setRowFactory(new Callback<TableView<Case>, TableRow<Case>>() {
			@Override
			public TableRow<Case> call(TableView<Case> tableView) {
				final TableRow<Case> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem summaryMenuItem = new MenuItem("Summary");
				summaryMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Case c = row.getItem();
						Network n = mainService.getCaseSummary(c);
						showCaseSummary(MouseInfo.getPointerInfo().getLocation(), c, n);
					}
				});
				contextMenu.getItems().add(summaryMenuItem);
				final MenuItem conversionMenuItem = new MenuItem("Conversion");
				conversionMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Case c = row.getItem();
						mainService.showConversionWithCase(c);
					}
				});
				contextMenu.getItems().add(conversionMenuItem);
				final MenuItem conversionResultMenuItem = new MenuItem("Conversion Results");
				conversionResultMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Case c = row.getItem();
						mainService.showConversionResult(c);
					}
				});
				contextMenu.getItems().add(conversionResultMenuItem);
				final MenuItem simluationMenuItem = new MenuItem("Simulation");
				simluationMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Case c = row.getItem();
						mainService.showSimulationWithCase(c);
					}
				});
				contextMenu.getItems().add(simluationMenuItem);
				final MenuItem compareMenuItem = new MenuItem("Compare Loadflows");
				compareMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Case c = row.getItem();
						mainService.showCompareLoadflowsWithCase(c);
					}
				});
				contextMenu.setOnShown(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent e) {

						Case c = row.getItem();
						ObservableList<MenuItem> items = contextMenu.getItems();
						for (MenuItem item : items) {
							if (item.getText().equals("Simulation") || item.getText().equals("Conversion Results"))
								if (PathUtils.existsFile(c.getLocation(), c.getName() + ".mo"))
									item.setDisable(false);
								else
									item.setDisable(true);
							else
								item.setDisable(false);
						}
					}
				});
				contextMenu.getItems().add(compareMenuItem);
				// Set context menu on row, but use a binding to make it only
				// show for non-empty rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});

	}

	@FXML
	private void handleCloseSummaryEvent() {
		summaryPane.setVisible(false);
	}

	public void showCaseSummary(Point point, Case c, Network n) {
		gridPane.getChildren().removeAll(gridPane.getChildren());

		// Name
		Label label = new Label("Name");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 0);
		Label text = new Label(c.getName());
		text.setWrapText(true);
		gridPane.add(text, 1, 0);
		RowConstraints constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Description
		label = new Label("Description");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 1);
		text = new Label(c.getDescription());
		text.setWrapText(true);
		gridPane.add(text, 1, 1);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(60);
		gridPane.getRowConstraints().add(constraints);

		// Substations
		label = new Label("Substations");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 2);
		text = new Label("" + n.getSubstationCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 2);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// VoltageLevels
		label = new Label("Voltage Levels");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 3);
		text = new Label("" + n.getVoltageLevelCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 3);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Buses
		label = new Label("Buses");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 4);
		text = new Label("" + Iterables.size(n.getBusBreakerView().getBuses()));
		text.setWrapText(true);
		gridPane.add(text, 1, 4);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Lines
		label = new Label("Lines");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 5);
		text = new Label("" + n.getLineCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 5);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		summaryPane.setLayoutX(point.x - 200);
		summaryPane.setLayoutY(point.y - 400);
		summaryPane.setVisible(true);
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

		catalogs.setItems(mainService.getCatalogs("cases"));
		catalogs.getSelectionModel().selectFirst();
	}

	@FXML
	private TitledPane summaryPane;
	@FXML
	private GridPane gridPane;

	@FXML
	private TableView<Catalog> catalogs;
	@FXML
	private TableColumn<Catalog, String> nameCatalogColumn;
	@FXML
	private TableColumn<Catalog, String> descriptionCatalogColumn;
	@FXML
	private TableColumn<Catalog, String> locationCatalogColumn;

	@FXML
	private TableView<Case> cases;
	@FXML
	private TableColumn<Case, String> nameCaseColumn;
	@FXML
	private TableColumn<Case, String> descriptionCaseColumn;
	@FXML
	private TableColumn<Case, String> locationCaseColumn;
	@FXML
	private TableColumn<Case, String> formatCaseColumn;
	@FXML
	private TableColumn<Case, Number> sizeCaseColumn;

	private MainService mainService;

}
