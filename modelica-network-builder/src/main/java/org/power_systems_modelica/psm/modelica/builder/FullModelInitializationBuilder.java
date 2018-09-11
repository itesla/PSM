package org.power_systems_modelica.psm.modelica.builder;

/*
 * #%L
 * Modelica network builder
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Connectable;
import com.powsybl.iidm.network.EquipmentTopologyVisitor;
import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class FullModelInitializationBuilder extends ModelicaNetworkBuilder
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
				public <I extends Connectable<I>> void visitEquipment(Connectable<I> e)
				{
					if (!visited.contains(e))
					{
						addInitialization(e, mos);
						visited.add(e);
					}
				}
			};
			// Only visit connected equipments, equipment that is out-of-service will not be visited
			b.visitConnectedEquipments(visitor);
		}
		setAllDynamicModelsAdded(true);
		return mos;
	}

	private void addInitialization(Identifiable<?> e, Collection<ModelicaDocument> mos)
	{
		ModelicaDocument mo = null;
		ModelicaModel de = getDdr().getModelicaModel(e, Stage.INITIALIZATION);
		if (de != null) mo = buildModelicaInitializationDocument(de);
		if (mo != null) mos.add(mo);
	}

	private ModelicaDocument buildModelicaInitializationDocument(ModelicaModel m)
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin("");
		ModelicaSystemModel ms = new ModelicaSystemModel(
				ModelicaUtil.normalizedIdentifier(m.getStaticId()));
		mo.setSystemModel(ms);

		// We solve here potential external references
		// Argument values in the declarations could be referred to external source (the IIDM Network)
		// We solve these references in the context of the current Network and ModelicaModel
		ms.addDeclarations(resolveReferences(m.getDeclarations(), m));
		ms.addEquations(m.getEquations());

		List<ModelicaEquation> otherSystemEquations;
		otherSystemEquations = getDdr().getSystemOtherEquationsInContext(ms, Stage.INITIALIZATION);
		if (otherSystemEquations != null) ms.addEquations(otherSystemEquations);
		return mo;
	}
}
