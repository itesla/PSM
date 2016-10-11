package org.power_systems_modelica.psm.server.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.Session;

import org.power_systems_modelica.psm.server.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketSessions
{
	public synchronized void add(Session session)
	{
		LOG.info("session idle timeout {}", session.getMaxIdleTimeout());
		sessions.add(session);
	}

	public synchronized void remove(Session session)
	{
		sessions.remove(session);
	}

	public synchronized <T> void send(Message<T> message)
	{
		for (Iterator<Session> it = sessions.iterator(); it.hasNext();)
		{
			Session session = it.next();
			if (session.isOpen())
			{
				try
				{
					session.getBasicRemote().sendObject(message);
				}
				catch (Exception e)
				{
					LOG.error(e.toString(), e);
				}
			}
			else
			{
				it.remove();
			}
		}
	}

	public synchronized void close()
	{
		for (Session session : sessions)
		{
			try
			{
				session.close();
			}
			catch (Exception e)
			{
				LOG.error(e.toString(), e);
			}
		}
	}

	private final Set<Session>	sessions	= new HashSet<Session>();

	private static final Logger	LOG			= LoggerFactory.getLogger(WebSocketSessions.class);
}
