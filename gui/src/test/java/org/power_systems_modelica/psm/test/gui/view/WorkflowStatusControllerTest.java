package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.MainApp.WorkflowType;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.service.fx.TaskService;
import org.power_systems_modelica.psm.gui.service.fx.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.fx.DynamicTreeView;
import org.power_systems_modelica.psm.gui.utils.fx.ProgressData;
import org.power_systems_modelica.psm.gui.view.WorkflowStatusController;
import org.power_systems_modelica.psm.test.gui.WorkflowTaskFake;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.testfx.framework.junit.ApplicationTest;

import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkflowStatusControllerTest extends ApplicationTest
{
	@Override
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = null;
		try
		{
			MainService mainService = new MainService(null);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowStatus.fxml"));
			AnchorPane workflowsStatus = (AnchorPane) loader.load();

			WorkflowStatusController controller = loader.getController();
			Workflow w = WF(TD(WorkflowTaskFake.class, "fake0"),
					TD(WorkflowTaskFake.class, "fake1"),
					TD(WorkflowTaskFake.class, "fake2"),
					TD(WorkflowTaskFake.class, "fake3"),
					TD(WorkflowTaskFake.class, "fake4"));
			controller.setMainService(mainService);
			controller.setWorkflow(w, WorkflowType.CONVERSION);

			task = (WorkflowService) TaskService.createTask(w, () -> {
			});
			task.stateProperty().addListener((observableValue, oldState, newState) -> {
				if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED)
					latch.countDown();
			});
			
			Scene scene = new Scene(workflowsStatus, 1000, 800);
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testProgressView() throws InterruptedException
	{
		TaskService.startTask(task);
		latch.await();

		DynamicTreeView<ProgressData> treeView = lookup("#treeView").query();
		assertEquals(5, treeView.getItems().size());
		assertEquals("fake0", treeView.getItems().get(0).getTaskId());
		assertEquals(7, treeView.getItems().get(0).getChildren().size());
		assertEquals("Start test workflow",
				treeView.getItems().get(0).getChildren().get(0).toString());
		assertEquals("running1", treeView.getItems().get(0).getChildren().get(1).toString());
		assertEquals("running2", treeView.getItems().get(0).getChildren().get(2).toString());
		assertEquals("running3", treeView.getItems().get(0).getChildren().get(3).toString());
		assertEquals("running4", treeView.getItems().get(0).getChildren().get(4).toString());
		assertEquals("running5", treeView.getItems().get(0).getChildren().get(5).toString());
		assertEquals("End test workflow",
				treeView.getItems().get(0).getChildren().get(6).toString());
		assertEquals("fake1", treeView.getItems().get(1).getTaskId());
		assertEquals(7, treeView.getItems().get(1).getChildren().size());
		assertEquals("Start test workflow",
				treeView.getItems().get(1).getChildren().get(0).toString());
		assertEquals("running1", treeView.getItems().get(1).getChildren().get(1).toString());
		assertEquals("running2", treeView.getItems().get(1).getChildren().get(2).toString());
		assertEquals("running3", treeView.getItems().get(1).getChildren().get(3).toString());
		assertEquals("running4", treeView.getItems().get(1).getChildren().get(4).toString());
		assertEquals("running5", treeView.getItems().get(1).getChildren().get(5).toString());
		assertEquals("End test workflow",
				treeView.getItems().get(1).getChildren().get(6).toString());
		assertEquals("fake2", treeView.getItems().get(2).getTaskId());
		assertEquals(7, treeView.getItems().get(2).getChildren().size());
		assertEquals("Start test workflow",
				treeView.getItems().get(2).getChildren().get(0).toString());
		assertEquals("running1", treeView.getItems().get(2).getChildren().get(1).toString());
		assertEquals("running2", treeView.getItems().get(2).getChildren().get(2).toString());
		assertEquals("running3", treeView.getItems().get(2).getChildren().get(3).toString());
		assertEquals("running4", treeView.getItems().get(2).getChildren().get(4).toString());
		assertEquals("running5", treeView.getItems().get(2).getChildren().get(5).toString());
		assertEquals("End test workflow",
				treeView.getItems().get(2).getChildren().get(6).toString());
		assertEquals("fake3", treeView.getItems().get(3).getTaskId());
		assertEquals(7, treeView.getItems().get(3).getChildren().size());
		assertEquals("Start test workflow",
				treeView.getItems().get(3).getChildren().get(0).toString());
		assertEquals("running1", treeView.getItems().get(3).getChildren().get(1).toString());
		assertEquals("running2", treeView.getItems().get(3).getChildren().get(2).toString());
		assertEquals("running3", treeView.getItems().get(3).getChildren().get(3).toString());
		assertEquals("running4", treeView.getItems().get(3).getChildren().get(4).toString());
		assertEquals("running5", treeView.getItems().get(3).getChildren().get(5).toString());
		assertEquals("End test workflow",
				treeView.getItems().get(3).getChildren().get(6).toString());
		assertEquals("fake4", treeView.getItems().get(4).getTaskId());
		assertEquals(7, treeView.getItems().get(4).getChildren().size());
		assertEquals("Start test workflow",
				treeView.getItems().get(4).getChildren().get(0).toString());
		assertEquals("running1", treeView.getItems().get(4).getChildren().get(1).toString());
		assertEquals("running2", treeView.getItems().get(4).getChildren().get(2).toString());
		assertEquals("running3", treeView.getItems().get(4).getChildren().get(3).toString());
		assertEquals("running4", treeView.getItems().get(4).getChildren().get(4).toString());
		assertEquals("running5", treeView.getItems().get(4).getChildren().get(5).toString());
		assertEquals("End test workflow",
				treeView.getItems().get(4).getChildren().get(6).toString());
	}

	private final CountDownLatch	latch	= new CountDownLatch(1);
	private WorkflowService			task;
}
