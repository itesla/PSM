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
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.ddr.dyd.Diagnostics;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.utils.fx.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.fx.PathUtilsFX;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class DdrsOverviewController extends MainChildrenController
{

	@Override
	public void setMainService(MainService mainService)
	{
		super.setMainService(mainService);

		catalogs.setItems(FXCollections.observableArrayList(CatalogService.getCatalogs("ddrs")));
		catalogs.getSelectionModel().selectFirst();
	}

	@FXML
	private void initialize()
	{

		fileContentPane.setVisible(false);
		UtilsFX.setDragablePane(fileContentPane);

		nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCatalogColumn
				.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCatalogColumn
				.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

		nameDdrColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionDdrColumn
				.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationDdrColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
		typeDdrColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		catalogs.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null)
					{
						String catalogName = (String) nameCatalogColumn
								.getCellObservableValue((int) newSelection).getValue();
						ddrs.setItems(FXCollections
								.observableArrayList(DdrService.getDdrs(catalogName)));
						ddrs.getItems().sort(new Comparator<Ddr>()
						{

							@Override
							public int compare(Ddr d1, Ddr d2)
							{
								return d1.getName().compareToIgnoreCase(d2.getName());
							}

						});
					}
				});

		ddrs.setRowFactory(new Callback<TableView<Ddr>, TableRow<Ddr>>()
		{
			@Override
			public TableRow<Ddr> call(TableView<Ddr> tableView)
			{
				ContextMenu contextMenu = new ContextMenu();
				TableRow<Ddr> row = new TableRow<>();

				row.itemProperty().addListener((obs, oldItem, newItem) -> {
					updateMenu(contextMenu, newItem);
				});

				row.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> row
						.setContextMenu(isNowEmpty ? null : contextMenu));

				return row;
			}

		});
	}

	private void updateMenu(ContextMenu contextMenu, Ddr ddr)
	{

		contextMenu.getItems().clear();
		if (ddr == null) return;

		MenuItem menuItem;
		Path ddrPath = Paths.get(ddr.getLocation());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(ddrPath))
		{

			for (Path entry : stream)
			{
				if (entry.toString().endsWith(".dyd") || entry.toString().endsWith(".par"))
				{
					menuItem = new MenuItem(entry.getFileName().toString());
					menuItem.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent event)
						{

							showDdrFileContent(ddr, entry.getFileName().toString());
						}
					});
					contextMenu.getItems().add(menuItem);
				}
			}

			contextMenu.getItems().add(new SeparatorMenuItem());
		}
		catch (IOException e1)
		{
		}

		menuItem = new MenuItem("Duplicate DDR");
		menuItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{

				duplicateDdr(ddr);
			}

		});
		contextMenu.getItems().add(menuItem);

		menuItem = new MenuItem("Check DDR");
		menuItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{

				checkDdr(ddr);
			}

		});
		contextMenu.getItems().add(menuItem);

	}

	@FXML
	private void handleFindContentEvent()
	{
		codeEditor.find();
	}

	@FXML
	private void handleSaveFileContentEvent()
	{
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try
		{
			PathUtils.saveFile(location, file, ddrContent);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		fileContentPane.setVisible(false);
	}

	@FXML
	private void handleRevertFileContentEvent()
	{
		codeEditor.revertEdits();
	}

	@FXML
	private void handleCloseFileContentEvent()
	{
		fileContentPane.setVisible(false);
	}

	private void showDdrFileContent(Ddr ddr, String file)
	{

		StringBuilder ddrContent = new StringBuilder();
		try
		{
			ddrContent = PathUtils.loadFile(ddr.getLocation(), file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		revertEditor.setVisible(true);
		revertEditor.setDisable(false);
		saveEditor.setVisible(true);
		saveEditor.setDisable(false);
		codeEditor.setEditingFile(ddr.getLocation(), file);
		codeEditor.setCode(ddrContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
	}

	private void checkDdr(Ddr ddr)
	{
		Diagnostics diagnostics = DdrService.check(ddr.getLocation());

		StringBuilder ddrContent = Utils.getCheckDdrResult(diagnostics);
		if (ddrContent.length() == 0)
			ddrContent.append("Successfully checked\n");

		revertEditor.setVisible(false);
		revertEditor.setDisable(true);
		saveEditor.setVisible(false);
		saveEditor.setDisable(true);
		codeEditor.setCode(ddrContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
	}

	private void duplicateDdr(Ddr ddr)
	{
		String outputPath = PathUtilsFX.directoryOutput(mainService.getPrimaryStage(),
				Paths.get(ddr.getLocation()).resolve("..").toString());
		if (outputPath == null) return;

		Stream<Ddr> filteredDdr = ddrs.getItems().stream().filter(d -> {
			try
			{
				return Files.isSameFile(Paths.get(d.getLocation()), Paths.get(outputPath));
			}
			catch (IOException e)
			{
			}
			return false;
		});

		if (filteredDdr.count() > 0)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Duplicate DDR");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to overwrite this DDR?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() != ButtonType.OK) return;
		}

		Ddr ddrOut = new Ddr();
		ddrOut.setLocation(outputPath);
		if (DdrService.duplicateDdr(ddr, ddrOut))
		{
			Catalog catalog = catalogs.getSelectionModel().getSelectedItem();
			ddrs.setItems(
					FXCollections.observableArrayList(DdrService.getDdrs(catalog.getName())));
		}
	}

	@FXML
	private TitledPane						fileContentPane;
	@FXML
	private CodeEditor						codeEditor;

	@FXML
	private Button							revertEditor;
	@FXML
	private Button							saveEditor;

	@FXML
	private TableView<Catalog>				catalogs;
	@FXML
	private TableColumn<Catalog, String>	nameCatalogColumn;
	@FXML
	private TableColumn<Catalog, String>	descriptionCatalogColumn;
	@FXML
	private TableColumn<Catalog, String>	locationCatalogColumn;

	@FXML
	private TableView<Ddr>					ddrs;
	@FXML
	private TableColumn<Ddr, String>		nameDdrColumn;
	@FXML
	private TableColumn<Ddr, String>		descriptionDdrColumn;
	@FXML
	private TableColumn<Ddr, String>		locationDdrColumn;
	@FXML
	private TableColumn<Ddr, DdrType>		typeDdrColumn;

}
