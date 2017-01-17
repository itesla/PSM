package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.util.List;

import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.stage.WindowEvent;
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
		sourceDdrColumn.setCellValueFactory(cellData -> cellData.getValue().sourceProperty());

		catalogs.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				String catalogName = (String) nameCatalogColumn.getCellObservableValue((int) newSelection).getValue();
				ddrs.setItems(mainService.getDdrs(catalogName));
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
				final MenuItem eventsMenuItem = new MenuItem("events.dyd");
				eventsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Ddr ddr = row.getItem();
						showDdrFileContent(ddr, "events.dyd");
					}

				});
				contextMenu.getItems().add(eventsMenuItem);
				final MenuItem paramsMenuItem = new MenuItem("params.par");
				paramsMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						Ddr ddr = row.getItem();
						showDdrFileContent(ddr, "params.par");
					}

				});
				contextMenu.getItems().add(paramsMenuItem);
				contextMenu.setOnShown(new EventHandler<WindowEvent>() {
				      public void handle(WindowEvent e) {
				    	  
				    	  Ddr ddr = row.getItem();
				    	  ObservableList<MenuItem> items = contextMenu.getItems();
				    	  for (MenuItem item: items) {
				    		  String file = item.getText();
				    		  if (PathUtils.existsFile(ddr.getLocation(), file))
				    			  item.setDisable(false);
				    		  else
				    			  item.setDisable(true);
				    	  }
				      }
				    });
				// Set context menu on row, but use a binding to make it only
				// show for non-empty rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});
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
	private void handleSaveAsFileContentEvent() {
		StringBuilder ddrContent = codeEditor.getCodeAndSnapshot();
		String location = codeEditor.getEditingLocation();
		String file = codeEditor.getEditingFile();

		boolean close = true;
		try {
			close = PathUtils.saveAsDdrFile(fileChooser, mainService.getPrimaryStage(), location, file, ddrContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileContentPane.setVisible(!close);
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

	public void setMainService(MainService mainService) {
		this.mainService = mainService;

		catalogs.setItems(mainService.getCatalogs("ddrs"));
		catalogs.getSelectionModel().selectFirst();
	}

	public void setFileChooser(GuiFileChooser fileChooser) {
		this.fileChooser = fileChooser;
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
	@FXML
	private TableColumn<Ddr, String> sourceDdrColumn;

	private GuiFileChooser fileChooser;
	private MainService mainService;
}
