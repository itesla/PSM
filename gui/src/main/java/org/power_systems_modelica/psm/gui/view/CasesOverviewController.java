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

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.Comparator;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.service.CaseService;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;

import com.google.common.collect.Iterables;

import com.powsybl.iidm.network.Network;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class CasesOverviewController extends MainChildrenController
{

	public void showCaseSummary(PointerInfo info, Case c, Network n)
	{
		Point point = info.getLocation();
		
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

		// Buses
		label = new Label("Buses");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 3);
		text = new Label("" + Iterables.size(n.getBusBreakerView().getBuses()));
		text.setWrapText(true);
		gridPane.add(text, 1, 3);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Lines
		label = new Label("Lines");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 4);
		text = new Label("" + n.getLineCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 4);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);
		
		// Transformers
		label = new Label("Transformers");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 5);
		text = new Label("" + (n.getTwoWindingsTransformerCount() + n.getThreeWindingsTransformerCount()));
		text.setWrapText(true);
		gridPane.add(text, 1, 5);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Generators
		label = new Label("Generators");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 6);
		text = new Label("" + n.getGeneratorCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 6);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		// Loads
		label = new Label("Loads");
		label.setStyle("-fx-font-weight: bold");
		gridPane.add(label, 0, 7);
		text = new Label("" + n.getLoadCount());
		text.setWrapText(true);
		gridPane.add(text, 1, 7);
		constraints = new RowConstraints();
		constraints.setMinHeight(30);
		constraints.setPrefHeight(30);
		gridPane.getRowConstraints().add(constraints);

		Bounds bounds = casesView.getBoundsInLocal();
        Bounds screenBounds = casesView.localToScreen(bounds);
		
		Point virtualPoint = new Point(point);
		virtualPoint.x -= screenBounds.getMinX();
		virtualPoint.y -= screenBounds.getMinY();
		virtualPoint.x -= summaryPane.getWidth()/2;
		virtualPoint.y -= summaryPane.getHeight()/2;
		if (virtualPoint.x < 0) virtualPoint.x = 30;
		if (virtualPoint.y < 0) virtualPoint.y = 0;
		if (virtualPoint.x + summaryPane.getWidth() > casesView.getWidth()) 
			virtualPoint.x = (int) Math.rint(casesView.getWidth() - summaryPane.getWidth() - 30);
		if (virtualPoint.y + summaryPane.getHeight() > casesView.getHeight()) 
			virtualPoint.y = (int) Math.rint(casesView.getHeight() - summaryPane.getHeight() - 30);

		summaryPane.setLayoutX(virtualPoint.x);
		summaryPane.setLayoutY(virtualPoint.y);
		summaryPane.setVisible(true);
	}

	@Override
	public void setMainService(MainService mainService)
	{
		super.setMainService(mainService);

		catalogs.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("cases")));
		catalogs.getSelectionModel().selectFirst();
	}

	@FXML
	private void initialize()
	{

		Properties p = PathUtils.getGUIProperties();
		DISABLELOADFLOWVIEW = Boolean.valueOf(Optional.ofNullable(p.getProperty("menu.disableLoadflowView")).orElse("true"));

		summaryPane.setVisible(false);
		UtilsFX.setDragablePane(summaryPane);

		nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCatalogColumn
				.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCatalogColumn
				.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

		nameCaseColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCaseColumn
				.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCaseColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
		formatCaseColumn.setCellValueFactory(cellData -> cellData.getValue().formatProperty());
		sizeCaseColumn.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());

		catalogs.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null)
					{
						String catalogName = (String) nameCatalogColumn
								.getCellObservableValue((int) newSelection).getValue();
						cases.setItems(FXCollections
								.observableArrayList(CaseService.getCases(catalogName)));
						cases.getItems().sort(new Comparator<Case>()
						{

							@Override
							public int compare(Case c1, Case c2)
							{
								return c1.getName().compareToIgnoreCase(c2.getName());
							}

						});
					}
				});

		cases.setRowFactory(new Callback<TableView<Case>, TableRow<Case>>()
		{
			@Override
			public TableRow<Case> call(TableView<Case> tableView)
			{
				final TableRow<Case> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem summaryMenuItem = new MenuItem("Summary");
				summaryMenuItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{

						Case c = row.getItem();
						Network n = CaseService.getCaseSummary(c);
						showCaseSummary(MouseInfo.getPointerInfo(), c, n);
					}
				});
				contextMenu.getItems().add(summaryMenuItem);
				final MenuItem conversionMenuItem = new MenuItem("Conversion");
				conversionMenuItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{

						Case c = row.getItem();
						mainService.showConversionWithCase(c);
					}
				});
				contextMenu.getItems().add(conversionMenuItem);
				final MenuItem conversionResultMenuItem = new MenuItem("Modelica file");
				conversionResultMenuItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{

						Case c = row.getItem();
						mainService.showConversionResult(c);
					}
				});
				contextMenu.getItems().add(conversionResultMenuItem);
				final MenuItem simluationMenuItem = new MenuItem("Simulation");
				simluationMenuItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{

						Case c = row.getItem();
						mainService.showSimulationWithCase(c);
					}
				});
				contextMenu.getItems().add(simluationMenuItem);
				final MenuItem compareMenuItem = new MenuItem("Loadflow");
				compareMenuItem.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event)
					{

						Case c = row.getItem();
						mainService.showCompareLoadflowsWithCase(c);
					}
				});
				contextMenu.setOnShown(new EventHandler<WindowEvent>()
				{
					public void handle(WindowEvent e)
					{

						Case c = row.getItem();
						ObservableList<MenuItem> items = contextMenu.getItems();
						for (MenuItem item : items)
						{
							if (item.getText().equals("Simulation")
									|| item.getText().equals("Modelica file"))
							{
								if (PathUtils.existsFile(c.getLocation(), c.getName() + ".mo"))
									item.setDisable(false);
								else
									item.setDisable(true);
							}
							else if (item.getText().equals("Loadflow"))
							{
								item.setDisable(DISABLELOADFLOWVIEW);
							}
							else
								item.setDisable(false);
						}
					}
				});
				contextMenu.getItems().add(compareMenuItem);
				// Set context menu on row, but use a binding to make it only
				// show for non-empty rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null)
								.otherwise(contextMenu));
				return row;
			}
		});

	}

	@FXML
	private void handleCloseSummaryEvent()
	{
		summaryPane.setVisible(false);
	}

	
	@FXML
	private AnchorPane						casesView;
	@FXML
	private TitledPane						summaryPane;
	@FXML
	private GridPane						gridPane;

	@FXML
	private TableView<Catalog>				catalogs;
	@FXML
	private TableColumn<Catalog, String>	nameCatalogColumn;
	@FXML
	private TableColumn<Catalog, String>	descriptionCatalogColumn;
	@FXML
	private TableColumn<Catalog, String>	locationCatalogColumn;

	@FXML
	private TableView<Case>					cases;
	@FXML
	private TableColumn<Case, String>		nameCaseColumn;
	@FXML
	private TableColumn<Case, String>		descriptionCaseColumn;
	@FXML
	private TableColumn<Case, String>		locationCaseColumn;
	@FXML
	private TableColumn<Case, String>		formatCaseColumn;
	@FXML
	private TableColumn<Case, Number>		sizeCaseColumn;

	private Boolean		DISABLELOADFLOWVIEW;
}
