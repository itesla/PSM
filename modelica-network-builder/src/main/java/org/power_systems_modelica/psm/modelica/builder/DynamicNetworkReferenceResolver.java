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

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.powsybl.iidm.network.Bus;
import com.powsybl.iidm.network.Identifiable;
import com.powsybl.iidm.network.Network;
import com.powsybl.iidm.network.SingleTerminalConnectable;
import com.powsybl.iidm.network.Switch;
import com.powsybl.iidm.network.TwoTerminalsConnectable;
import com.powsybl.iidm.network.VoltageLevel.BusBreakerView;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DynamicNetworkReferenceResolver extends IidmReferenceResolver
{
	public DynamicNetworkReferenceResolver(Network network, ModelicaBuilder modelicaBuilder)
	{
		super(network, modelicaBuilder.getDynamicModelFor(ModelicaUtil.getSystemStaticId()) != null ? modelicaBuilder.getDynamicModelFor(ModelicaUtil.getSystemStaticId()) : null);
		this.modelicaBuilder = modelicaBuilder;
	}

	@Override
	public Object resolveReference(
			ModelicaArgumentReference a,
			ModelicaModel m,
			BaseModelicaDeclaration d)
			throws ModelicaArgumentReferenceException
	{
		// The source name could be a single name or something similar to a 'function' call, like
		// numModelsConnectToTarget({system},omegaRef)
		String[] nameParams = splitNameParams(a.getSourceName());
		String name = nameParams[0];

		switch (name)
		{
		case "numModelsConnectToTarget":
			if (nameParams.length < 3)
			{
				String msg = String.format(
						"Invalid reference [%s]: %s requires 2 parameters and %d were present",
						a.getSourceName(),
						name,
						nameParams.length - 1);
				LOG.error(msg);
				throw new UnresolvedReferenceException(a);
			}
			if (modelicaBuilder.haveAllDynamicModelsBeenAdded())
			{
				String targetModel = nameParams[1];
				String targetName = nameParams[2];
				return countHowManyModelsConnectToTarget(modelicaBuilder.getModels(), targetModel,
						targetName);
			}
			else
				throw new IncompleteReferenceException(a);
		default:
			throw new UnresolvedReferenceException(a);
		}
	}

	@Override
	public ModelicaInterconnection resolveConnectionTarget(
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
		else if (targetItem.equals("{element}"))
		{
			// Events can ask for a reference to the dynamic model of the element they are linked to
			targetModel = modelicaBuilder.getDynamicModelFor(sourceElement.getId());
		}
		// The target is my "bus" from the point of view of the sourceElement
		else if (targetItem.equals("{bus}"))
		{
			if (sourceElement instanceof Bus)
			{
				LOG.error("Bus connection should not have a reference to its bus in target {}:{}, sourceElement {}",
						targetItem,
						targetPin,
						sourceElement.getId());
				return null;
			}

			// If sourceElement is not a Bus,
			// Then it should be a SingleTerminalConnectable element
			// Someone that wants to connect to "its unique" bus
			SingleTerminalConnectable<?> e = (SingleTerminalConnectable<?>) sourceElement;
			Bus bus = e.getTerminal().getBusBreakerView().getBus();
			targetModel = modelicaBuilder.getDynamicModelFor(bus.getId());
		}
		else if (targetItem.startsWith("{bus"))
		{
			// We assume targetItem is either bus1 or bus2
			// We do not rely on ordinal of the enumeration items
			TwoTerminalsConnectable.Side side;
			if (targetItem.endsWith("1}")) side = TwoTerminalsConnectable.Side.ONE;
			else if (targetItem.endsWith("2}")) side = TwoTerminalsConnectable.Side.TWO;
			else return null;

			Bus bus = null;
			if (sourceElement instanceof TwoTerminalsConnectable)
			{
				TwoTerminalsConnectable<?> e = (TwoTerminalsConnectable<?>) sourceElement;
				bus = e.getTerminal(side).getBusBreakerView().getBus();
			}
			else if (sourceElement instanceof Switch)
			{
				Switch sw = (Switch) sourceElement;
				BusBreakerView bbv = sw.getVoltageLevel().getBusBreakerView();
				switch (side)
				{
				case ONE:
					bus = bbv.getBus1(sw.getId());
					break;
				case TWO:
					bus = bbv.getBus2(sw.getId());
					break;
				}
			}
			if (bus != null)
				targetModel = modelicaBuilder.getDynamicModelFor(bus.getId());
		}
		else 
		{
			// Assume the target model is referenced using the staticId of the element
			targetModel = modelicaBuilder.getDynamicModelFor(targetItem);
		}
		if (targetModel == null)
		{
			LOG.error(
					"Could not resolve target {}:{} for sourceElement {}",
					targetItem,
					targetPin,
					sourceElement.getId());
			return null;
		}

		Optional<ModelicaInterconnection> c = findInterconnection(
				targetPin,
				targetModel.getInterconnections());
		if (!c.isPresent())
		{
			LOG.error(
					"Could not resolve target pin {} in targetItem {} for sourceElement {}",
					targetPin,
					targetItem,
					sourceElement.getId());
			return null;
		}

		// All connectors that have been resolved through this resolver will receive a proper staticId
		String targetStaticId = targetModel.getStaticId();
		c.get().setStaticId(targetStaticId);
		return c.get();
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
								c0.getName(), c0.getComponentId(), var1, null, null, null, null);
						return c1;
					});
		}

		// Find by name
		c = Stream.of(connectors)
				.filter(c0 -> c0.getName() != null && c0.getName().equals(name))
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

	private String[] splitNameParams(String s)
	{
		int ps = s.indexOf('(');
		if (ps < 0)
		{
			String[] nameParams = { s };
			return nameParams;
		}
		else
		{
			String name = s.substring(0, ps);
			int pe = s.indexOf(')');
			if (pe < 0) pe = s.length();
			String[] params = s.substring(ps + 1, pe).split(",");
			String[] nameParams = new String[params.length + 1];
			nameParams[0] = name;
			System.arraycopy(params, 0, nameParams, 1, params.length);
			return nameParams;
		}
	}

	private long countHowManyModelsConnectToTarget(
			Collection<ModelicaModel> models,
			String targetModel,
			String targetName)
	{
		long count = models.stream()
				.map(m -> Arrays.asList(m.getInterconnections()).stream()
						.filter(i -> isTarget(i, targetModel, targetName))
						.count())
				.mapToLong(c -> c)
				.sum();
		return count;
	}

	private static boolean isTarget(
			ModelicaInterconnection i,
			String targetModel,
			String targetName)
	{
		if (i.getTargetModel() == null) return false;
		if (i.getTargetName() == null) return false;
		boolean isTargetModel = i.getTargetModel().equals(targetModel);
		boolean isTargetName = i.getTargetName().equals(targetName);
		return isTargetModel && isTargetName;
	}

	private final ModelicaBuilder	modelicaBuilder;

	private static final Logger		LOG	= LoggerFactory
			.getLogger(DynamicNetworkReferenceResolver.class);
}
