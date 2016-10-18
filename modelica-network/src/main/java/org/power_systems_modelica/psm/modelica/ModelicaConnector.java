package org.power_systems_modelica.psm.modelica;

import java.util.Objects;
import java.util.Optional;

public class ModelicaConnector
{
	// Represents a global pin in the system where others can connect (omegaRef)
	public ModelicaConnector(String pin)
	{
		Objects.requireNonNull(pin);
		this.id = Optional.empty();
		this.pin = pin;
		this.target = Optional.empty();
		ref = pin;
	}

	// A pin inside a dynamic model identified by id
	// That optionally contains information about other connector it wants to be connected
	// The target is composed of a dataSource, an item and a pin id,
	// The target will be resolved when building the system dynamic model
	public ModelicaConnector(String id, String pin, String target)
	{
		Objects.requireNonNull(id);
		Objects.requireNonNull(pin);
		this.id = Optional.of(id);
		this.pin = pin;
		this.target = Optional.ofNullable(target);
		ref = id.concat(".").concat(pin);
	}

	public Optional<String> getId()
	{
		return id;
	}

	public String getPin()
	{
		return pin;
	}

	public String getRef()
	{
		return ref;
	}

	public Optional<String> getTarget()
	{
		return target;
	}

	public ModelicaConnector setStaticId(String staticId)
	{
		this.staticId = staticId;
		return this;
	}

	public String getStaticId()
	{
		return staticId;
	}

	private final Optional<String>	id;
	private final String			pin;
	private final String			ref;
	private final Optional<String>	target;

	private String					staticId;
}
