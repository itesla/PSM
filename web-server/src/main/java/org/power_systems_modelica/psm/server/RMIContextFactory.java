package org.power_systems_modelica.psm.server;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import com.sun.jndi.url.rmi.rmiURLContext;

public class RMIContextFactory implements InitialContextFactory
{
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException
	{
		return new rmiURLContext(environment);
	}
}
