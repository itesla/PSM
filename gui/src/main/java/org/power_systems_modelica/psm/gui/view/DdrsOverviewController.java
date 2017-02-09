package org.power_systems_modelica.psm.gui.view;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.ddr.dyd.ModelMapping;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.gui.model.Case;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.model.Ddr.DdrType;
import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.GuiFileChooser;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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

public class DdrsOverviewController implements MainChildrenController
{

	@Override
	public void handleMainAction()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMenuAction(String action)
	{

	}

	@Override
	public String getMainAction()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMenuActions()
	{
		// TODO Auto-generated method stub
		return null;
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

	@FXML
	private void initialize()
	{

		fileContentPane.setVisible(false);
		Utils.setDragablePane(fileContentPane);

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
						ddrs.setItems(mainService.getDdrs(catalogName));
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

		MenuItem menuItem = new MenuItem("Duplicate DDR");
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
		
		Path ddrPath = Paths.get(ddr.getLocation());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(ddrPath))
		{

			SeparatorMenuItem sMenuItem = new SeparatorMenuItem();

			for (Path entry : stream)
			{
				if (entry.toString().endsWith(".dyd") || entry.toString().endsWith(".par"))
				{
					if (sMenuItem != null)
					{
						contextMenu.getItems().add(sMenuItem);
						sMenuItem = null;
					}
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
		}
		catch (IOException e1)
		{
		}
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
		Map<String, String> xmlMapping = mainService.checkXml(ddr.getLocation());
		Map<String, ModelMapping> modelMapping = mainService.checkDuplicates(ddr.getLocation());
		
		StringBuilder ddrDuplicates = new StringBuilder();
		
		for (String key: modelMapping.keySet())
		{
			if (modelMapping.get(key).isDuplicated())
				ddrDuplicates.append("\t - " + modelMapping.get(key).toString() + "\n");
		}

		StringBuilder ddrXml = new StringBuilder();
		
		for (String key: xmlMapping.keySet())
		{
			ddrXml.append(key + " - " + xmlMapping.get(key) + "\n");
		}

		StringBuilder ddrContent = new StringBuilder();
		
		if (ddrXml.length() > 0) 
		{
			ddrContent.append("Xml files with errors:\n");
			ddrContent.append(ddrXml);
		}
		if (ddrDuplicates.length() > 0)
		{
			ddrContent.append("Duplicated model mappings:\n");
			ddrContent.append(ddrDuplicates);
		}
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
		String outputPath = PathUtils.directoryOutput(mainService.getPrimaryStage(),
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
		if (mainService.duplicateDdr(ddr, ddrOut))
		{
			Catalog catalog = catalogs.getSelectionModel().getSelectedItem();
			ddrs.setItems(mainService.getDdrs(catalog.getName()));
		}
	}

	@Override
	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;

		catalogs.setItems(mainService.getCatalogs("ddrs"));
		catalogs.getSelectionModel().selectFirst();
	}

	@Override
	public void setFileChooser(GuiFileChooser fileChooser)
	{
	}

	@Override
	public void setDefaultInit()
	{
	}

	@Override
	public void setWorkflow(Workflow w, Object... objects)
	{
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

	private MainService						mainService;

}
