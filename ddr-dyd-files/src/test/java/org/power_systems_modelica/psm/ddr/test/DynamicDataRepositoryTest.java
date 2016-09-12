package org.power_systems_modelica.psm.ddr.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.mockito.Mockito;
import org.power_systems_modelica.psm.ddr.ConnectionException;
import org.power_systems_modelica.psm.ddr.DynamicDataRepository;
import org.power_systems_modelica.psm.ddr.DynamicDataRepositoryMainFactory;
import org.power_systems_modelica.psm.modelica.ModelicaArgument;
import org.power_systems_modelica.psm.modelica.ModelicaConnect;
import org.power_systems_modelica.psm.modelica.ModelicaEquation;
import org.power_systems_modelica.psm.modelica.ModelicaModel;
import org.power_systems_modelica.psm.modelica.ModelicaModelInstantiation;

import eu.itesla_project.iidm.network.Identifiable;

public class DynamicDataRepositoryTest
{
	private static final Path TEST_SAMPLES = Paths.get(System.getenv("PSM_DATA"))
			.resolve("test");

	@Test
	public void testSample0() throws ConnectionException
	{
		String location = TEST_SAMPLES.resolve("sample0/BDD").toString();
		DynamicDataRepository ddr = DynamicDataRepositoryMainFactory.create("DYD", location);
		ddr.connect();
		Identifiable<?> e = Mockito.mock(Identifiable.class);
		Mockito.when(e.getId()).thenReturn("CIVAU7CIVAU1_NGU_SM");

		// Query the dynamic data repository
		ModelicaModel m = ddr.getModelicaModel(e);
		List<ModelicaModelInstantiation> is = m.getModelInstantiations();
		List<ModelicaEquation> eqs = m.getEquations();

		// Check the result (types and names of model instantiations)
		List<String> ts = is.stream().map(i -> i.getType()).collect(Collectors.toList());
		List<String> ns = is.stream().map(i -> i.getName()).collect(Collectors.toList());
		List<String> ets = Arrays.asList(
				"Dynamo.Electrical.Machines.DYNModelM1S",
				"Dynamo.Electrical.Controls.Machines.VoltageRegulator.DYNModelVoltageRegulator",
				"Dynamo.Electrical.Controls.Machines.DYNModelGover1",
				"Dynamo.Electrical.Controls.Machines.DYNModelRPCL",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint",
				"Dynamo.Electrical.Controls.Machines.DYNModelSetPoint");
		List<String> ens = Arrays.asList(
				"M1S_CIVAU7CIVAU1_NGU_SM",
				"VR_CIVAU7CIVAU1_NGU_SM",
				"GOVER_CIVAU7CIVAU1_NGU_SM",
				"RPCL_CIVAU7CIVAU1_NGU_SM",
				"SP_LAI_CIVAU7CIVAU1_NGU_SM",
				"SP_LIS_CIVAU7CIVAU1_NGU_SM",
				"SP_LIR_CIVAU7CIVAU1_NGU_SM");
		assertThat(ts, is(equalTo(ets)));
		assertThat(ns, is(equalTo(ens)));

		// Only check parameters for first model instantiation
		List<ModelicaArgument> as = is.get(0).getArguments();
		List<String> ans = as.stream().map(a -> a.getName()).collect(Collectors.toList());
		List<String> avs = as.stream().map(a -> a.getValue()).collect(Collectors.toList());
		List<String> eans = Arrays.asList(
				"mD0Pu",
				"rQ1Pu",
				"lDPu",
				"SNomTfo",
				"nQSat",
				"pPuWLMDV",
				"HIn",
				"DIn",
				"lStatIn",
				"rStatIn",
				"mCanPu",
				"xTfoIn",
				"nDSat",
				"PNom",
				"rDPu",
				"rTfoIn",
				"PNomAlt",
				"mQ0Pu",
				"mDSatIn",
				"lQ2Pu",
				"lRotIn",
				"mQSatIn",
				"lQ1Pu",
				"rQ2Pu",
				"rRotIn",
				"SNom",
				"P0",
				"Q0",
				"UNwBase",
				"UNwNomTfo",
				"UNomGenTfo",
				"uBMac",
				"U0",
				"UTeta0");
		List<String> eavs = Arrays.asList(
				"2.351",
				"0.01428",
				"0.13605",
				"1500",
				"5.57",
				"1500",
				"6.3",
				"0",
				"0.219",
				"0.00357",
				"0",
				"0.136",
				"5.57",
				"1500",
				"0.01771",
				"0",
				"1500",
				"2.351",
				"0.084",
				"0.10067",
				"0.21881",
				"0.084",
				"0.54458",
				"0.01842",
				"0.00088",
				"1500",
				"14.5452",
				"-0.6953555",
				"380.0",
				"380.0",
				"24",
				"24",
				"1.08269473684",
				"0.152089867247");
		assertThat(ans, is(equalTo(eans)));
		assertThat(avs, is(equalTo(eavs)));

		// Check only first equations
		ModelicaConnect eq0 = (ModelicaConnect) (eqs.get(0));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.EfdPU", eq0.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.efd", eq0.getRef2());
		ModelicaConnect eq1 = (ModelicaConnect) (eqs.get(1));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.UsPU", eq1.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.UReg", eq1.getRef2());
		ModelicaConnect eq2 = (ModelicaConnect) (eqs.get(2));
		assertEquals("VR_CIVAU7CIVAU1_NGU_SM.QsPU", eq2.getRef1());
		assertEquals("M1S_CIVAU7CIVAU1_NGU_SM.Qs", eq2.getRef2());
	}
}
