package org.power_systems_modelica.psm.workflow;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.DateTime;
import org.power_systems_modelica.psm.config.ModuleConfig;
import org.power_systems_modelica.psm.config.PlatformConfig;
import org.power_systems_modelica.psm.dao.Event;

public class WorkflowParameters implements Serializable
{

	private String		created;

	private int			threads;
	private String		jmxHost;
	private String		jmxPort;

	// Description
	private String		catalogName	= "";
	private String		caseName	= "";
	private String		ddrName		= "";

	// Loadflow
	private int			engineLoadflow;

	// Modelica network
	private boolean		mainConnectedComponentOnly;

	// Events
	private List<Event>	events		= new CopyOnWriteArrayList<>();

	// Dynamic simulation
	private int			engineDS;

	public static WorkflowParameters loadDefault()
	{
		int threads = -1;
		String jmxHost = "127.0.0.1";
		String jmxPort = "9010";

		ModuleConfig config = PlatformConfig.defaultConfig()
				.getModuleConfigIfExists("appserver-parameters");
		if (config != null)
		{
			threads = config.getIntProperty("threads", threads);
			jmxHost = config.getStringProperty("jmxHost", jmxHost);
			jmxPort = config.getStringProperty("jmxPort", jmxPort);
		}

		String created = DateTime.now().toString();

		return new WorkflowParameters(created, threads, jmxHost, jmxPort);
	}

	public WorkflowParameters()
	{
	}

	public WorkflowParameters(String created, int threads, String jmxHost, String jmxPort)
	{
		this.created = created;
		this.threads = threads;
		this.jmxHost = jmxHost;
		this.jmxPort = jmxPort;
	}

	public String getCreated()
	{
		return created;
	}

	public void setCreated(String created)
	{
		this.created = created;
	}

	public int getThreads()
	{
		return threads;
	}

	public String getJmxHost()
	{
		return jmxHost;
	}

	public String getJmxPort()
	{
		return jmxPort;
	}

	public String getCatalogName()
	{
		return catalogName;
	}

	public void setCatalogName(String catalogName)
	{
		this.catalogName = catalogName;
	}

	public String getCaseName()
	{
		return caseName;
	}

	public void setCaseName(String caseName)
	{
		this.caseName = caseName;
	}

	public String getDdrName()
	{
		return ddrName;
	}

	public void setDdrName(String ddrName)
	{
		this.ddrName = ddrName;
	}

	public int getEngineLoadflow()
	{
		return engineLoadflow;
	}

	public void setEngineLoadflow(int engineLoadflow)
	{
		this.engineLoadflow = engineLoadflow;
	}

	public boolean isMainConnectedComponentOnly()
	{
		return mainConnectedComponentOnly;
	}

	public void setMainConnectedComponentOnly(boolean mainConnectedComponentOnly)
	{
		this.mainConnectedComponentOnly = mainConnectedComponentOnly;
	}

	public List<Event> getEvents()
	{
		return events;
	}

	public void setEvents(List<Event> events)
	{
		this.events = events;
	}

	public int getEngineDS()
	{
		return engineDS;
	}

	public void setEngineDS(int engineDS)
	{
		this.engineDS = engineDS;
	}

	@Override
	public String toString()
	{

		String txt = "Created: " + created;
		txt += ", ";
		txt += "Catalog: " + catalogName;
		txt += ", ";
		txt += "Case: " + caseName;
		txt += ", ";
		txt += "DDR: " + ddrName;
		txt += ", ";
		txt += "Loadflow Engine: " + (engineLoadflow == 0 ? "HADES" : "HELMFlow");
		txt += ", ";
		txt += "Only main connected component: " + (mainConnectedComponentOnly ? "YES" : "NO");
		txt += ", ";
		txt += "Dynamic Simulation Engine: " + (engineDS == 0 ? "DYMOLA" : "OPENMODELICA");
		txt += ", ";
		txt += "Events: " + events.toString();

		return txt;
	}

}
