package org.power_systems_modelica.psm.modelica.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.modelica.Annotation;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaArgumentReference;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaDeclaration;
import org.power_systems_modelica.psm.modelica.ModelicaDocument;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaUtil;
import org.power_systems_modelica.psm.modelica.builder.ModelicaBuilder;
import org.power_systems_modelica.psm.modelica.builder.ReferenceResolver;
import org.power_systems_modelica.psm.modelica.io.ModelicaTextPrinter;

public class ModelicaBuilderTest
{
	@Test
	public void testEmptyDocument()
	{
		MoBuilder mob = new MoBuilder("empty");
		ModelicaDocument mo = mob.getDoc();
		assertEquals(0, mo.getSystemModel().getDeclarations().size());
		assertEquals(0, mo.getSystemModel().getEquations().size());
	}

	@Test
	public void testSimpleDocument() throws Exception
	{
		// We build a simple document that contains only one model with two components and one equation and write it to a file
		// Then we make a copy of the model, remove everything from the document, add the copy to it and write again
		// We check that the written Modelica files are identical

		MoBuilder mob = new MoBuilder("simple");

		ModelicaModel m = simpleModelForStaticId("1");
		mob.addModel(m);
		assertSameDeclarationsAndEquations(m, mob.getDoc().getSystemModel());
		Path original = DATA_TMP.resolve("modelicaBuilderTest_original");
		mob.print(original);

		mob.removeModel(m);
		assertEquals(0, mob.getDoc().getSystemModel().getDeclarations().size());
		assertEquals(0, mob.getDoc().getSystemModel().getEquations().size());

		ModelicaModel mc = m.copy();
		assertSameDeclarationsAndEquations(m, mc);
		mob.addModel(mc);
		assertSameDeclarationsAndEquations(mc, mob.getDoc().getSystemModel());
		Path copy = DATA_TMP.resolve("modelicaBuilderTest_copy");
		mob.print(copy);

		ModelicaTestUtil.assertEqualsNormalizedModelicaText(original, copy);
	}

	@Test
	public void testGroupingModels()
	{
		MoBuilder mob = new MoBuilder("simple");
		ModelicaModel[] models = {
				simpleModelForStaticId("1"),
				simpleModelForStaticId("2") };
		for (ModelicaModel m : models)
			mob.addModel(m);
		Map<String, ModelicaModel> gmodels = ModelicaUtil.groupByNormalizedStaticId(mob.getDoc());
		// Number of expected models is the number of models we have added plus one for the interconnections
		boolean hasInterconnections = false;
		int expectedNumGroupedModels = models.length + (hasInterconnections ? 1 : 0);
		assertEquals(expectedNumGroupedModels, gmodels.keySet().size());

		for (ModelicaModel m : models)
		{
			ModelicaModel gm = gmodels.get(m.getStaticId());
			assertSameDeclarationsAndEquations(m, gm);
		}
	}

	private void assertSameDeclarationsAndEquations(ModelicaModel expected, ModelicaModel actual)
	{
		// TODO Declarations and equations should have equals implemented

		assertEquals(expected.getDeclarations().size(), actual.getDeclarations().size());
		for (int k = 0; k < expected.getDeclarations().size(); k++)
			assertEquals(expected.getDeclarations().get(k).getId(),
					actual.getDeclarations().get(k).getId());
		assertEquals(expected.getEquations().size(), actual.getEquations().size());
		for (int k = 0; k < expected.getEquations().size(); k++)
		{
			// Check only connect equations
			assertTrue(expected.getEquations().get(k) instanceof ModelicaConnect);
			assertTrue(actual.getEquations().get(k) instanceof ModelicaConnect);
			ModelicaConnect eqe = (ModelicaConnect) expected.getEquations().get(k);
			ModelicaConnect eqa = (ModelicaConnect) actual.getEquations().get(k);
			assertEquals(eqe.getRef1(), eqa.getRef1());
			assertEquals(eqe.getRef2(), eqa.getRef2());
		}
	}

	private ModelicaModel simpleModelForStaticId(String staticId)
	{
		// Build a document that contains components for different staticId's and then group them by staticId
		ModelicaModel m = new ModelicaModel(idFor("DM", staticId));
		m.setStaticId(staticId);
		List<ModelicaArgument> paramsc1a = new ArrayList<>();
		paramsc1a.add(new ModelicaArgument("ca_p0", "0"));
		paramsc1a.add(new ModelicaArgument("ca_p1", "1"));
		paramsc1a.add(new ModelicaArgument("ca_p2", "2"));
		boolean isparamc1a = false;
		Annotation annotationc1a = null;
		m.addDeclaration(new ModelicaDeclaration(
				"caType",
				idFor("ca", staticId),
				paramsc1a,
				isparamc1a,
				annotationc1a));
		List<ModelicaArgument> paramsc1b = new ArrayList<>();
		paramsc1b.add(new ModelicaArgumentReference("cb_p0", "REFS", idFor("cb_p0", staticId)));
		boolean isparamc1b = false;
		Annotation annotationc1b = null;
		m.addDeclaration(new ModelicaDeclaration(
				"cbType",
				idFor("cb", staticId),
				paramsc1b,
				isparamc1b,
				annotationc1b));
		ModelicaConnect eq = new ModelicaConnect(
				idFor("ca", staticId).concat(".p"),
				idFor("cb", staticId).concat(".p"));
		m.addEquation(eq);
		return m;
	}

	private String idFor(String baseId, String staticId)
	{
		return baseId.concat("_").concat(staticId);
	}

	static class MoBuilder extends ModelicaBuilder
	{
		MoBuilder(String name)
		{
			createModelicaDocument(name);
			registerResolver("REFS", new ReferenceResolver()
			{
				@Override
				public Object resolveReference(String name, ModelicaModel m, ModelicaDeclaration d)
				{
					switch (name)
					{
					case "cb_p0_1":
						return Math.PI;
					case "cb_p0_2":
						return Math.E;
					}
					return null;
				}
			});
		}

		public ModelicaDocument getDoc()
		{
			return getModelicaDocument();
		}

		public void addModel(ModelicaModel m)
		{
			addDynamicModel(m);
		}

		public void removeModel(ModelicaModel m)
		{
			removeDynamicModel(m);
		}

		public void print(Path path) throws Exception
		{
			ModelicaTextPrinter mop = new ModelicaTextPrinter(getDoc());
			mop.setIncludePsmAnnotations(true);
			try (PrintWriter out = new PrintWriter(path.toFile());)
			{
				mop.print(out);
			}
		}
	}

	public static final Path DATA_TMP = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");
}
