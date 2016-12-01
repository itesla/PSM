package org.power_systems_modelica.psm.test.gui.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.power_systems_modelica.psm.workflow.Workflow.TD;
import static org.power_systems_modelica.psm.workflow.Workflow.WF;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;
import org.power_systems_modelica.psm.gui.service.TaskService;
import org.power_systems_modelica.psm.gui.service.WorkflowService;
import org.power_systems_modelica.psm.gui.utils.ProgressData;
import org.power_systems_modelica.psm.test.gui.WorkflowTaskFake;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowCreationException;

import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;

public class WorkflowTest {

	 @BeforeClass
     public static void initToolkit() throws InterruptedException
     {
         final CountDownLatch latch = new CountDownLatch(1);
         SwingUtilities.invokeLater(() -> {
             new JFXPanel(); // initializes JavaFX environment
             latch.countDown();
         });

         if (!latch.await(5L, TimeUnit.SECONDS))
             throw new ExceptionInInitializerError();
     }
	 
	 @Test
	public void testWorkflowProgressMessages() throws WorkflowCreationException, InterruptedException {
		
        final CountDownLatch latch = new CountDownLatch(1);
		Workflow w = WF(TD(WorkflowTaskFake.class, "fake0"));
		
		WorkflowService task = (WorkflowService) TaskService.createTask(w, () -> {});
		task.stateProperty().addListener((observableValue, oldState, newState) -> {

			if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED)
				latch.countDown();
		});
		TaskService.startTask(task);    
	    latch.await();
	    
	    ObservableList<ProgressData> messages = task.getWorkflowInfo();
		assertNotNull(messages);
		assertEquals(1, messages.size());
		assertEquals("fake0               Workflow Task Test                                          SUCCESS", messages.get(0).toString());
		ObservableList<ProgressData> messageChildrens = messages.get(0).getChildren();
		assertEquals(3, messageChildrens.size());
		assertEquals("Start test workflow", messageChildrens.get(0).toString());
		assertEquals("Succeded test workflow", messageChildrens.get(1).toString());
		assertEquals("End test workflow", messageChildrens.get(2).toString());
	}

}
