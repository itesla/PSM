package org.power_systems_modelica.psm.modelica.builder;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.ModelicaConnector;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.itesla_project.iidm.network.Bus;
import eu.itesla_project.iidm.network.Identifiable;
import eu.itesla_project.iidm.network.Network;
import eu.itesla_project.iidm.network.SingleTerminalConnectable;
import eu.itesla_project.iidm.network.TwoTerminalsConnectable;

public class DynamicNetworkReferenceResolver extends IidmReferenceResolver
{
	public DynamicNetworkReferenceResolver(Network network, ModelicaDocument mo)
	{
		super(network);
		dynamicModelsByStaticId = ModelicaUtil.groupByStaticId(mo);
	}

	public void addModel(ModelicaModel m)
	{
		dynamicModelsByStaticId.put(m.getStaticId(), m);
	}

	public Collection<ModelicaModel> getModels()
	{
		return dynamicModelsByStaticId.values();
	}

	@Override
	public ModelicaConnector resolveConnectionTarget(
			String targetItem,
			String targetPin,
			ModelicaModel sourceModel)
	{
		Identifiable<?> sourceElement = network.getIdentifiable(sourceModel.getStaticId());
		if (sourceElement == null) return null;

		ModelicaModel targetModel = null;
		if (targetItem.equals("bus"))
		{
			// If the target is my "bus" from the point of view of the sourceElement
			// It implies that is a single terminal connectable element
			SingleTerminalConnectable<?> e = (SingleTerminalConnectable<?>) sourceElement;
			Bus bus = e.getTerminal().getBusBreakerView().getBus();
			targetModel = dynamicModelsByStaticId.get(bus.getId());
		}
		else if (targetItem.startsWith("bus"))
		{
			// We assume targetItem is either bus1 or bus2
			// We do not rely on ordinal of the enumeration items
			TwoTerminalsConnectable.Side side;
			if (targetItem.endsWith("1")) side = TwoTerminalsConnectable.Side.ONE;
			else if (targetItem.endsWith("2")) side = TwoTerminalsConnectable.Side.TWO;
			else return null;

			TwoTerminalsConnectable<?> e = (TwoTerminalsConnectable<?>) sourceElement;
			Bus bus = e.getTerminal(side).getBusBreakerView().getBus();
			targetModel = dynamicModelsByStaticId.get(bus.getId());
		}
		if (targetModel == null)
		{
			LOG.error(
					"Could not resolve target {}:{} for sourceElement {}",
					targetItem,
					targetPin,
					sourceElement.getId());
		}
		return findConnector(targetPin, targetModel.getConnectors());
	}

	private ModelicaConnector findConnector(String pin, ModelicaConnector[] connectors)
	{
		ModelicaConnector cf = Stream.of(connectors)
				.filter(c -> c.getPin().equals(pin))
				.findFirst()
				.get();
		return cf;
	}

	private Map<String, ModelicaModel>	dynamicModelsByStaticId;

	private static final Logger			LOG	= LoggerFactory
			.getLogger(DynamicNetworkReferenceResolver.class);
}
