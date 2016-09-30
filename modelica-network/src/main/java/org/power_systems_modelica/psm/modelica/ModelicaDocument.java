package org.power_systems_modelica.psm.modelica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ModelicaDocument
{
	public void setWithin(String within)
	{
		this.within = within;
	}

	public String getWithin()
	{
		return within;
	}

	public void setSystemModel(ModelicaSystemModel m)
	{
		model = m;
	}

	public ModelicaSystemModel getSystemModel()
	{
		return model;
	}

	public ModelicaDocument shallowCopy()
	{
		ModelicaDocument mo = new ModelicaDocument();
		mo.setWithin(this.getWithin());
		ModelicaSystemModel m = new ModelicaSystemModel(this.getSystemModel().getName());
		m.addDeclarations(this.getSystemModel().getDeclarations());
		m.addEquations(this.getSystemModel().getEquations());
		mo.setSystemModel(m);
		return mo;
	}

	public void replaceModelForStaticId(String id, ModelicaModel m)
	{
		// FIXME Models should be organized by id to be easily removed (see groupInModels in dyd-files-from-modelica)
		Collection<?> connectors = otherSides(getConnectors(id));
		removeModelForStaticId(id);
		addModelForStaticId(id, m, connectors);
	}

	public void removeModelForStaticId(String id)
	{
		// FIXME remove
		throw new RuntimeException("removeModelForStaticId");
	}

	public Collection<ModelicaConnector> getConnectors(String id)
	{
		Collection<ModelicaConnector> connectors = new ArrayList<>();
		if (connectors.isEmpty()) throw new RuntimeException("getConnectors");
		return connectors;
	}

	public void addModelForStaticId(String id, ModelicaModel m)
	{
		// FIXME We assume the new model will be connected to the existing connectors of an already model for staticId
		Collection<ModelicaConnector> connectors = getConnectors(id);
		addModelForStaticId(id, m, connectors);
	}

	private void addModelForStaticId(String id, ModelicaModel m, Collection<?> connectors)
	{
		// FIXME connect m.connectors with connectors

		// FIXME add m.components to the list of components for the staticId (if already had some)
		// FIXME add m.equations to the list of components for the staticId

		throw new RuntimeException("addModelForStaticId");
	}

	private Collection<ModelicaConnector> otherSides(Collection<ModelicaConnector> connectors)
	{
		return connectors.stream().map(c -> otherSide(c)).collect(Collectors.toList());
	}

	private ModelicaConnector otherSide(ModelicaConnector c)
	{
		// FIXME otherSide solo esta bien definido si este c no es re-usable (mapping 1-1)
		// FIXME si nos piden el otherSide de un connector reusable (1-n) nos tendrian que decir mas datos para encontrar el otro lado
		// FIXME Assuming that we have a list of connections
		// ModelicaConnector other = model.getConnections().find(c).other(c);
		ModelicaConnector other = null;
		return other;
	}

	private String				within;
	private ModelicaSystemModel	model;
}
