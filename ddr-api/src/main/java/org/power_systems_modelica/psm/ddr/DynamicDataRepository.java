package org.power_systems_modelica.psm.ddr;

/*
 * #%L
 * Dynamic Data Repository API
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.power_systems_modelica.psm.dd.Event;
import org.power_systems_modelica.psm.dd.ModelForEvent.Injection;
import org.power_systems_modelica.psm.dd.Model;
import org.power_systems_modelica.psm.dd.Stage;
import org.power_systems_modelica.psm.dd.StaticType;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaSystemModel;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public interface DynamicDataRepository
{
	String getType();

	void setLocation(String location);

	String getLocation();

	void connect() throws ConnectionException;

	List<Model> getModels(Identifiable<?> e, Stage stage);
	ModelicaModel getModelicaModel(Identifiable<?> e, Stage stage);

	Optional<ModelicaModel> getSystemModel(Stage stage);

	List<ModelicaEquation> getSystemOtherEquationsInContext(
			ModelicaSystemModel m,
			Stage stage);

	ModelicaModel getModelicaModelForEvent(Event e);

	Collection<String> getEvents();

	Injection getEventInjection(String event);

	List<EventParameter> getEventParameters(String event);

	StaticType getEventAppliesToStaticType(String event);

	public static final String	EVENT_PARAMS_DATA_SOURCE	= "EVENT";
	public static final String	PREVIOUS_DYNAMIC_MODEL		= "PREVIOUS_DYNAMIC_MODEL";
}