package org.power_systems_modelica.psm.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;

import org.power_systems_modelica.psm.app.IPsmApplication;
import org.power_systems_modelica.psm.app.PsmApplicationListener;
import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.server.message.ConnectionMessage;
import org.power_systems_modelica.psm.server.message.StatusMessage;
import org.power_systems_modelica.psm.server.message.WorkflowListMessage;
import org.power_systems_modelica.psm.server.util.WebSocketSessions;
import org.power_systems_modelica.psm.workflow.StatusSynthesis;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.WorkflowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class PsmApplicationBean
{
	WebSocketSessions getSessions()
	{
		return sessions;
	}

	@PostConstruct
	void init()
	{
		LOG.info("Initializing psm workflow");
		try
		{
			WorkflowParameters conf = WorkflowParameters.loadDefault();
			application = new RemotePsmApplication(conf);
			PsmApplicationListener appListener = new ThisPsmApplicationListener();
			application.addListener(appListener);
		}
		catch (IOException | MalformedObjectNameException | InstanceNotFoundException e)
		{
			remoteConnected = false;
			LOG.error(" PSMApplicationBean error th runtime Exception " + e.getMessage());
		}
	}

	@PreDestroy
	void terminate()
	{
		LOG.info("Terminating psm workflow");
		sessions.close();
	}

	public WorkflowParameters create()
	{
		String id = application.create();
		return application.getWorkflow(id);
	}

	void start(WorkflowParameters p)
	{
		if (!remoteConnected) return;
		executorService.submit(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					LOG.info("START WORKFLOW " + p.getCreated());
					application.startWorkflow(p);
				}
				catch (Throwable e)
				{
					LOG.error(e.toString(), e);
				}
			}
		});
	}

	void cancel(String id)
	{
		LOG.info("CANCEL WORKFLOW " + id);
		if (application != null) application.removeWorkflow(id);
	}

	void stop(String id)
	{
		LOG.info("STOP WORKFLOW " + id);
		if (!remoteConnected) return;

		if (application != null)
		{
			application.removeWorkflow(id);
			application.stopWorkflow();
		}
	}

	public List<Catalog> getCatalogs()
	{
		if (!remoteConnected) return null;
		return application.getCatalogs();
	}

	public List<Case> getCases(String catalogName)
	{
		if (!remoteConnected) return null;
		return application.getCases(catalogName);
	}

	public List<Ddr> getDDRs(String catalogName)
	{
		if (!remoteConnected) return null;
		return application.getDdrs(catalogName);
	}

	public List<String> getNameCatalogs()
	{
		if (!remoteConnected) return null;
		List<String> catalogs = application.getCatalogs()
				.stream()
				.map(Catalog::getName)
				.collect(Collectors.toList());
		return catalogs;
	}

	public List<String> getNameCases(String catalogName)
	{
		if (!remoteConnected) return null;
		List<String> cases = application.getCases(catalogName)
				.stream()
				.map(Case::getName)
				.collect(Collectors.toList());
		return cases;
	}

	public List<String> getNameDdrs(String catalogName)
	{
		if (!remoteConnected) return null;
		List<String> ddrs = application.getDdrs(catalogName)
				.stream()
				.map(Ddr::getName)
				.collect(Collectors.toList());
		return ddrs;
	}

	public HashMap<String, WorkflowInfo> getWorkFlows()
	{
		return workflows;
	}

	public int getWorkflowStatus(String workflowId)
	{
		return workflows.get(workflowId).getStatus();
	}

	public List<WorkflowResult> getWorkflowResult(String workflowId)
	{
		return application.getWorkflowResult(workflowId);
	}

	public class ThisPsmApplicationListener implements PsmApplicationListener
	{
		@Override
		public void onDisconnection()
		{
			remoteConnected = false;
			LOG.warn("JMX Disconnected");
			sessions.send(new ConnectionMessage(false));
		}

		@Override
		public void onConnection()
		{
			remoteConnected = true;
			LOG.info("JMX Connected");
			sessions.send(new ConnectionMessage(true));
		}

		@Override
		public void onWorkflowUpdate(StatusSynthesis status)
		{
			WorkflowInfo wi = workflows.get(status.getWorkflowId());
			if (wi == null)
			{
				wi = new WorkflowInfo(status.getWorkflowId());
				workflows.put(status.getWorkflowId(), wi);
				sessions.send(new WorkflowListMessage(workflows));
			}
			wi.setStatus(status.getStatus());
			sessions.send(new StatusMessage(status));
		}
	};

	private final WebSocketSessions			sessions		= new WebSocketSessions();
	@Resource
	private ManagedExecutorService			executorService;
	private IPsmApplication					application;
	private boolean							remoteConnected	= false;
	private HashMap<String, WorkflowInfo>	workflows		= new HashMap<String, WorkflowInfo>();

	private static final Logger				LOG				= LoggerFactory
			.getLogger(PsmApplicationBean.class);

}