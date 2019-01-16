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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.BaseModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaAssignment;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaInterconnection;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class DD
{
	public static String dynamicId(String baseId, String staticId)
	{
		if (baseId == null) return null;
		String nstaticId = ModelicaUtil.normalizedIdentifier(staticId);
		String dynamicId = baseId.replace("{staticId}", nstaticId);
		return dynamicId;
	}

	public static ModelicaModel buildModelicaModelFromDynamicModelDefinition(
			Model mdef,
			String staticId,
			ParameterSetProvider parameters,
			ModelProvider dynamicModels)
	{
		ModelicaModel m = new ModelicaModel(dynamicId(mdef.getId(), staticId));
		m.setStaticId(staticId);
		m.setOrigin(mdef.getOrigin());

		List<BaseModelicaDeclaration> ds = new ArrayList<>();
		for (Component c : mdef.getComponents())
		{
			String type = c.getType();
			String did = dynamicId(c.getId(), staticId);
			List<ModelicaArgument> arguments = buildModelicaArguments(c, staticId, parameters);
			Annotation annotation = null;

			boolean isParameter = c.isParameter();
			if (arguments != null)
				ds.add(new ModelicaDeclaration(type, did, arguments, isParameter, annotation));
			else
				ds.add(new ModelicaAssignment(type, did, c.getValue(), isParameter, annotation));
		}
		m.addDeclarations(ds);
		ds.forEach(d -> d.setOrigin(mdef.getOrigin()));

		m.setInterconnections(mdef.getInterconnections().stream()
				.map(ic -> new ModelicaInterconnection(
						ic.getName(),
						dynamicId(ic.getComponentId(), staticId),
						ic.getComponentVar(),
						ic.getTargetModel(),
						ic.getTargetName(),
						ic.getTargetModel2(),
						ic.getTargetName2()))
				.collect(Collectors.toList()));

		List<ModelicaEquation> meqs = new ArrayList<>(mdef.getConnections().size());
		for (Connection c : mdef.getConnections())
		{
			if (c.getIdMacro() == null) 
			{
				String ref1 = ModelicaUtil.idvar2ref(dynamicId(c.getId1(), staticId), c.getVar1());
				String ref2 = ModelicaUtil.idvar2ref(dynamicId(c.getId2(), staticId), c.getVar2());
				meqs.add(new ModelicaConnect(ref1, ref2));
			}
			else
			{
				Model association = getDynamicModelForAssociation(dynamicModels, c.getIdMacro());
				for (Connection cx : association.getConnections())
				{
					String ref1 = ModelicaUtil.idvar2ref(dynamicId(c.getId1(), staticId), cx.getVar1());
					String ref2 = ModelicaUtil.idvar2ref(dynamicId(c.getId2(), staticId), cx.getVar2());
					meqs.add(new ModelicaConnect(ref1, ref2));
				}
			}
		}
		m.addEquations(meqs);

		return m;
	}

	private static List<ModelicaArgument> buildModelicaArguments(
			Component c,
			String staticId,
			ParameterSetProvider parameters)
	{
		ParameterSet set = getParameterSet(c, staticId, parameters);
		List<ModelicaArgument> arguments = null;
		if (set != null) 
		{
			arguments = new ArrayList<>(set.getParameters().size());
			for (Parameter p : set.getParameters())
			{
				ModelicaArgument a = null;
				if (p instanceof ParameterValue) a = new ModelicaArgument(
						p.getName(),
						((ParameterValue) p).getValue());
				else if (p instanceof ParameterReference) a = new ModelicaArgumentReference(
						p.getName(),
						((ParameterReference) p).getDataSource(),
						((ParameterReference) p).getSourceName());
				if (a != null) arguments.add(a);
			}
		}
		List<ModelicaArgument> args = buildModelicaReferenceArguments(c, staticId, parameters);
		if (arguments == null) arguments = args;
		else if (args != null) arguments.addAll(args);
		return arguments;
	}

	private static List<ModelicaArgument> buildModelicaReferenceArguments(
			Component c,
			String staticId,
			ParameterSetProvider parameters)
	{
		ParameterSet set = getParameterSetReference(c, staticId, parameters);
		if (set == null) return null;

		List<ModelicaArgument> arguments = new ArrayList<>(set.getParameters().size());
		for (Parameter p : set.getParameters())
		{
			ModelicaArgument a = null;
			if (p instanceof ParameterValue) a = new ModelicaArgument(
					p.getName(),
					((ParameterValue) p).getValue());
			else if (p instanceof ParameterReference) a = new ModelicaArgumentReference(
					p.getName(),
					((ParameterReference) p).getDataSource(),
					((ParameterReference) p).getSourceName());
			if (a != null) arguments.add(a);
		}
		return arguments;
	}

	private static Model getDynamicModelForAssociation(ModelProvider dynamicModels, String associationId)
	{
		return dynamicModels.getDynamicModelForAssociation(associationId);
	}

	private static ParameterSet getParameterSet(
			Component c,
			String staticId,
			ParameterSetProvider parameters)
	{
		ParameterSet set = c.getParameterSet();
		return set;
	}

	private static ParameterSet getParameterSetReference(
			Component c,
			String staticId,
			ParameterSetProvider parameters)
	{
		ParameterSet set = null;
		ParameterSetReference psetRef = c.getParameterSetReference();
		if (psetRef != null) set = parameters.get(psetRef, staticId);
		return set;
	}
}
