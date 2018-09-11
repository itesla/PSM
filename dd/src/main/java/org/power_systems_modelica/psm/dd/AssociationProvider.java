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
import java.util.Optional;
import java.util.stream.Stream;

import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import com.powsybl.iidm.network.Identifiable;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class AssociationProvider
{
	public Optional<Association> findAssociation(Identifiable<?> element)
	{
		return findAssociations(element).findFirst();
	}

	public Stream<Association> findAssociations(Identifiable<?> element)
	{
		String nid = ModelicaUtil.normalizedIdentifier(element.getId());
		return findAssociations(nid);
	}

	public Optional<Association> findAssociation(String id)
	{
		return findAssociations(id).findFirst();
	}

	public Stream<Association> findAssociations(String id)
	{
		return associations.stream()
				.filter(a -> belongs(a, id));
	}

	public void add(Association a)
	{
		associations.add(a);
	}

	private static boolean belongs(Association a, String id)
	{
		return id.matches(a.getPattern());
	}

	private final List<Association> associations = new ArrayList<>();
}
