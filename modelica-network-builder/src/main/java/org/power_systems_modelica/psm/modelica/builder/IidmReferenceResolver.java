package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.modelica.ModelicaModel;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;
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

	// TODO consider if the dictionary of known properties should be placed somewhere in dyd:
	// allowing to check that references read are valid
	// and having a place where "all" potential references that can be made are "documented"
	// Something like:
	// For external references to IIDM:
	// For elements of type Bus: V, angle, pu(V), rad(angle)
	// For elements of type Line: R, X, G, B
	// ...

	private Object fromKnownProperty(Identifiable<?> element, String name)
	{
		if (element instanceof Bus)
		{
			Bus b = (Bus) element;
			switch (name)
			{
			case "pu(V)":
				float V = Float.NaN;
				if (!Float.isNaN(b.getV())) V = b.getV() / b.getVoltageLevel().getNominalV();
				return V;
			case "rad(angle)":
				float angle = Float.NaN;
				if (!Float.isNaN(b.getAngle())) angle = (float) Math.toRadians(b.getAngle());
				return angle;
			case "V":
				return b.getV();
			case "angle":
				return b.getAngle();
			}
		}
		else if (element instanceof Load)
		{
			Load l = (Load) element;
			Bus b = l.getTerminal().getBusView().getBus();
			switch (name)
			{
			case "pu(V)":
				float V = Float.NaN;
				if (b != null && !Float.isNaN(b.getV()))
					V = b.getV() / l.getTerminal().getVoltageLevel().getNominalV();
				return V;
			case "rad(angle)":
				float angle = Float.NaN;
				if (b != null && !Float.isNaN(b.getAngle()))
					angle = (float) Math.toRadians(b.getAngle());
				return angle;
			// P0 and Q0 references should be solved using reflection ...
			// case "P0":
			// return l.getP0();
			// case "Q0":
			// return l.getQ0();
			}
		}
		else if (element instanceof Line)
		{
			Line l = (Line) element;
			float nominalV = l.getTerminal2().getVoltageLevel().getNominalV();
			if (Float.isNaN(nominalV)) nominalV = 0;
			float Z = (nominalV * nominalV) / SNREF;
			switch (name)
			{
			case "pu(R)":
				return l.getR() / Z;
			case "pu(X)":
				return l.getX() / Z;
			case "pu(G1)":
				return l.getG1() / Z;
			case "pu(B1)":
				return l.getB1() / Z;
			}
		}
		else if (element instanceof TwoWindingsTransformer)
		{
			TwoWindingsTransformer tx = (TwoWindingsTransformer) element;

			// FIXME review code (take into account impedance modification of tap changers) and organize
			// Compute only data that is needed for answering the value of the queried property
			float t1NomV = tx.getTerminal1().getVoltageLevel().getNominalV();
			float t2NomV = tx.getTerminal2().getVoltageLevel().getNominalV();
			float U1nom = Float.isNaN(t1NomV) == false ? t1NomV : 0;
			float U2nom = Float.isNaN(t2NomV) == false ? t2NomV : 0;
			float V1 = Float.isNaN(tx.getRatedU1()) == false ? tx.getRatedU1() : 0;
			float V2 = Float.isNaN(tx.getRatedU2()) == false ? tx.getRatedU2() : 0;
			float Zbase = (float) Math.pow(U2nom, 2) / SNREF;

			float r = tx.getR() / Zbase;
			float x = tx.getX() / Zbase;
			float g = tx.getG() * Zbase;
			float b = tx.getB() * Zbase;

			// FIXME ratio computed according to helmflow (legacy comment)
			float Vend_pu = V1 / U1nom;
			float Vsource_pu = V2 / U2nom;
			float ratio = Vsource_pu / Vend_pu;

			switch (name)
			{
			case "pu(R)":
				return r;
			case "pu(X)":
				return x;
			case "pu(G)":
				return g;
			case "pu(B)":
				return b;
			case "ratio":
				return ratio;
			}
		}
		return null;
	}

	private final Network		network;

	private static final String	COMPOUND_ID_SEPARATOR	= "::";
	// FIXME the value for SNREF depends on the building we are performing ... it shouldn't be a constant
	private static final float	SNREF					= 100.0f;
}
