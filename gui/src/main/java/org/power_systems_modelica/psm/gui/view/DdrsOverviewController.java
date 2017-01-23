package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.util.Callback;

public class DdrsOverviewController implements MainChildrenController {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMenuActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSummaryLabels() {
		
		return null;
	}

	@FXML
	private void initialize() {

		fileContentPane.setVisible(false);
		Utils.setDragablePane(fileContentPane);

		nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

		nameDdrColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionDdrColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationDdrColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
		typeDdrColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		catalogs.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				String catalogName = (String) nameCatalogColumn.getCellObservableValue((int) newSelection).getValue();
				ddrs.setItems(mainService.getDdrs(catalogName));
			}
		});

		ddrs.setRowFactory(new Callback<TableView<Ddr>, TableRow<Ddr>>() {
			@Override
			public TableRow<Ddr> call(TableView<Ddr> tableView) {
				ContextMenu contextMenu = new ContextMenu();
				TableRow<Ddr> row = new TableRow<>();
				
		        row.itemProperty().addListener((obs, oldItem, newItem) -> {
		        	updateMenu(contextMenu, newItem);
		        });
		        	
		        row.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> 
		             row.setContextMenu(isNowEmpty ? null : contextMenu));

		        return row ;
			}

		});
	}

	private void updateMenu(ContextMenu contextMenu, Ddr ddr) {
		
		contextMenu.getItems().clear();
		if (ddr == null) return;
		
		MenuItem menuItem = new MenuItem("Duplicate DDR");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				duplicateDdr(ddr);
			}

		});
		contextMenu.getItems().add(menuItem);
		
		Path ddrPath = Paths.get(ddr.getLocation());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(ddrPath)) {
			
			SeparatorMenuItem sMenuItem = new SeparatorMenuItem();

			for (Path entry : stream) {
				if (entry.toString().endsWith(".dyd") || entry.toString().endsWith(".par")) {
					if (sMenuItem != null) {
						contextMenu.getItems().add(sMenuItem);
						sMenuItem = null;
					}
					menuItem = new MenuItem(entry.getFileName().toString());
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {

							showDdrFileContent(ddr, entry.getFileName().toString());
						}
					});
					contextMenu.getItems().add(menuItem);
				}
			}
		} catch (IOException e1) {
		}
	}
	
	@FXML
	private void handleFindContentEvent() {
		codeEditor.find();
	}

	@FXML
	private void handleSaveFileContentEvent() {
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		try {
			PathUtils.saveFile(location, file, ddrContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileContentPane.setVisible(false);
	}

	@FXML
	private void handleRevertFileContentEvent() {
		codeEditor.revertEdits();
	}

	@FXML
	private void handleCloseFileContentEvent() {
		fileContentPane.setVisible(false);
	}

	private void showDdrFileContent(Ddr ddr, String file) {

		StringBuilder ddrContent = new StringBuilder();
		try {
			ddrContent = PathUtils.loadFile(ddr.getLocation(), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		codeEditor.setEditingFile(ddr.getLocation(), file);
		codeEditor.setCode(ddrContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
	}

	private void duplicateDdr(Ddr ddr) {
		String outputPath = PathUtils.directoryOutput(mainService.getPrimaryStage(), ddr.getLocation());
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
		
		if (filteredDdr.count() > 0) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Duplicate DDR");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to overwrite this DDR?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() != ButtonType.OK) return;
		}
		
		Ddr ddrOut = new Ddr();
		ddrOut.setLocation(outputPath);
		if (mainService.duplicateDdr(ddr, ddrOut)) {
			Catalog catalog = catalogs.getSelectionModel().getSelectedItem();
			ddrs.setItems(mainService.getDdrs(catalog.getName()));
		}
	}

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

		catalogs.setItems(mainService.getCatalogs("ddrs"));
		catalogs.getSelectionModel().selectFirst();
	}

	@FXML
	private TitledPane fileContentPane;
	@FXML
	private CodeEditor codeEditor;

	@FXML
	private TableView<Catalog> catalogs;
	@FXML
	private TableColumn<Catalog, String> nameCatalogColumn;
	@FXML
	private TableColumn<Catalog, String> descriptionCatalogColumn;
	@FXML
	private TableColumn<Catalog, String> locationCatalogColumn;

	@FXML
	private TableView<Ddr> ddrs;
	@FXML
	private TableColumn<Ddr, String> nameDdrColumn;
	@FXML
	private TableColumn<Ddr, String> descriptionDdrColumn;
	@FXML
	private TableColumn<Ddr, String> locationDdrColumn;
	@FXML
	private TableColumn<Ddr, DdrType> typeDdrColumn;

	private MainService mainService;
}
