package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaModel;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.VoltageLevel;

public class IidmReferenceResolver implements ReferenceResolver
{
	public IidmReferenceResolver(Network network)
	{
		this.network = network;
	}

	@Override
	public Object resolveReference(String name, ModelicaModel m)
	{
		String staticId = m.getStaticId();
		Identifiable<?> element = network.getIdentifiable(staticId);
		if (element == null) element = getFromCompoundId(staticId);
		if (element == null) return null;
		Object value = fromKnownProperty(element, name);
		if (value == null) value = ReflectionUtils.getPropertyValue(element, name);
		return value;
	}

	private Identifiable<?> getFromCompoundId(String staticId)
	{
		// We allow references to computed buses
		// This is related to which view we are using for Network buses when building the model
		// References to buses may be qualified using voltageLevel
		// And the id of the bus may be found in the BusBreakerView or the BusView
		// When these buses are calculated they do not appear in the list of identifiable objects of the network
		String[] ids = staticId.split(COMPOUND_ID_SEPARATOR);
		if (ids.length == 2)
		{
			String vlId = ids[0];
			String busId = ids[1];
			VoltageLevel vl = network.getVoltageLevel(vlId);
			if (vl != null)
			{
				Bus b = vl.getBusBreakerView().getBus(busId);
				if (b == null) b = vl.getBusView().getBus(busId);
				return b;
			}
		}
		return null;
	}

	private Object fromKnownProperty(Identifiable<?> element, String name)
	{
		if (element instanceof Bus)
		{
			Bus b = (Bus) element;
			switch (name)
			{
			case "pu(V)":
				return b.getV() / b.getVoltageLevel().getNominalV();
			case "rad(angle)":
				return Math.toRadians(b.getAngle());
			case "V":
				return b.getV();
			case "angle":
				return b.getAngle();
			}
		}
		return null;
	}

	private final Network		network;

	private static final String	COMPOUND_ID_SEPARATOR	= "::";
}
