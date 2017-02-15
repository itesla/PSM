package org.power_systems_modelica.psm.ddr;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Generator;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Line;
import eu.itesla_project.iidm.network.Load;
import eu.itesla_project.iidm.network.ShuntCompensator;
import eu.itesla_project.iidm.network.Switch;
import eu.itesla_project.iidm.network.TwoWindingsTransformer;

public enum StaticType
{
	System, Bus, Line, Transformer, Load, Shunt, Generator, Switch;

	static public StaticType from(Identifiable<?> e)
	{
		if (e instanceof Bus) return Bus;
		else if (e instanceof Line) return Line;
		else if (e instanceof TwoWindingsTransformer) return Transformer;
		else if (e instanceof Load) return Load;
		else if (e instanceof ShuntCompensator) return Shunt;
		else if (e instanceof Generator) return Generator;
		else if (e instanceof Switch) return Switch;
		return null;
	}

	public static StaticType from(ModelicaModel m)
	{
		if (ModelicaUtil.isSystemModel(m)) return System;

		ModelicaDeclaration d = m.getDeclarations().get(0);
		String dtype = d.getType();
		StaticType stype = fromDynamicType(dtype);
		if (stype == null)
		{
			String message = "Can not identify static type from dynamic type '" + dtype + "'";
			LOG.error(message);
			throw new RuntimeException(message);
		}
		stype = checkGeneratorModeledAsFixedInjection(stype, m);
		return stype;
	}

	public static StaticType fromDynamicType(String dtype)
	{
		if (dtype.startsWith("iPSL.Electrical.Buses.")) return Bus;
		else if (dtype.startsWith("iPSL.Electrical.Branches"))
		{
			if (dtype.contains("Line")) return Line;
			else if (dtype.contains("Transformer")) return Transformer;
		}
		else if (dtype.startsWith("iPSL.Electrical.Loads.")) return Load;
		else if (dtype.startsWith("iPSL.Electrical.Banks.")) return Shunt;
		else if (dtype.startsWith("iPSL.Electrical.Machines")) return Generator;
		return null;
	}

	public static StaticType checkGeneratorModeledAsFixedInjection(
			StaticType stype,
			ModelicaModel m)
	{
		ModelicaDeclaration d = m.getDeclarations().get(0);
		if (d.getType().equals("iPSL.Electrical.Loads.Eurostag.PwLoadPQ") &&
				d.getId().contains("fixinj") &&
				d.getId().contains("_GEN_"))
			return Generator;
		return stype;
	}

	private static final Logger LOG = LoggerFactory.getLogger(StaticType.class);
}
