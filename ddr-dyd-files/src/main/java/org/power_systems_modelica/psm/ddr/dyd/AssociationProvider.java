package org.power_systems_modelica.psm.ddr.dyd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.power_systems_modelica.psm.modelica.ModelicaUtil;

import eu.itesla_project.iidm.network.Identifiable;

public class AssociationProvider
{
	public Optional<Association> findAssociation(Identifiable<?> element)
	{
		String nid = ModelicaUtil.normalizedIdentifier(element.getId());
		return findAssociation(nid);
	}

	public Optional<Association> findAssociation(String id)
	{
		return associations.stream()
				.filter(a -> belongs(a, id))
				.findFirst();
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
