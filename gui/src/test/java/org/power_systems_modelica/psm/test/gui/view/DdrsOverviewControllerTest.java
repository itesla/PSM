package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.Catalog;
import org.power_systems_modelica.psm.gui.model.Ddr;
import org.power_systems_modelica.psm.gui.service.CatalogService;
import org.power_systems_modelica.psm.gui.service.DdrService;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CodeEditor;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.view.DdrsOverviewController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DdrsOverviewControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DdrsOverview.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooserFake("models.dyd"));

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testContentDdrs() {

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());

		clickOn("#catalogs").clickOn("Reference cases");

		TableView<Catalog> catalogs = lookup("#catalogs").query();
		assertEquals(CatalogService.getCatalogs("ddrs").size(), catalogs.getItems().size());

		TableView<Ddr> ddrs = lookup("#ddrs").query();
		assertEquals(DdrService.getDdrs(catalog).size(), ddrs.getItems().size());
	}

	@Test
	public void testFileContent() {

		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());

		clickOn("#catalogs").clickOn("Reference cases");
		clickOn("#ddrs").clickOn("ieee14_ddr", MouseButton.SECONDARY).clickOn("models.dyd");
		interact(new Runnable() {

			@Override
			public void run() {
				StringBuilder ddrContent = null;
				try {
					ddrContent = PathUtils.loadFile(PathUtils.DATA_TEST.resolve("ieee14").resolve("ddr").toString(),
							"models.dyd");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				assertNotNull(ddrContent);

				CodeEditor codeEditor = lookup("#codeEditor").query();
				StringBuilder editorContent = codeEditor.getCodeAndSnapshot();
				assertEquals(ddrContent.toString(), editorContent.toString());

				ddrContent.setLength(0);
				ddrContent.append("Added new line to test editor");
				codeEditor.setCode(ddrContent);
				codeEditor.setEditingFile(System.getProperty("user.home"), "models.dyd");
				clickOn("#saveAsEditor");
				clickOn("#closeEditor");

				interact(new Runnable() {

					@Override
					public void run() {
						StringBuilder ddrContent = null;
						try {
							ddrContent = PathUtils.loadFile(System.getProperty("user.home"), "models.dyd");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						assertNotNull(ddrContent);
		
						assertEquals("Added new line to test editor\n", ddrContent.toString());
					}
				});
			}
		});
	}

	private DdrsOverviewController controller;
}