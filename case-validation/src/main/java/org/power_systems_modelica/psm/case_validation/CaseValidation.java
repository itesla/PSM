package org.power_systems_modelica.psm.case_validation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.case_validation.model.VariableValidation;
import org.power_systems_modelica.psm.commons.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaseValidation
{

	public static final Path DATA_TMP = Paths
			.get(System.getenv("PSM_DATA"))
			.resolve("tmp");

	public CaseValidation()
	{
		rr = new CaseValidationLoader(result);
	}

	public void configure(Configuration config)
	{
		this.stepSize = Double
				.valueOf(Optional.ofNullable(config.getParameter("stepSize"))
						.orElse(this.properties.getProperty("stepSize")));
		pathNamesMapping = config.getParameter("pathNamesMapping");
		pathRefDat = config.getParameter("pathRefData");
		pathModelData = config.getParameter("pathModelicaData");

	}

	public void calculate() throws IOException
	{

		rr.loadNamesMapping(stepSize, pathNamesMapping);
		rr.loadModelicaData(stepSize, pathModelData);
		rr.loadRefData(stepSize, pathRefDat);
		rr.calculateDiff();
	}

	public void validation()
			throws IOException
	{

		StringBuilder relRmesw = new StringBuilder();
		StringBuilder absRmesw = new StringBuilder();

		CompareData compare = new CompareData(absThreshold, relThreshold, toleranceTh, result);
		compare.compareValues();

		valuesTest.forEach(v -> {
			VariableValidation vv = new VariableValidation();
			vv.setName(v);
			result.addVariable(vv);
		});

		int nElements = result.getElements().size();
		int nValuesElement = ((Element) result.getElements().values().iterator().next()).getValues()
				.size();
		int totalValues = nElements * nValuesElement;

		List<String> elements = new ArrayList<>();
		elements.addAll(result.getElements().keySet());

		result.getVariables().forEach(vv -> {

			elements.forEach(ke -> {

				Element e = result.getElements().get(ke);
				if (e.getRefName().contains(vv.getName()))
				{
					vv.addSteps(e.getValues().size());
					vv.addAbsOffset(e.getAbsOffset());
					vv.addRelOffset(e.getRelOffset());

					if (e.getAbsRmes() > absThreshold)
						vv.incRmesKo();
				}
			});

			double offset = vv.getAbsOffset();
			vv.setAbsOffset(offset / nValuesElement);
			vv.incAbsTotalOffset(offset / totalValues);

			offset = vv.getRelOffset();
			vv.setRelOffset(offset / nValuesElement);
			vv.incRelTotalOffset(offset / totalValues);

			vv.setRmesAbove(vv.getRmesKo() / totalValues);
		});

		rr.getOutputNames().forEach(ke -> {

			Element e = result.getElements().get(ke);

			absRmesw.append(e.getRefName() + "	" + e.getAbsRmes() + "\n");
			relRmesw.append(e.getRefName() + "	" + e.getRelRmes() + "\n");
		});

		result.getVariables().forEach(vv -> {

			LOG.debug(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getAbsOffset() * 100.0f + " % Total " + vv.getName() + " points: "
					+ vv.getSteps());
		});

		LOG.debug("**************** Validation absolute *******************");

		result.getVariables().forEach(vv -> {

			LOG.debug("RMES Above Threshold " + ": " + vv.getRmesAbove() + " % ");
			LOG.debug(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getAbsTotalOffset() * 100.0f + " % " + "points: " + totalValues);
		});

		LOG.debug("**************** Validation relative *******************");
		result.getVariables().forEach(vv -> {

			LOG.debug(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getRelTotalOffset() * 100.0f + " % " + "points: " + totalValues);
		});

		if (writeFile)
		{
			BufferedWriter rmescsv = new BufferedWriter(
					new FileWriter(DATA_TMP.resolve("RMES1.csv").toString()));
			rmescsv.write(absRmesw.toString());
			rmescsv.close();
			rmescsv = new BufferedWriter(new FileWriter(DATA_TMP.resolve("RMES0.csv").toString()));
			rmescsv.write(relRmesw.toString());
			rmescsv.close();
		}
	}

	public CaseValidationLoader getCaseValidationLoader()
	{
		return rr;
	}

	public ValidationResult getResult()
	{
		return result;
	}

	private Properties loadDefaultProperties()
	{
		Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(DEF_PROPERTIES))
		{
			properties.load(inputStream);

			this.stepSize = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("stepSize"))
							.orElse("0.0001"));

			this.toleranceTh = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("toleranceTh"))
							.orElse("1.5"));
			this.absThreshold = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("absThreshold"))
							.orElse("0.001"));
			this.relThreshold = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("relThreshold"))
							.orElse("0.011"));

			if (!properties.containsKey("variables"))
			{
				String[] v = { "V", "angle", "P", "Q", "efd", "cm", "lambdad", "lambdaq1",
						"lambdaf", "lambdaq2" };

				this.valuesTest = Arrays.asList(v);
			}
			else
			{
				this.valuesTest = new ArrayList<>();
				for (int i = 1; i <= Integer.parseInt(properties.getProperty("variables")); i++)
				{
					String v = properties.getProperty("variables.variable" + i);
					this.valuesTest.add(v);
				}
			}
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}
		return properties;
	}

	private Properties				properties		= loadDefaultProperties();
	private double					stepSize;
	private String					pathRefDat;
	private String					pathModelData;
	private String					pathNamesMapping;
	private double					toleranceTh;
	private double					absThreshold;
	private double					relThreshold;
	private List<String>			valuesTest;

	private CaseValidationLoader	rr;
	private ValidationResult		result			= new ValidationResult();
	private boolean					writeFile		= false;

	private static final Path		DEF_PROPERTIES	= Paths.get(System.getenv("PSM_DATA"))
			.resolve("test").resolve("cfg").resolve("case-validation.properties");
	private static final Logger		LOG				= LoggerFactory
			.getLogger(CaseValidation.class);
}
