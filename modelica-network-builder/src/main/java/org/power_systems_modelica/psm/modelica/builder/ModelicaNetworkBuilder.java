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

import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;

import com.powsybl.iidm.network.Network;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public abstract class ModelicaNetworkBuilder extends ModelicaBuilder
{
	public ModelicaNetworkBuilder(DynamicDataRepository ddr, Network n)
	{
		this.ddr = ddr;
		this.network = n;
		onlyMainConnectedComponent = false;
		allModelsAdded = false;
		registerResolver("IIDM", new IidmReferenceResolver(network, ddr.getSystemModel(Stage.SIMULATION).orElse(null)));
	}

	public Network getNetwork()
	{
		return network;
	}

	public boolean isOnlyMainConnectedComponent()
	{
		return onlyMainConnectedComponent;
	}

	public void setOnlyMainConnectedComponent(boolean onlyMain)
	{
		this.onlyMainConnectedComponent = onlyMain;
	}

	public DynamicDataRepository getDdr()
	{
		return ddr;
	}

	@Override
	public boolean haveAllDynamicModelsBeenAdded()
	{
		return allModelsAdded;
	}

	protected void setAllDynamicModelsAdded(boolean allModelsAdded)
	{
		this.allModelsAdded = allModelsAdded;
	}

	private final DynamicDataRepository	ddr;
	private final Network				network;
	private boolean						onlyMainConnectedComponent;
	private boolean						allModelsAdded;
}
