package org.power_systems_modelica.psm.modelica;

/*
 * #%L
 * Modelica network model
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class ModelicaModel
{
	public ModelicaModel(String id)
	{
		this.id = Objects.requireNonNull(id);
	}

	public String getId()
	{
		return id;
	}

	public void setStaticId(String staticId)
	{
		this.staticId = staticId;
	}

	public String getStaticId()
	{
		return this.staticId;
	}

	public void setInterconnections(List<ModelicaInterconnection> is)
	{
		this.interconnections = new ModelicaInterconnection[is.size()];
		this.interconnections = is.toArray(this.interconnections);
	}

	public void addDeclarations(List<BaseModelicaDeclaration> declarations)
	{
		Objects.requireNonNull(declarations);
		this.declarations.addAll(declarations);
	}

	public void addDeclaration(BaseModelicaDeclaration declaration)
	{
		Objects.requireNonNull(declaration);
		this.declarations.add(declaration);
	}

	public List<BaseModelicaDeclaration> getDeclarations()
	{
		return Collections.unmodifiableList(declarations);
	}

	public void removeDeclarations(List<BaseModelicaDeclaration> declarations)
	{
		Objects.requireNonNull(declarations);
		// Received declarations are not the same objects that are put inside the model
		// Declarations inside the model have been re-created with references resolved
		// We can not do:
		// this.declarations.removeAll(declarations);
		List<String> removeIds = declarations.stream()
				.map(BaseModelicaDeclaration::getId)
				.collect(Collectors.toList());
		this.declarations = this.declarations.stream()
				.filter(d -> !removeIds.contains(d.getId()))
				.collect(Collectors.toList());
	}

	public void addEquations(List<ModelicaEquation> equations)
	{
		Objects.requireNonNull(equations);
		this.equations.addAll(equations);
	}

	public void addEquation(ModelicaEquation equation)
	{
		if (equation != null) this.equations.add(equation);
	}

	public List<ModelicaEquation> getEquations()
	{
		return Collections.unmodifiableList(equations);
	}

	public void removeEquations(List<ModelicaEquation> equations)
	{
		Objects.requireNonNull(equations);
		this.equations.removeAll(equations);
	}

	public Annotation getAnnotation()
	{
		return annotation;
	}

	public ModelicaInterconnection[] getInterconnections()
	{
		return interconnections;
	}

	public ModelicaModel copy()
	{
		ModelicaModel m = new ModelicaModel(getId());
		copy(this, m);
		return m;
	}

	public static void copy(ModelicaModel s, ModelicaModel t)
	{
		t.setStaticId(s.getStaticId());

		// Interconnections, Declarations and Equations are immutable objects
		// There is no need to make deep copies of them
		t.addDeclarations(s.getDeclarations());
		t.addEquations(s.getEquations());
		t.getAnnotation().addItems(s.getAnnotation().getItems());
		if (s.interconnections != null) t.interconnections = s.interconnections.clone();
	}

	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	public String getOrigin()
	{
		return origin;
	}

	private final String					id;

	private String							staticId;
	private List<BaseModelicaDeclaration>	declarations		= new ArrayList<>();
	private List<ModelicaEquation>			equations			= new ArrayList<>();
	private Annotation						annotation			= new Annotation();
	private ModelicaInterconnection[]		interconnections	= new ModelicaInterconnection[0];

	private String							origin;
}
