package org.power_systems_modelica.psm.gui.view;

import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CasesOverviewController {

    @FXML
    private void initialize() {
        
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
    			cases.setItems(mainApp.getCases(catalogName));
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
	private TableView cases;
    @FXML
    private TableColumn<Case, String> nameCaseColumn;
    @FXML
    private TableColumn<Case, String> descriptionCaseColumn;
    @FXML
    private TableColumn<Case, String> locationCaseColumn;
    @FXML
    private TableColumn<Case, Number> formatCaseColumn;
    @FXML
    private TableColumn<Case, Number> sizeCaseColumn;

	private MainApp mainApp;
}
