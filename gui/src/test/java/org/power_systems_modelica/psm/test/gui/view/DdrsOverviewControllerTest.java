package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

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
import org.testfx.framework.junit.ApplicationTest;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
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

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();
			latch.countDown();

			CodeEditor codeEditor = lookup("#codeEditor").query();
			WebView webView = codeEditor.getWebView();
			webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

				@Override
				public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
					if (newValue == Worker.State.SUCCEEDED) {
						latch2.countDown();
					}
				}
				
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testContentDdrs() throws InterruptedException {

		latch.await();

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
	public void testFileContent() throws InterruptedException {

		latch.await();
		
		Catalog catalog = new Catalog();
		catalog.setName("Reference cases");
		catalog.setLocation(PathUtils.DATA_TEST.toString());

		clickOn("#catalogs").clickOn("Reference cases");
		clickOn("#ddrs").clickOn("ieee14_ddr", MouseButton.SECONDARY).clickOn("models.dyd");
		latch2.await();
		
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
				latch2 = new CountDownLatch(1);
				codeEditor.setCode(ddrContent);
				codeEditor.setEditingFile(System.getProperty("user.home"), "models.dyd");
			}
		});
		
		latch2.await();
		clickOn("#saveEditor");
		
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

	private final CountDownLatch latch = new CountDownLatch(1);
	private CountDownLatch latch2 = new CountDownLatch(1);
	private DdrsOverviewController controller;
}
