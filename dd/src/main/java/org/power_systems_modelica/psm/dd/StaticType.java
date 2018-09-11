package org.power_systems_modelica.psm.dd;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Generator;
import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Line;
import com.powsybl.iidm.network.Load;
import com.powsybl.iidm.network.ShuntCompensator;
import com.powsybl.iidm.network.Switch;
import com.powsybl.iidm.network.TwoWindingsTransformer;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
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

		BaseModelicaDeclaration d = m.getDeclarations().get(0);
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
		BaseModelicaDeclaration d = m.getDeclarations().get(0);
		if (d.getType().equals("iPSL.Electrical.Loads.Eurostag.PwLoadPQ") &&
				d.getId().contains("fixinj") &&
				d.getId().contains("_GEN_"))
			return Generator;
		return stype;
	}

	private static final Logger LOG = LoggerFactory.getLogger(StaticType.class);
}
