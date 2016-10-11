package org.power_systems_modelica.psm.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		// Create the MBean
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		PsmApplication mbean = new PsmApplication(scheduledExecutorService, true);

		// Wait forever
		System.out.println("Waiting for incoming requests ...");
		Thread.sleep(Long.MAX_VALUE);
	}
}
