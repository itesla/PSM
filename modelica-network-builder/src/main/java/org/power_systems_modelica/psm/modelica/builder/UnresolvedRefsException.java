package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;

@SuppressWarnings("serial")
public class UnresolvedRefsException extends Exception
{
	public UnresolvedRefsException(Collection<UnresolvedRef> unresolved)
	{
		this.unresolved = unresolved;
	}

	public Collection<UnresolvedRef> getUnresolved()
	{
		return unresolved;
	}

	private final Collection<UnresolvedRef> unresolved;
}
