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

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.dd.Association;
import org.power_systems_modelica.psm.dd.Component;
import org.power_systems_modelica.psm.dd.Connection;
import org.power_systems_modelica.psm.dd.Interconnection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.ModelForAssociation;
import org.power_systems_modelica.psm.dd.ModelForElement;
import org.power_systems_modelica.psm.dd.ModelForType;
import org.power_systems_modelica.psm.dd.ParameterReference;
import org.power_systems_modelica.psm.dd.ParameterSet;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.dd.StaticType;
import org.power_systems_modelica.psm.dd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.xml.equations.EquationXml;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelContainerXml {
	public static final String MODEL_CONTAINER_ELEMENT_NAME_AIA = "model_container";
	public static final String MODEL_CONTAINER_ELEMENT_NAME_DYN = "dynamicModelsArchitecture";

	public static ModelContainer readAia(XMLStreamReader r) throws XMLStreamException {
		final ModelContainer dyd = new ModelContainer();
		dyd.setDynamo(false);
		Model[] model = new Model[1];
		Component[] component = new Component[1];
		ParameterSet[] set = new ParameterSet[1];

		XmlUtil.readUntilEndElement(MODEL_CONTAINER_ELEMENT_NAME_AIA, r, () -> {
			switch (r.getLocalName()) {
			case AssociationXml.ROOT_ELEMENT_NAME:
				dyd.add(AssociationXml.read(r));
				break;
			case ModelXml.ROOT_ELEMENT_NAME_AIA:
				model[0] = ModelXml.readAia(r);
				dyd.add(model[0]);
				break;
			case ComponentXml.ROOT_ELEMENT_NAME_AIA:
				component[0] = ComponentXml.readAia(r);
				set[0] = null;
				model[0].addComponent(component[0]);
				break;
			case ParameterValueXml.ROOT_ELEMENT_NAME:
				if (set[0] == null) {
					// TODO Reorganize XML reading/writing using inner elements and adders
					set[0] = new ParameterSet("inline");
					component[0].setParameterSet(set[0]);
				}
				set[0].add(ParameterValueXml.read(r));
				break;
			case ParameterReferenceXml.ROOT_ELEMENT_NAME_AIA:
				if (set[0] == null) {
					// TODO Reorganize XML reading/writing using inner elements and adders
					set[0] = new ParameterSet("inline");
					component[0].setParameterSet(set[0]);
				}
				set[0].add(ParameterReferenceXml.readAia(r));
				break;
			case InterconnectionXml.ROOT_ELEMENT_NAME:
				Interconnection connector = InterconnectionXml.read(r);
				model[0].addConnector(connector);
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME_AIA:
				Connection connection = ConnectionXml.read(r);
				model[0].addConnection(connection);
				break;
			case EquationXml.ELEMENT_NAME:
				Equation eq = EquationXml.read(r);
				model[0].addOtherEquation(eq);
				break;
			}
		});
		return dyd;
	}

	public static ModelContainer readDynamo(XMLStreamReader r) throws XMLStreamException {
		final ModelContainer dyd = new ModelContainer();
		dyd.setDynamo(true);
		Model[] model = new Model[1];
		Model[] initModel = new Model[1];
		Model[] asocModel = new Model[1];
		Model[] sysModel = new Model[1];
		Component[] component = new Component[1];
		ParameterSet[] set = new ParameterSet[1];

		XmlUtil.readUntilEndElement(MODEL_CONTAINER_ELEMENT_NAME_DYN, r, () -> {
			switch (r.getLocalName()) {
			case ModelXml.ROOT_ELEMENT_NAME_DYN:
				initModel[0] = null;
				model[0] = readDynamoModel(dyd, r);
				dyd.add(model[0]);
				break;
			case ModelXml.ROOT_TEMPLATE_NAME_DYN:
				initModel[0] = null;
				model[0] = ModelXml.readDynamo(r);
				dyd.add(model[0]);
				component[0] = ComponentXml.readDynamoTemplate(r);
				set[0] = null;
				model[0].addComponent(component[0]);
				break;
			case ComponentXml.BLACKBOX_MODEL_NAME:
				Model sm = getModel(dyd, "_SYSTEM_", "DM__SYSTEM_");
				if (sm == null)
				{
					sm = ModelXml.SystemModel(r);
					dyd.add(sm);
					model[0] = sm;
				}
				component[0] = ComponentXml.readBlackBoxModel(r);
				set[0] = null;
				if (component[0] != null)
				{
					component[0].setParameterSet(set[0]);
					model[0].addComponent(component[0]);
				}
				break;
			case ComponentXml.MACRO_CONNECTOR_NAME:
				String idC = r.getAttributeValue(null, "id");
				readMacroConnector(dyd, idC, r);
				break;
			case ParameterReferenceXml.STATIC_REF_NAME:
				/*
				component[0] = new Component("inline", null);
				model[0].addComponent(component[0]);
				set[0] = new ParameterSet("inline");
				component[0].setParameterSet(set[0]);
				set[0].add(ParameterReferenceXml.readStaticRef(r));
				*/
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME_DYN:
				String idM1 = r.getAttributeValue(null, "id1");
				String idM2 = r.getAttributeValue(null, "id2");
				model[0] = dyd.getModel(idM1, Stage.SIMULATION);
				if (model[0] == null) model[0] = dyd.getModel(idM2, Stage.SIMULATION);
				Connection c = ConnectionXml.readConnection(dyd, r);
				if (c != null)
				{
					if (ModelicaUtil.containsOmegaRef(idM1) && !ModelicaUtil.isNetwork(idM2))
					{
						Model m1 = dyd.getModel(idM2, Stage.SIMULATION);
						m1.addConnector(InterconnectionXml.readConnector(c.getId2(), c.getVar2(), "{system}", c.getVar1()));
						m1 = dyd.getModel(idM1, Stage.SIMULATION);
						m1.addConnector(InterconnectionXml.readConnector(c.getId1(), c.getVar1()));
					}
					else if (ModelicaUtil.containsOmegaRef(idM2) && !ModelicaUtil.isNetwork(idM1))
					{
						Model m2 = dyd.getModel(idM1, Stage.SIMULATION);
						m2.addConnector(InterconnectionXml.readConnector(c.getId1(), c.getVar1(), "{system}", c.getVar2()));
						m2 = dyd.getModel(idM2, Stage.SIMULATION);
						m2.addConnector(InterconnectionXml.readConnector(c.getId2(), c.getVar2()));
					}
					else if ((ModelicaUtil.isNetwork(idM1) && !ModelicaUtil.containsOmegaRef(idM2)) || (ModelicaUtil.isNetwork(idM2) && !ModelicaUtil.containsOmegaRef(idM1)))
					{
						model[0].addConnector(InterconnectionXml.readConnector(c.getId1(), c.getVar1(), c.getId2(), c.getVar2()));
					}
					else
						model[0].addConnection(c);
					
				}
				break;
			}
		});
		addGenericComponents(dyd, "Bus");
		addGenericComponents(dyd, "Line");
		addGenericComponents(dyd, "Generator");
		addGenericComponents(dyd, "Load");
		addGenericComponents(dyd, "Shunt");
		addGenericComponents(dyd, "Switch");
		addGenericComponents(dyd, "Transformer");
		return dyd;
	}
	
	public static Model readDynamoModel(ModelContainer dyd, XMLStreamReader r) throws XMLStreamException {
		final Model model = ModelXml.readDynamo(r);
		Component[] component = new Component[1];
		ParameterSet[] set = new ParameterSet[1];

		XmlUtil.readUntilEndElement(ModelXml.ROOT_ELEMENT_NAME_DYN, r, () -> {
			switch (r.getLocalName()) {
			case ComponentXml.ROOT_ELEMENT_NAME_DYN:
				component[0] = ComponentXml.readDynamo(r);
				set[0] = null;
				if (component[0].getType() != null)
					model.addComponent(component[0]);
				if (component[0].getInitName() != null)
				{
					Model m = getInitializationModel(dyd, ((ModelForElement) model).getStaticId(), model.getId());
					if (m == null) 
					{
						m = new ModelForElement(((ModelForElement) model).getStaticId(), model.getId());
						m.setStage(Stage.INITIALIZATION);
						dyd.add(m);
					}
					m.addComponent(component[0]);
				}
				break;
			case ConnectionXml.CONNECT_REF_NAME:
				model.addConnection(ConnectionXml.readConnectRef(r));
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME_DYN:
				Connection c = ConnectionXml.read(r);
				if (ModelicaUtil.isNetwork(c.getId1()))
				{
					model.addConnector(InterconnectionXml.searchConnector(model, c.getVar1()));
				}
				else if (ModelicaUtil.isNetwork(c.getId2()))
				{
					model.addConnector(InterconnectionXml.searchConnector(model, c.getVar2()));
				}
				else model.addConnection(c);
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME_DYN_INIT:
				Model m = getInitializationModel(dyd, ((ModelForElement) model).getStaticId(), model.getId());
				if (m == null) 
				{
					m = new ModelForElement(((ModelForElement) model).getStaticId(), model.getId());
					m.setStage(Stage.INITIALIZATION);
					dyd.add(m);
				}
				Connection initConnection = ConnectionXml.read(r);
				m.addConnection(initConnection);
				break;
			}
		});
		
		return model;
	}
	
	public static void readMacroConnector(ModelContainer dyd, String id, XMLStreamReader r) throws XMLStreamException {
		XmlUtil.readUntilEndElement(ComponentXml.MACRO_CONNECTOR_NAME, r, () -> {
			switch (r.getLocalName()) {
			case ConnectionXml.ROOT_ELEMENT_NAME_DYN:
				ConnectionXml.readSimulation(dyd, id, r);
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME_DYN_INIT:
				ConnectionXml.readInitialization(dyd, id, r);
				break;
			}
		});
	}
	
	private static Model getModel(ModelContainer dyd, String staticId, String id) {
		
		for (Model m : dyd.getModels())
		{
			if (m.getId().equals(id) && ((ModelForElement) m).getStaticId().equals(staticId))
				return m;
		}
		return null;
	}

	private static Model getInitializationModel(ModelContainer dyd, String staticId, String id) {
		
		for (Model m : dyd.getModels())
		{
			if (m.getId().equals(id) && ((ModelForElement) m).getStaticId().equals(staticId) && m.getStage() == Stage.INITIALIZATION)
				return m;
		}
		return null;
	}

	private static void addGenericComponents(ModelContainer dyd, String type) {
		Model model = null;
		Component component = null;
		Interconnection ic = null;
		ParameterSet set = null;
		ParameterReference ref = null;
		switch (type) {
		case "Bus":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("bus_{staticId}", "Dynamo.Electrical.Buses.Bus");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "V", null, "IIDM", "V_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "angle", null, "IIDM", "A");
			set.add(ref);
			
			ic = new Interconnection(
					"ACPIN", "bus_{staticId}", "ACPIN",
					null, null,
					null, null);
			model.addConnector(ic);
			ic = new Interconnection(
					"switchOff", "bus_{staticId}", "switchOff",
					null, null,
					null, null);
			model.addConnector(ic);
			break;
		case "Generator":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("gen_{staticId}", "Dynamo.Electrical.Machines.DYNModelM1S");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "P0", null, "IIDM", "P_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "Q0", null, "IIDM", "Q_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "U0", null, "IIDM", "U_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "UTeta0", null, "IIDM", "A");
			set.add(ref);
			ic = new Interconnection(
					null, "gen_{staticId}", "ACPIN",
					"{bus}", "ACPIN",
					null, null);
			model.addConnector(ic);
			break;
		case "Line":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("line_{staticId}", "Dynamo.Electrical.Lines.Line");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "R", null, "IIDM", "R_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "X", null, "IIDM", "X_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "G", null, "IIDM", "G1_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "B", null, "IIDM", "B1_pu");
			set.add(ref);
			ic = new Interconnection(
					null, "line_{staticId}", "ACPINF",
					"{bus1}", "ACPIN",
					null, null);
			model.addConnector(ic);
			ic = new Interconnection(
					null, "line_{staticId}", "ACPINT",
					"{bus2}", "ACPIN",
					null, null);
			model.addConnector(ic);
			break;
		case "Load":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("load_{staticId}", "Dynamo.Electrical.Loads.PwLoad.PwLoad3");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "P0", null, "IIDM", "P_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "Q0", null, "IIDM", "Q_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "U0", null, "IIDM", "V_pu");
			set.add(ref);
			ic = new Interconnection(
					null, "load_{staticId}", "ACPIN",
					"{bus}", "ACPIN",
					null, null);
			model.addConnector(ic);
			break;
		case "Shunt":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("cap_{staticId}", "Dynamo.Electrical.Shunts.Shunt");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "B0", null, "IIDM", "B_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "Q0", null, "IIDM", "Q_pu");
			set.add(ref);
			ic = new Interconnection(
					null, "cap_{staticId}", "ACPIN",
					"{bus}", "ACPIN",
					null, null);
			model.addConnector(ic);
			break;
		case "Switch":
			break;
		case "Transformer":
			model = new ModelForType(StaticType.valueOf(type), "DM{staticId}");
			model.setStage(Stage.SIMULATION);
			dyd.add(model);
			component = new Component("TR", "Dynamo.Electrical.PowerTransformers.PowerTransformer");
			model.addComponent(component);
			set = new ParameterSet("inline");
			component.setParameterSet(set);
			ref = new ParameterReference("DOUBLE", "R", null, "IIDM", "R_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "X", null, "IIDM", "X_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "G", null, "IIDM", "G_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "B", null, "IIDM", "B_pu");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "ratio", null, "IIDM", "ratio");
			set.add(ref);
			ref = new ParameterReference("DOUBLE", "theta", null, "IIDM", "theta");
			set.add(ref);
			ic = new Interconnection(
					null, "trafo_{staticId}", "ACPINF",
					"{bus1}", "ACPIN",
					null, null);
			model.addConnector(ic);
			ic = new Interconnection(
					null, "trafo_{staticId}", "ACPINT",
					"{bus2}", "ACPIN",
					null, null);
			model.addConnector(ic);
			break;
		}
		if (model != null) {
		}
	}

	public static void write(XMLStreamWriter w, ModelContainer mc) throws XMLStreamException {
		if (mc.isDynamo())
			w.writeStartElement(MODEL_CONTAINER_ELEMENT_NAME_DYN);
		else
			w.writeStartElement(MODEL_CONTAINER_ELEMENT_NAME_AIA);
		w.writeDefaultNamespace(XmlUtil.NAMESPACE);

		// Sort models before writing
		for (Model m : sortedModels(mc.getModels()))
			ModelXml.write(w, m, mc.isDynamo());
		for (Association a : mc.getAssociations())
			AssociationXml.write(w, a);

		w.writeEndElement();
	}

	private static Collection<Model> sortedModels(Collection<Model> models) {
		Comparator<Model> byTypeName, byId;
		byTypeName = Comparator.comparing(ModelContainerXml::getTypeName);
		byId = Comparator.comparing(ModelContainerXml::getId);
		return models.stream().sorted(byTypeName.thenComparing(byId)).collect(Collectors.toList());
	}

	private static String getTypeName(Model m) {
		if (m instanceof ModelForType)
			return ((ModelForType) m).getType().name();
		// Ensure models that are not for a type go later in the sorting
		return "~";
	}

	private static String getId(Model m) {
		if (m instanceof ModelForElement)
			return ((ModelForElement) m).getId();
		return "";
	}
}
