package org.power_systems_modelica.psm.server;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.power_systems_modelica.psm.server.message.encoder.CasesMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.CatalogMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.ConnectionMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.DdrsMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.StatusMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.StringListMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.WorkflowListMessageEncoder;
import org.power_systems_modelica.psm.server.message.encoder.WorkflowResultMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/messages/psm/workflow", encoders = { ConnectionMessageEncoder.class,
		CatalogMessageEncoder.class, CasesMessageEncoder.class, DdrsMessageEncoder.class,
		StringListMessageEncoder.class, StatusMessageEncoder.class,
		WorkflowListMessageEncoder.class, WorkflowResultMessageEncoder.class })
public class PsmApplicationEndPoint
{
	@OnOpen
	public void onOpen(Session session)
	{
		LOG.info("Session {} opened", session.getId());
		bean.getSessions().add(session);
	}

	@OnClose
	public void onClose(Session session)
	{
		LOG.info("Session {} closed", session.getId());
		bean.getSessions().remove(session);
	}

	@OnError
	public void onError(Session session, Throwable t)
	{
		LOG.error(t.toString(), t);
	}

	@Inject
	private PsmApplicationBean	bean;

	private static final Logger	LOG	= LoggerFactory.getLogger(PsmApplicationEndPoint.class);
}
