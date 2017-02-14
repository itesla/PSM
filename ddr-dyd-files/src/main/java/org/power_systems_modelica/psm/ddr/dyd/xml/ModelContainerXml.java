package org.power_systems_modelica.psm.ddr.dyd.xml;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.power_systems_modelica.psm.ddr.dyd.Association;
import org.power_systems_modelica.psm.ddr.dyd.Component;
import org.power_systems_modelica.psm.ddr.dyd.Connection;
import org.power_systems_modelica.psm.ddr.dyd.Interconnection;
import org.power_systems_modelica.psm.ddr.dyd.Model;
import org.power_systems_modelica.psm.ddr.dyd.ModelContainer;
import org.power_systems_modelica.psm.ddr.dyd.ModelForElement;
import org.power_systems_modelica.psm.ddr.dyd.ModelForType;
import org.power_systems_modelica.psm.ddr.dyd.ParameterSet;
import org.power_systems_modelica.psm.ddr.dyd.equations.Equation;
import org.power_systems_modelica.psm.ddr.dyd.xml.equations.EquationXml;

public class ModelContainerXml
{
	public static final String MODEL_CONTAINER_ELEMENT_NAME = "model_container";

	public static ModelContainer read(XMLStreamReader r) throws XMLStreamException
	{
		final ModelContainer dyd = new ModelContainer();
		Model[] model = new Model[1];
		Component[] component = new Component[1];
		ParameterSet[] set = new ParameterSet[1];

		XmlUtil.readUntilEndElement(MODEL_CONTAINER_ELEMENT_NAME, r, () -> {
			switch (r.getLocalName())
			{
			case AssociationXml.ROOT_ELEMENT_NAME:
				dyd.add(AssociationXml.read(r));
				break;
			case ModelXml.ROOT_ELEMENT_NAME:
				model[0] = ModelXml.read(r);
				dyd.add(model[0]);
				break;
			case ComponentXml.ROOT_ELEMENT_NAME:
				component[0] = ComponentXml.read(r);
				set[0] = null;
				model[0].addComponent(component[0]);
				break;
			case ParameterValueXml.ROOT_ELEMENT_NAME:
				if (set[0] == null)
				{
					// TODO Reorganize XML reading/writing using inner elements and adders
					set[0] = new ParameterSet("inline");
					component[0].setParameterSet(set[0]);
				}
				set[0].add(ParameterValueXml.read(r));
				break;
			case ParameterReferenceXml.ROOT_ELEMENT_NAME:
				if (set[0] == null)
				{
					// TODO Reorganize XML reading/writing using inner elements and adders
					set[0] = new ParameterSet("inline");
					component[0].setParameterSet(set[0]);
				}
				set[0].add(ParameterReferenceXml.read(r));
				break;
			case InterconnectionXml.ROOT_ELEMENT_NAME:
				Interconnection connector = InterconnectionXml.read(r);
				model[0].addConnector(connector);
				break;
			case ConnectionXml.ROOT_ELEMENT_NAME:
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

	public static void write(XMLStreamWriter w, ModelContainer mc)
			throws XMLStreamException
	{
		w.writeStartElement(MODEL_CONTAINER_ELEMENT_NAME);
		w.writeDefaultNamespace(XmlUtil.NAMESPACE);

		// Sort models before writing
		for (Model m : sortedModels(mc.getModels()))
			ModelXml.write(w, m);
		for (Association a : mc.getAssociations())
			AssociationXml.write(w, a);

		w.writeEndElement();
	}

	private static Collection<Model> sortedModels(Collection<Model> models)
	{
		Comparator<Model> byTypeName, byId;
		byTypeName = Comparator.comparing(ModelContainerXml::getTypeName);
		byId = Comparator.comparing(ModelContainerXml::getId);
		return models.stream().sorted(byTypeName.thenComparing(byId)).collect(Collectors.toList());
	}

	private static String getTypeName(Model m)
	{
		if (m instanceof ModelForType) return ((ModelForType) m).getType().name();
		// Ensure models that are not for a type go later in the sorting
		return "~";
	}

	private static String getId(Model m)
	{
		if (m instanceof ModelForElement) return ((ModelForElement) m).getId();
		return "";
	}
}
