package org.power_systems_modelica.psm.app;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.WorkflowResult;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PsmApplication implements IPsmApplication, PsmApplicationMBean
{
	public PsmApplication(
			ScheduledExecutorService ses,
			boolean enableJmx)
			throws InstanceAlreadyExistsException,
			MBeanRegistrationException,
			NotCompliantMBeanException,
			MalformedObjectNameException
	{
		init();

		this.enableJmx = enableJmx;
		if (enableJmx)
		{
			// create and register online application mbean
			ManagementFactory
					.getPlatformMBeanServer()
					.registerMBean(this, new ObjectName(BEAN_NAME));
		}
		future = ses.scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					// System.out.println("scheduled run");
				}
				catch (Throwable t)
				{
					LOGGER.error(t.toString(), t);
				}
			}
		}, 0, 20, TimeUnit.SECONDS);
	}

	private void init()
	{
	}

	@Override
	public void close() throws Exception
	{
		System.out.println("Closing ...");
		future.cancel(true);
		if (enableJmx)
		{
			// unregister application mbean
			ManagementFactory.getPlatformMBeanServer().unregisterMBean(new ObjectName(BEAN_NAME));
		}
		synchronized (this)
		{
			this.notifyAll();
		}
	}

	@Override
	public void ping()
	{
		// System.out.println("ping received");
	}

	@Override
	public void addListener(PsmApplicationListener l)
	{
		listeners.add(l);
	}

	@Override
	public void removeListener(PsmApplicationListener l)
	{
		listeners.remove(l);
	}

	@Override
	public String create()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startWorkflow(WorkflowParameters conf)
	{
		// TODO Auto-generated method stub
		System.out.println("startWorkflow");
		System.out.println("catalog " + conf.getCatalogName());
		System.out.println("case " + conf.getCaseName());
		System.out.println("ddr " + conf.getDdrName());
	}

	@Override
	public void stopWorkflow()
	{
		// TODO Auto-generated method stub

	}

	public List<Workflow> getWorkflowStates()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Catalog> getCatalogs()
	{
		System.out.println("getCatalogs");
		List<Catalog> catalogs = new ArrayList<Catalog>();

		Catalog catalog = new Catalog();
		catalog.setName("sampleA");
		catalogs.add(catalog);

		catalog = new Catalog();
		catalog.setName("sampleB");
		catalogs.add(catalog);

		return catalogs;
	}

	@Override
	public List<Case> getCases(String catalogName)
	{
		System.out.println("getCases " + catalogName);
		List<Case> cases = new ArrayList<Case>();

		if (catalogName.equals("sampleA"))
		{
			Case c = new Case();
			c.setName("caseA1");
			cases.add(c);

			c = new Case();
			c.setName("caseA2");
			cases.add(c);

			c = new Case();
			c.setName("caseA3");
			cases.add(c);

			c = new Case();
			c.setName("caseA4");
			cases.add(c);
		}
		else
		{
			Case c = new Case();
			c.setName("caseB1");
			cases.add(c);

			c = new Case();
			c.setName("caseB2");
			cases.add(c);

			c = new Case();
			c.setName("caseB3");
			cases.add(c);

			c = new Case();
			c.setName("caseB4");
			cases.add(c);
		}

		return cases;
	}

	@Override
	public List<Ddr> getDdrs(String catalogName)
	{
		List<Ddr> ddrs = new ArrayList<Ddr>();
		if (catalogName.equals("sampleA"))
		{

			Ddr ddr = new Ddr();
			ddr.setName("sampleA");
			ddrs.add(ddr);
		}
		else
		{

			Ddr ddr = new Ddr();
			ddr.setName("sampleB");
			ddrs.add(ddr);
		}

		return ddrs;
	}

	@Override
	public WorkflowParameters getWorkflow(String id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeWorkflow(String id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<WorkflowResult> getWorkflowResult(String workflowId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private final boolean						enableJmx;
	private final ScheduledFuture<?>			future;
	private final List<PsmApplicationListener>	listeners	= new CopyOnWriteArrayList<>();

	private static final Logger					LOGGER		= LoggerFactory
			.getLogger(PsmApplication.class);
}