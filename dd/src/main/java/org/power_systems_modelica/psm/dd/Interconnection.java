package org.power_systems_modelica.psm.dd;

/*
 * #%L
 * Dynamic Data
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Objects;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class Interconnection
{
	public Interconnection(
			String name, String componentId, String componentVar,
			String targetModel, String targetName,
			String targetModel2, String targetName2)
	{
		this.name = name;
		this.componentId = componentId;
		this.componentVar = componentVar;
		this.targetModel = targetModel;
		this.targetName = targetName;
		this.targetModel2 = targetModel2;
		this.targetName2 = targetName2;
	}

	// A declaration of a point of interconnection where others can connect (a target)
	// One component of this model offers a variable where others can connect, with a public name
	public static Interconnection createReceiver(
			String name, String componentId, String componentVar)
	{
		Objects.requireNonNull(name);
		Objects.requireNonNull(componentVar);
		// componentId may be null (the variable is global)
		return new Interconnection(
				name, componentId, componentVar,
				null, null,
				null, null);
	}

	// A declaration of a point of interconnection that wants to reach a target
	// A variable of a component of this model wants to connect to a target
	public static Interconnection createSender(
			String componentId, String componentVar,
			String targetModel, String targetName)
	{
		Objects.requireNonNull(componentVar);
		Objects.requireNonNull(targetModel);
		Objects.requireNonNull(targetName);
		return new Interconnection(
				null, componentId, componentVar,
				targetModel, targetName,
				null, null);
	}

	// A declaration of an interconnection between two points of other models (declared on other models)
	// Used when defining coupling devices
	public static Interconnection createDoubleTarget(
			String targetModel, String targetName,
			String targetModel2, String targetName2)
	{
		return new Interconnection(
				null, null, null,
				targetModel, targetName,
				targetModel2, targetName2);
	}

	public String getName()
	{
		return name;
	}

	public String getComponentId()
	{
		return componentId;
	}

	public String getComponentVar()
	{
		return componentVar;
	}

	public String getTargetModel()
	{
		return targetModel;
	}

	public String getTargetName()
	{
		return targetName;
	}

	public String getTargetModel2()
	{
		return targetModel2;
	}

	public String getTargetName2()
	{
		return targetName2;
	}

	private final String	name;
	private final String	componentId;
	private final String	componentVar;
	private final String	targetModel;
	private final String	targetName;
	private final String	targetModel2;
	private final String	targetName2;
}
