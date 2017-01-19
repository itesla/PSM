package org.power_systems_modelica.psm.modelica.builder;

import org.power_systems_modelica.psm.ddr.DynamicDataRepository;

import eu.itesla_project.iidm.network.Network;

public abstract class ModelicaNetworkBuilder extends ModelicaBuilder
{
	public ModelicaNetworkBuilder(DynamicDataRepository ddr, Network n)
	{
		this.ddr = ddr;
		this.network = n;
		onlyMainConnectedComponent = false;
		allModelsAdded = false;
		registerResolver("IIDM", new IidmReferenceResolver(network));
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
