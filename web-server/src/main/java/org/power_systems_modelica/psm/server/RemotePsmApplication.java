package org.power_systems_modelica.psm.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.power_systems_modelica.psm.app.IPsmApplication;
import org.power_systems_modelica.psm.app.PsmApplicationListener;
import org.power_systems_modelica.psm.app.PsmApplicationMBean;
import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.workflow.StatusSynthesis;
import org.power_systems_modelica.psm.workflow.Workflow;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.WorkflowResult;
import org.power_systems_modelica.psm.workflow.WorkflowResultItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemotePsmApplication implements IPsmApplication
{

	public RemotePsmApplication(WorkflowParameters conf)
			throws IOException, MalformedObjectNameException, InstanceNotFoundException
	{
		String host = conf.getJmxHost();
		String port = conf.getJmxPort();

		String urlString = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
		LOG.info("JMX url  " + urlString);
		serviceURL = new JMXServiceURL(urlString);
		jmxEnv = new HashMap<>();

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable()
		{
			public void run()
			{
				try
				{
					application.ping();
				}
				catch (Exception ex)
				{
					try
					{
						notifyDisconnection();
						connect();
					}
					catch (Throwable t)
					{
					}
				}

			}
		}, 2, 10, TimeUnit.SECONDS);
	}

	private void connect()
			throws IOException, MalformedObjectNameException, InstanceNotFoundException
	{
		try
		{
			this.connector = JMXConnectorFactory.connect(serviceURL, jmxEnv);
			mbsc = connector.getMBeanServerConnection();

			ObjectName name = new ObjectName(PsmApplicationMBean.BEAN_NAME);
			application = MBeanServerInvocationHandler.newProxyInstance(
					mbsc,
					name,
					PsmApplicationMBean.class,
					false);
			connected = true;
			for (PsmApplicationListener l : listeners)
			{
				l.onConnection();
			}
		}
		catch (Exception ex)
		{
			// ex.printStackTrace();
			LOG.error("Exception connecting JMX " + ex);
		}
	}

	private void notifyDisconnection()
	{
		if (connected)
		{
			connected = false;
			for (PsmApplicationListener l : listeners)
			{
				l.onDisconnection();
			}
		}
	}

	@Override
	public void close() throws Exception
	{
		connector.close();
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
		WorkflowParameters p = WorkflowParameters.loadDefault();
		String key = p.getCreated();
		parameters.put(key, p);
		return key;
	}

	@Override
	public WorkflowParameters getWorkflow(String key)
	{
		return parameters.get(key);
	}

	@Override
	public void removeWorkflow(String key)
	{
		LOG.info("" + parameters.size());
		parameters.remove(key);
		LOG.info("" + parameters.size());
	}

	@Override
	public void startWorkflow(WorkflowParameters conf)
	{
		try
		{
			LOG.info("" + parameters.size());
			application.startWorkflow(conf);

			double n = rnd.nextDouble();
			LOG.info("Random " + n);
			int status = (int) Math.round(n * 3.0);
			LOG.info("status " + status);
			for (PsmApplicationListener l : listeners)
			{
				l.onWorkflowUpdate(new StatusSynthesis(conf.getCreated(), status));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			notifyDisconnection();
		}
	}

	@Override
	public void stopWorkflow()
	{
		try
		{
			application.stopWorkflow();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			notifyDisconnection();
		}
	}

	@Override
	public List<Workflow> getWorkflowStates()
	{
		return application.getWorkflowStates();
	}

	@Override
	public List<Catalog> getCatalogs()
	{
		return application.getCatalogs();
	}

	@Override
	public List<Case> getCases(String catalogName)
	{
		return application.getCases(catalogName);
	}

	@Override
	public List<Ddr> getDdrs(String catalogName)
	{
		return application.getDdrs(catalogName);
	}

	@Override
	public List<WorkflowResult> getWorkflowResult(String workflowId)
	{
		List<WorkflowResult> results = new CopyOnWriteArrayList<>();

		WorkflowResult r;
		int i = 0;
		while (i < 25)
		{
			r = new WorkflowResult();
			r.setId("" + i);
			r.setWorkflow(workflowId);

			int j = 0;
			List<WorkflowResultItem> items = new CopyOnWriteArrayList<>();
			WorkflowResultItem it;
			while (j < 25)
			{
				it = new WorkflowResultItem();
				it.setX(String.format("%03d", j));
				it.setY(((j + rnd.nextDouble()) + ((j + rnd.nextDouble()) * (j + rnd.nextDouble())))
						/ 600.0);
				items.add(it);
				j++;
			}
			r.setResult(items);
			results.add(r);
			i++;
		}

		return results;
	}

	private JMXConnector						connector;
	private MBeanServerConnection				mbsc;
	private JMXServiceURL						serviceURL;
	Map<String, String>							jmxEnv;
	private PsmApplicationMBean					application;
	private boolean								connected;
	private final List<PsmApplicationListener>	listeners	= new CopyOnWriteArrayList<>();
	private HashMap<String, WorkflowParameters>	parameters	= new HashMap<String, WorkflowParameters>();
	Random										rnd			= new Random();

	private static final Logger					LOG			= LoggerFactory
			.getLogger(RemotePsmApplication.class);
}
