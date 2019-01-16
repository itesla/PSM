package org.power_systems_modelica.psm.ddr.dyd.xml;

/*
 * #%L
 * Dynamic Data Repository on DYD files
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.Connection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ConnectionXml
{
	public static final String	ROOT_ELEMENT_NAME_AIA	   = "connection";
	public static final String	ROOT_ELEMENT_NAME_DYN	   = "connect";
	public static final String  ROOT_ELEMENT_NAME_DYN_INIT = "initConnect";
	public static final String	CONNECT_REF_NAME		   = "macroConnect";

	public static Connection read(XMLStreamReader r)
	{
		String id1 = r.getAttributeValue(null, "id1");
		String var1 = r.getAttributeValue(null, "var1");
		String id2 = r.getAttributeValue(null, "id2");
		String var2 = r.getAttributeValue(null, "var2");
		Connection c = new Connection(id1, var1, id2, var2);
		return c;
	}

	public static Connection readConnection(ModelContainer dyd, XMLStreamReader r) {
		String id1 = r.getAttributeValue(null, "id1");
		String var1 = r.getAttributeValue(null, "var1");
		String id2 = r.getAttributeValue(null, "id2");
		String var2 = r.getAttributeValue(null, "var2");
		
		Model m1 = dyd.getModel(id1, Stage.SIMULATION);
		Model m2 = dyd.getModel(id2, Stage.SIMULATION);

		String targetId1 = null;
		String targetVar1 = null;
		String targetId2 = null;
		String targetVar2 = null;
		if (ModelicaUtil.containsOmegaRef(id1))
		{
			targetId1 = id1;
			targetVar1 = var1;
		}
		else if (m1 != null)
		{
			for (Component c : m1.getComponents())
			{
				if (var1.startsWith(c.getId()))
				{
					targetId1 = c.getId();
					targetVar1 = var1.replaceAll(targetId1 + "_", "");
				}
			}
		}
		else if (ModelicaUtil.isNetwork(id1))
		{
			targetId1 = var1.substring(0, var1.lastIndexOf("_"));
			targetVar1 = var1.replaceAll(targetId1 + "_", "");
		}
		if (ModelicaUtil.containsOmegaRef(id2))
		{
			targetId2 = id2;
			targetVar2 = var2;
		}
		else if (m2 != null)
		{
			for (Component c : m2.getComponents())
			{
				if (var2.startsWith(c.getId()))
				{
					targetId2 = c.getId();
					targetVar2 = var2.replaceAll(targetId2 + "_", "");
				}
			}
		}
		else if (ModelicaUtil.isNetwork(id2))
		{
			targetId2 = var2.substring(0, var2.lastIndexOf("_"));
			targetVar2 = var2.replaceAll(targetId2 + "_", "");
		}
		
		if (targetId1 == null || targetId2 == null) return null;
		Connection c = new Connection(targetId1, targetVar1, targetId2, targetVar2);
		return c;
	}

	public static Connection readConnectRef(XMLStreamReader r)
	{
		String connector = r.getAttributeValue(null, "connector");
		String id1 = r.getAttributeValue(null, "id1");
		String id2 = r.getAttributeValue(null, "id2");
		Connection c = new Connection(connector, id1, id2);
		return c;
	}

	public static void readSimulation(ModelContainer dyd, String id, XMLStreamReader r) {
		readMacroConnector(dyd,Stage.SIMULATION,id,r);
	}

	public static void readInitialization(ModelContainer dyd, String id, XMLStreamReader r) {
		readMacroConnector(dyd,Stage.INITIALIZATION,id,r);
	}

	public static void readMacroConnector(ModelContainer dyd, Stage stage, String idMacro, XMLStreamReader r) {

		String var1 = r.getAttributeValue(null, "var1");
		String var2 = r.getAttributeValue(null, "var2");

		for (Model m : dyd.getModels())
		{
			if (!m.getStage().equals(stage)) continue;
			
			for (Connection c : m.getConnections())
			{
				if (c.getIdMacro() != null && c.getIdMacro().equals(idMacro))
				{
					c.setVar1(var1);
					c.setVar2(var2);
					c.setIdMacro(null);
				}
			}
		}
	}

	public static void write(XMLStreamWriter w, Connection c, boolean dynamo)
			throws XMLStreamException
	{
		if (dynamo) w.writeEmptyElement(ROOT_ELEMENT_NAME_DYN);
		else w.writeEmptyElement(ROOT_ELEMENT_NAME_AIA);
		w.writeAttribute("id1", c.getId1());
		w.writeAttribute("var1", c.getVar1());
		w.writeAttribute("id2", c.getId2());
		w.writeAttribute("var2", c.getVar2());
	}

}
