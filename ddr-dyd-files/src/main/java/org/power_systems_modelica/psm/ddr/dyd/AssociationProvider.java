package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.itesla_project.iidm.network.Identifiable;

public class AssociationProvider
{
	public Optional<Association> findAssociation(Identifiable<?> element)
	{
		return associations.stream()
				.filter(a -> belongs(a, element))
				.findFirst();
	}

	public void add(Association a)
	{
		associations.add(a);
	}

	private static boolean belongs(Association a, Identifiable<?> element)
	{
		return element.getId().matches(a.getPattern());
	}

	private final List<Association> associations = new ArrayList<>();
}
