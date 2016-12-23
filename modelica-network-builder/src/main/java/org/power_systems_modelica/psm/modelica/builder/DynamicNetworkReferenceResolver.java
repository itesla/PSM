package org.power_systems_modelica.psm.modelica.builder;

import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.SingleTerminalConnectable;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;

public class DynamicNetworkReferenceResolver extends IidmReferenceResolver
{
	public DynamicNetworkReferenceResolver(Network network, ModelicaBuilder modelicaBuilder)
	{
		super(network);
		this.modelicaBuilder = modelicaBuilder;
	}

	@Override
	public Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d)
	{
		switch (name)
		{
		case "numGeneratorsOmegaRef":
			// TODO We are assuming here that all generators present in the static model will connect to global omegaRef
			// At least, we should reduce this numbers taking into account generators modeled as fixed injections
			return network.getGeneratorCount();
		}
		return null;
	}

	@Override
	public Optional<ModelicaInterconnection> resolveConnectionTarget(
			String targetItem,
			String targetPin,
			ModelicaModel sourceModel)
	{
		Identifiable<?> sourceElement = network.getIdentifiable(sourceModel.getStaticId());
		if (sourceElement == null) return null;

		ModelicaModel targetModel = null;
		if (targetItem.equals("{system}"))
		{
			// A direct reference to a "system pin" (omegaRef)
			targetModel = modelicaBuilder.getSystemModel();
		}
		// The target is my "bus" from the point of view of the sourceElement
		else if (targetItem.equals("{bus}"))
		{
			// The source element could be a Bus
			// This is the case when the source dynamic model is a fault in a bus,
			// The targetModel is the same bus
			if (sourceElement instanceof Bus)
			{
				targetModel = modelicaBuilder.getDynamicModelFor(sourceElement.getId());
			}
			else
			{
				// If sourceElement is not a Bus,
				// Then it should be a SingleTerminalConnectable element
				// Someone that wants to connect to "its unique" bus
				SingleTerminalConnectable<?> e = (SingleTerminalConnectable<?>) sourceElement;
				Bus bus = e.getTerminal().getBusBreakerView().getBus();
				targetModel = modelicaBuilder.getDynamicModelFor(bus.getId());
			}
		}
		else if (targetItem.startsWith("{bus"))
		{
			// We assume targetItem is either bus1 or bus2
			// We do not rely on ordinal of the enumeration items
			TwoTerminalsConnectable.Side side;
			if (targetItem.endsWith("1}")) side = TwoTerminalsConnectable.Side.ONE;
			else if (targetItem.endsWith("2}")) side = TwoTerminalsConnectable.Side.TWO;
			else return Optional.empty();

			TwoTerminalsConnectable<?> e = (TwoTerminalsConnectable<?>) sourceElement;
			Bus bus = e.getTerminal(side).getBusBreakerView().getBus();
			targetModel = modelicaBuilder.getDynamicModelFor(bus.getId());
		}
		if (targetModel == null)
		{
			LOG.error(
					"Could not resolve target {}:{} for sourceElement {}",
					targetItem,
					targetPin,
					sourceElement.getId());
			return Optional.empty();
		}

		Optional<ModelicaInterconnection> c = findInterconnection(targetPin,
				targetModel.getInterconnections());
		if (!c.isPresent())
		{
			LOG.error(
					"Could not resolve target pin {} in targetItem {} for sourceElement {}",
					targetPin,
					targetItem,
					sourceElement.getId());
			return Optional.empty();
		}

		// All connectors that have been resolved through this resolver will receive a proper staticId
		String targetStaticId = targetModel.getStaticId();
		c.get().setStaticId(targetStaticId);
		return c;
	}

	private Optional<ModelicaInterconnection> findInterconnection(
			String name,
			ModelicaInterconnection[] connectors)
	{
		Optional<ModelicaInterconnection> c;

		// The target could be an item of an array of connectors
		if (isConnectionArrayReference(name))
		{
			String arrayName = getConnectionArrayName(name);
			return Stream.of(connectors)
					.filter(c0 -> isConnectionArray(c0.getName()))
					.filter(c0 -> getConnectionArrayName(c0.getName()).equals(arrayName))
					.findFirst()
					.map(c0 -> {
						String varArrayName = getConnectionArrayName(c0.getComponentVar());
						int k = c0.nextConnectionArrayItem();
						String var1 = String.format("%s[%d]", varArrayName, k);
						ModelicaInterconnection c1 = new ModelicaInterconnection(
								c0.getName(), c0.getComponentId(), var1);
						return c1;
					});
		}

		// Find by name
		c = Stream.of(connectors)
				.filter(c0 -> c0.getName().equals(name))
				.findFirst();
		return c;
	}

	private static boolean isConnectionArrayReference(String pin)
	{
		return pin.indexOf("[?]") > 0;
	}

	private static boolean isConnectionArray(String pin)
	{
		return pin.indexOf("[]") > 0;
	}

	private static String getConnectionArrayName(String pin)
	{
		int p = pin.indexOf("[");
		if (p < 0) return "";
		String name = pin.substring(0, p);
		return name;
	}

	private final ModelicaBuilder	modelicaBuilder;

	private static final Logger		LOG	= LoggerFactory
			.getLogger(DynamicNetworkReferenceResolver.class);
}
