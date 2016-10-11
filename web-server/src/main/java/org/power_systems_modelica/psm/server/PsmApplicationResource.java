package org.power_systems_modelica.psm.server;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.power_systems_modelica.psm.dao.Case;
import org.power_systems_modelica.psm.dao.Catalog;
import org.power_systems_modelica.psm.dao.Ddr;
import org.power_systems_modelica.psm.dao.Event;
import org.power_systems_modelica.psm.server.message.CasesMessage;
import org.power_systems_modelica.psm.server.message.CasesSynthesis;
import org.power_systems_modelica.psm.server.message.CatalogMessage;
import org.power_systems_modelica.psm.server.message.CatalogSynthesis;
import org.power_systems_modelica.psm.server.message.DdrsMessage;
import org.power_systems_modelica.psm.server.message.DdrsSynthesis;
import org.power_systems_modelica.psm.server.message.StringListMessage;
import org.power_systems_modelica.psm.server.message.StringListSynthesis;
import org.power_systems_modelica.psm.server.message.WorkflowListMessage;
import org.power_systems_modelica.psm.server.message.WorkflowParametersMessage;
import org.power_systems_modelica.psm.server.message.WorkflowResultMessage;
import org.power_systems_modelica.psm.server.message.WorkflowResultSynthesis;
import org.power_systems_modelica.psm.workflow.WorkflowParameters;
import org.power_systems_modelica.psm.workflow.WorkflowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/psm/workflow")
@NoCache
public class PsmApplicationResource
{
	public PsmApplicationResource()
	{
	}

	@POST
	@Path("start")
	@Produces(MediaType.TEXT_PLAIN)
	public void start(JsonObject paramsObj)
	{
		LOG.info(paramsObj.toString());
		WorkflowParameters params = new WorkflowParameters();
		params.setCreated(paramsObj.getJsonString("created").getString());
		params.setCatalogName(paramsObj.getJsonString("catalogName").getString());
		params.setCaseName(paramsObj.getJsonString("caseName").getString());
		params.setDdrName(paramsObj.getJsonString("ddrName").getString());
		params.setEngineLoadflow(paramsObj.getJsonNumber("engineLoadflow").intValue());
		params.setMainConnectedComponentOnly(
				Boolean.parseBoolean(paramsObj.get("mainConnectedComponentOnly").toString()));
		params.setEngineDS(paramsObj.getJsonNumber("engineDS").intValue());

		List<Event> events = new CopyOnWriteArrayList<>();

		JsonArray jsonEvents = paramsObj.getJsonArray("events");
		for (Object o : jsonEvents)
		{
			JsonObject jsonEvent = (JsonObject) o;
			int action = Integer.parseInt(jsonEvent.getString("action"));
			String element = jsonEvent.getString("element");

			Event event = new Event();
			event.setAction(action);
			event.setElement(element);

			events.add(event);
		}
		params.setEvents(events);

		LOG.info("start " + params.toString());
		bean.start(params);
	}

	@POST
	@Path("cancel")
	@Produces(MediaType.TEXT_PLAIN)
	public void cancel(JsonObject paramsObj)
	{
		LOG.info("cancel");
		bean.cancel(paramsObj.getJsonString("id").getString());
	}

	@POST
	@Path("stop")
	@Produces(MediaType.TEXT_PLAIN)
	public void stop(JsonObject paramsObj)
	{
		LOG.info("stop");
		bean.stop(paramsObj.getJsonString("id").getString());
	}

	@POST
	@Path("notifyListeners")
	public void notifyListeners()
	{
		LOG.info("notifyListeners");
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("currentUser")
	public String getCurrentUser()
	{
		LOG.info("getCurrentUser");
		String user = "";
		if (securityContext != null && securityContext.getUserPrincipal() != null)
			user = securityContext.getUserPrincipal().getName();
		return user;
	}

	@POST
	@Path("logout")
	public void logout(@Context HttpServletRequest req)
	{
		try
		{
			req.logout();
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}

		HttpSession session = req.getSession();
		if (session != null)
		{
			try
			{
				session.invalidate();
			}
			catch (Exception ex)
			{
			}
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("create")
	public String create()
	{
		LOG.info("create");
		WorkflowParameters params = bean.create();
		WorkflowParametersMessage message = new WorkflowParametersMessage(params);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("catalogs")
	public String getCatalogs()
	{
		LOG.info("getCatalogs");
		List<Catalog> catalogs = bean.getCatalogs();
		CatalogSynthesis synthesis = new CatalogSynthesis(catalogs);
		CatalogMessage message = new CatalogMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("case/{catalogName}")
	public String getCases(@PathParam("catalogName") String catalogName)
	{
		LOG.info("getCases " + catalogName);
		List<Case> cases = bean.getCases(catalogName);
		CasesSynthesis synthesis = new CasesSynthesis(cases);
		CasesMessage message = new CasesMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ddrs/{catalogName}")
	public String getDDRs(@PathParam("catalogName") String catalogName)
	{
		LOG.info("getDDRs");
		List<Ddr> ddrs = bean.getDDRs(catalogName);
		DdrsSynthesis synthesis = new DdrsSynthesis(ddrs);
		DdrsMessage message = new DdrsMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("namecatalogs")
	public String getNameCatalogs()
	{
		LOG.info("getCatalogs");
		List<String> catalogs = bean.getNameCatalogs();
		StringListSynthesis synthesis = new StringListSynthesis(catalogs);
		StringListMessage message = new StringListMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("namecase/{catalogName}")
	public String getNameCases(@PathParam("catalogName") String catalogName)
	{
		LOG.info("getCases " + catalogName);
		List<String> cases = bean.getNameCases(catalogName);
		StringListSynthesis synthesis = new StringListSynthesis(cases);
		StringListMessage message = new StringListMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("nameddrs/{catalogName}")
	public String getNameDDRs(@PathParam("catalogName") String catalogName)
	{
		LOG.info("getDDRs");
		List<String> ddrs = bean.getNameDdrs(catalogName);
		StringListSynthesis synthesis = new StringListSynthesis(ddrs);
		StringListMessage message = new StringListMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("workflows")
	public String getWorkflows()
	{
		LOG.info("getWorkflows");
		String res = null;
		HashMap<String, WorkflowInfo> workflows = bean.getWorkFlows();
		if (workflows != null)
		{
			WorkflowListMessage msg = new WorkflowListMessage(workflows);
			res = msg.toJson();
		}
		return res;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("status/{workflowId}")
	public int getStatus(@PathParam("workflowId") String workflowId)
	{
		LOG.info("getStatus");
		int res = bean.getWorkflowStatus(workflowId);
		return res;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("results/{workflowId}")
	public String getResult(@PathParam("workflowId") String workflowId)
	{
		LOG.info("getResult");
		List<WorkflowResult> results = bean.getWorkflowResult(workflowId);
		WorkflowResultSynthesis synthesis = new WorkflowResultSynthesis(results);
		WorkflowResultMessage message = new WorkflowResultMessage(synthesis);
		String res = message.toJson();
		return res;
	}

	@Inject
	private PsmApplicationBean	bean;

	@Context
	SecurityContext				securityContext;

	private static final Logger	LOG	= LoggerFactory.getLogger(PsmApplicationResource.class);
}
