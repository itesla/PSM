package org.power_systems_modelica.psm.modelica;

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
		throw new RuntimeException("replaceModelForStaticId");
	}

	private String				within;
	private ModelicaSystemModel	model;
}
