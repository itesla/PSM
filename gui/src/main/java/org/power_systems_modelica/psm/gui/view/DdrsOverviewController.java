package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DdrsOverviewController {

    @FXML
    private void initialize() {
        
    	nameCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	descriptionCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    	locationCatalogColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    	
    	nameDdrColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	descriptionDdrColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
    	typeDdrColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    	sourceDdrColumn.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());

    	catalogs.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
    		if (newSelection != null) {
    			String catalogName = (String) nameCatalogColumn.getCellObservableValue((int) newSelection).getValue();
    			ddrs.setItems(mainApp.getDdrs(catalogName));
    		}
    	});
    }

    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		catalogs.setItems(mainApp.getCatalogs());
		catalogs.getSelectionModel().selectFirst();
	}

	@FXML
	private TableView catalogs;
    @FXML
    private TableColumn<Catalog, String> nameCatalogColumn;
    @FXML
    private TableColumn<Catalog, String> descriptionCatalogColumn;
    @FXML
    private TableColumn<Catalog, String> locationCatalogColumn;

    @FXML
	private TableView ddrs;
    @FXML
    private TableColumn<Ddr, String> nameDdrColumn;
    @FXML
    private TableColumn<Ddr, String> descriptionDdrColumn;
    @FXML
    private TableColumn<Ddr, DdrType> typeDdrColumn;
    @FXML
    private TableColumn<Ddr, String> sourceDdrColumn;
	
	private MainApp mainApp;
}
