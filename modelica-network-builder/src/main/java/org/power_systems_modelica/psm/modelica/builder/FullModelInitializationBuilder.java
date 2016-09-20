package org.power_systems_modelica.psm.modelica.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Connectable;
import eu.itesla_project.iidm.network.EquipmentTopologyVisitor;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;

public class FullModelInitializationBuilder extends ModelicaBuilder
{
	public FullModelInitializationBuilder(DynamicDataRepository ddr, Network n)
	{
		super(ddr, n);
	}

	public Collection<ModelicaDocument> buildModelicaDocuments()
	{
		Network network = getNetwork();

		if (network.getBusBreakerView() == null) return null;
		Collection<ModelicaDocument> mos = new ArrayList<ModelicaDocument>();

		// For every equipment that has initialization components build its initialization Modelica document
		final Set<Identifiable<?>> visited = new HashSet<>(network.getIdentifiables().size());
		for (Bus b : network.getBusBreakerView().getBuses())
		{
			if (isOnlyMainConnectedComponent() && !b.isInMainConnectedComponent()) continue;

			addInitialization(b, mos);
			EquipmentTopologyVisitor visitor = new EquipmentTopologyVisitor()
			{
				@Override
				public void visitEquipment(Connectable<?> e)
				{
					if (!visited.contains(e))
					{
						addInitialization(e, mos);
						visited.add(e);
					}
				}
			};
			if (isOnlyMainConnectedComponent()) b.visitConnectedEquipments(visitor);
			else b.visitConnectedOrConnectableEquipments(visitor);
		}
		return mos;
	}

	private void addInitialization(Identifiable<?> e, Collection<ModelicaDocument> mos)
	{
		ModelicaDocument mo = null;
		ModelicaModel de = getDdr().getModelicaInitializationModel(e);
		if (de != null) mo = buildModelicaInitializationDocument(de);
		if (mo != null) mos.add(mo);
	}

	private ModelicaDocument buildModelicaInitializationDocument(ModelicaModel m)
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel ms = new ModelicaSystemModel(m.getStaticId()); // FIXME or m.getName(); ???
		ms.addDeclarations(getDdr().getSystemDeclarations());

		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel

		ms.addDeclarations(resolveReferences(m.getDeclarations(), m));
		ms.addEquations(m.getEquations());
		return mo;
	}
}
