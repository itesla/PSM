package org.power_systems_modelica.psm.gui.view;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.Utils;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

public class DdrsOverviewController {

	@FXML
	private void initialize() {
		
		fileContentPane.setVisible(false);

		nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

		nameDdrColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		descriptionDdrColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		locationDdrColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
		typeDdrColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		sourceDdrColumn.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());

		catalogs.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				String catalogName = (String) nameCatalogColumn.getCellObservableValue((int) newSelection).getValue();
				ddrs.setItems(mainApp.getDdrs(catalogName));
			}
		});

    	ddrs.setRowFactory(new Callback<TableView<Ddr>, TableRow<Ddr>>() {  
            @Override  
            public TableRow<Ddr> call(TableView<Ddr> tableView) {  
                final TableRow<Ddr> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                final MenuItem emodelsMenuItem = new MenuItem("models.dyd");  
                emodelsMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {
                    	
                    	Ddr ddr = row.getItem();
                    	showDdrFileContent(ddr, "models.dyd");
                    }

                });  
                contextMenu.getItems().add(emodelsMenuItem);  
                final MenuItem systemMenuItem = new MenuItem("system.dyd");  
                systemMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {
                    	
                    	Ddr ddr = row.getItem();
                    	showDdrFileContent(ddr, "system.dyd");
                    }

                });  
                contextMenu.getItems().add(systemMenuItem);  
                final MenuItem paramsMenuItem = new MenuItem("params.par");  
                paramsMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {
                    	
                    	Ddr ddr = row.getItem();
                    	showDdrFileContent(ddr, "params.par");
                    }

                });  
                contextMenu.getItems().add(paramsMenuItem);  
               // Set context menu on row, but use a binding to make it only show for non-empty rows:  
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row ;  
            }  
        });  
	}

	@FXML
	private void handleSaveFileContentEvent() {
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();
		
		try {
			Utils.saveFile(location, file, ddrContent);
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
			ddrContent = Utils.loadFile(ddr.getLocation(), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		codeEditor.setEditingFile(ddr.getLocation(), file);
		codeEditor.setCode(ddrContent);
		codeEditor.setVisible(true);
		fileContentPane.setVisible(true);
	}  

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		catalogs.setItems(mainApp.getCatalogs("ddrs"));
		catalogs.getSelectionModel().selectFirst();
	}
	
	@FXML
	private TitledPane fileContentPane;
	@FXML
	private CodeEditor codeEditor;

	@FXML
	private TableView catalogs;
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
	@FXML
	private TableColumn<Ddr, String> sourceDdrColumn;

	private MainApp mainApp;
}
