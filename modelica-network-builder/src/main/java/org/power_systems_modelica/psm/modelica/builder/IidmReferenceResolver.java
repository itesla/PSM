package org.power_systems_modelica.psm.modelica.builder;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Generator;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.PhaseTapChanger;
import eu.itesla_project.iidm.network.PhaseTapChangerStep;
import eu.itesla_project.iidm.network.RatioTapChanger;
import eu.itesla_project.iidm.network.RatioTapChangerStep;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.SingleTerminalConnectable;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;
import eu.itesla_project.iidm.network.VoltageLevel;

public class IidmReferenceResolver implements ReferenceResolver
{
	public IidmReferenceResolver(Network network)
	{
		this.network = network;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			ModelicaDeclaration d)
			throws ModelicaArgumentReferenceException
	{
		// Find the element in the network
		String staticId = m.getStaticId();
		Identifiable<?> element = network.getIdentifiable(staticId);
		if (element == null) element = getFromCompoundId(staticId);
		if (element == null) throw new UnresolvedReferenceException(a);

		// Check if the referenced property needs transformation from original IIDM data
		String name = a.getSourceName();
		Object value = fromTransformedProperty(element, name);
		if (value != null) return value;

		// Check if we can obtain the property name directly from the IIDM object
		try
		{
			value = ReflectionUtils.getPropertyValue(element, name);
			return value;
		}
		catch (Exception e)
		{
			throw new UnresolvedReferenceException(a);
		}
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
	// For elements of type Bus: V, angle, V_pu, A_rad
	// For elements of type Line: R, X, G, B
	// ...

	private Object fromTransformedProperty(Identifiable<?> element, String name)
	{
		if (element instanceof Bus)
		{
			Bus b = (Bus) element;
			switch (name)
			{
			case "V_pu":
				return b.getV() / b.getVoltageLevel().getNominalV();
			case "A_rad":
				return (float) Math.toRadians(b.getAngle());
			case "V":
				return b.getV();
			case "A":
				return b.getAngle();
			}
		}
		if (element instanceof SingleTerminalConnectable<?>)
		{
			SingleTerminalConnectable<?> e = (SingleTerminalConnectable<?>) element;
			Bus b = e.getTerminal().getBusView().getBus();
			switch (name)
			{
			case "V_pu":
				float v = Float.NaN;
				if (b != null) v = b.getV() / e.getTerminal().getVoltageLevel().getNominalV();
				return v;
			case "A_rad":
				float a = Float.NaN;
				if (b != null) a = (float) Math.toRadians(b.getAngle());
				return a;
			case "V":
				return (b != null ? b.getV() : Float.NaN);
			case "A":
				return (b != null ? b.getAngle() : Float.NaN);
			case "Vnom":
				return e.getTerminal().getVoltageLevel().getNominalV();
			case "P":
				return e.getTerminal().getP();
			case "-P":
				return -e.getTerminal().getP();
			case "Q":
				return e.getTerminal().getQ();
			case "-Q":
				return -e.getTerminal().getQ();
			case "P_pu":
				return e.getTerminal().getP() / SNREF;
			case "-P_pu":
				return -e.getTerminal().getP() / SNREF;
			case "Q_pu":
				return e.getTerminal().getQ() / SNREF;
			case "-Q_pu":
				return -e.getTerminal().getQ() / SNREF;
			}
		}
		if (element instanceof ShuntCompensator)
		{
			ShuntCompensator s = (ShuntCompensator) element;
			float V = s.getTerminal().getVoltageLevel().getNominalV();
			float sectionB = s.getbPerSection();
			switch (name)
			{
			case "B_pu":
				float B = sectionB * s.getCurrentSectionCount();
				// As a reactive injection at the nominal voltage
				B = B * V * V;
				return B / SNREF;
			case "B0_pu":
				float B0 = sectionB;
				// As a reactive injection at the nominal voltage
				B0 = B0 * V * V;
				return B0 / SNREF;
			}
		}
		if (element instanceof Generator)
		{
			Generator g = (Generator) element;
			Bus b = g.getTerminal().getBusView().getBus();

			float v = Float.NaN;
			if (b != null) v = b.getV() / g.getTerminal().getVoltageLevel().getNominalV();
			float a = Float.NaN;
			if (b != null) a = (float) Math.toRadians(b.getAngle());
			Complex u = ComplexUtils.polar2Complex(v, a);

			// XXX LUMA check different results same input data
			System.out.printf("XXX LUMA %-12s %10.6f %10.6f %10.6f%n", element, b.getAngle(), a,
					u.getImaginary());

			switch (name)
			{
			case "u0_re":
				return u.getReal();
			case "u0_im":
				return u.getImaginary();
			}
		}
		if (element instanceof TwoTerminalsConnectable<?>)
		{
			TwoTerminalsConnectable<?> e = (TwoTerminalsConnectable<?>) element;
			Bus b1 = e.getTerminal1().getBusView().getBus();
			Bus b2 = e.getTerminal2().getBusView().getBus();
			switch (name)
			{
			case "V1_pu":
				float v1 = Float.NaN;
				if (b1 != null) v1 = b1.getV() / e.getTerminal1().getVoltageLevel().getNominalV();
				return v1;
			case "A1_rad":
				float a1 = Float.NaN;
				if (b1 != null) a1 = (float) Math.toRadians(b1.getAngle());
				return a1;
			case "V1":
				return (b1 != null ? b1.getV() : Float.NaN);
			case "A1":
				return (b1 != null ? b1.getAngle() : Float.NaN);
			case "V2_pu":
				float v2 = Float.NaN;
				if (b2 != null) v2 = b2.getV() / e.getTerminal1().getVoltageLevel().getNominalV();
				return v2;
			case "A2_rad":
				float a2 = Float.NaN;
				if (b2 != null) a2 = (float) Math.toRadians(b2.getAngle());
				return a2;
			case "V2":
				return (b2 != null ? b2.getV() : Float.NaN);
			case "A2":
				return (b2 != null ? b2.getAngle() : Float.NaN);
			}
		}
		if (element instanceof Line)
		{
			Line l = (Line) element;
			float nominalV = l.getTerminal2().getVoltageLevel().getNominalV();
			if (Float.isNaN(nominalV)) nominalV = 0;
			float Z = (nominalV * nominalV) / SNREF;
			switch (name)
			{
			case "R_pu":
				return l.getR() / Z;
			case "X_pu":
				return l.getX() / Z;
			case "G1_pu":
				return l.getG1() * Z;
			case "B1_pu":
				return l.getB1() * Z;
			}
		}
		if (element instanceof TwoWindingsTransformer)
		{
			TwoWindingsTransformer tx = (TwoWindingsTransformer) element;

			// TODO Compute only data that is needed for answering the value of the queried property
			// TODO These small transformations should be moved to the iPST library
			float nominalV1 = tx.getTerminal1().getVoltageLevel().getNominalV();
			float nominalV2 = tx.getTerminal2().getVoltageLevel().getNominalV();
			if (Float.isNaN(nominalV1)) nominalV1 = 0;
			if (Float.isNaN(nominalV2)) nominalV2 = 0;
			float V1 = tx.getRatedU1();
			float V2 = tx.getRatedU2();
			if (Float.isNaN(V1)) V1 = 0;
			if (Float.isNaN(V2)) V2 = 0;
			float Zbase = (float) Math.pow(nominalV2, 2) / SNREF;

			float r = tx.getR() / Zbase;
			float x = tx.getX() / Zbase;
			float g = tx.getG() * Zbase;
			float b = tx.getB() * Zbase;

			float dx = 0, dr = 0;
			RatioTapChanger rtc = tx.getRatioTapChanger();
			PhaseTapChanger ptc = tx.getPhaseTapChanger();
			if (rtc != null)
			{
				RatioTapChangerStep rtcs = rtc.getCurrentStep();
				V1 /= rtcs.getRho();
				dr += rtcs.getR();
				dx += rtcs.getX();
			}
			float theta = 0.0f;
			if (ptc != null)
			{
				PhaseTapChangerStep ptcs = ptc.getCurrentStep();
				V1 /= ptcs.getRho();
				dr += ptcs.getR();
				dx += ptcs.getX();
				theta = ptcs.getAlpha();
			}
			r *= (1 + dr / 100);
			x *= (1 + dx / 100);

			// FIXME ratio computed according to helmflow (legacy comment)
			float endV = V1 / nominalV1;
			float sourceV = V2 / nominalV2;
			float ratio = sourceV / endV;

			switch (name)
			{
			case "R_pu":
				return r;
			case "X_pu":
				return x;
			case "G_pu":
				return g;
			case "B_pu":
				return b;
			case "ratio":
				return ratio;
			case "theta":
				return theta;
			}
		}
		return null;
	}

	protected final Network		network;

	private static final String	COMPOUND_ID_SEPARATOR	= "::";
	// FIXME The value for SNREF depends on the building we are performing (it is a system parameter) ... it shouldn't be a constant
	private static final float	SNREF					= 100.0f;
}
