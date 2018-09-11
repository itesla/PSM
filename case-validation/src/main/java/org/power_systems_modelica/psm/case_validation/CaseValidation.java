package org.power_systems_modelica.psm.case_validation;

/*
 * #%L
 * Case Validation
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.power_systems_modelica.psm.case_validation.model.ComparisionData;
import org.power_systems_modelica.psm.case_validation.model.Element;
import org.power_systems_modelica.psm.case_validation.model.TimeValue;
import org.power_systems_modelica.psm.case_validation.model.ValidationResult;
import org.power_systems_modelica.psm.case_validation.model.VariableValidation;
import org.power_systems_modelica.psm.commons.Configuration;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luma Zamarreño <zamarrenolm at aia.es>
 */
public class CaseValidation
{
	public static Map<Double, ComparisionData> calculateElementValues(String refPath,
			String casePath,
			Double stepSize, Element element) throws IOException
	{
		double toleranceTh = 1.5;
		double absThreshold = 0.001;

		Properties properties = new Properties();
		try (InputStream inputStream = Files
				.newInputStream(PathUtils.CONFIG.resolve("case-validation.properties")))
		{
			properties.load(inputStream);

			toleranceTh = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("toleranceTh"))
							.orElse("1.5"));
			absThreshold = Double
					.parseDouble(Optional.ofNullable(properties.getProperty("absThreshold"))
							.orElse("0.001"));
		}
		catch (IOException e)
		{
			LOG.error("", e.getMessage());
		}

		ValidationResult result = new ValidationResult();
		CaseValidationLoader rr = new CaseValidationLoader(result, false, false, PathUtils.DATA_TMP.toString());
		Map<String, List<TimeValue>> refData = rr.loadCvsData(refPath);
		Map<String, List<TimeValue>> modelicaData = rr.loadCvsData(casePath);

		return rr.calculateElementDiff(element, stepSize, refData, modelicaData, absThreshold,
				toleranceTh);
	}

	public CaseValidation(Configuration config)
	{
		configure(config);
		rr = new CaseValidationLoader(result, debugFile, writeFile, pathOutput);
	}

	public void configure(Configuration config)
	{
		this.stepSize = Double
				.valueOf(Optional.ofNullable(config.getParameter("stepSize"))
						.orElse(this.properties.getProperty("stepSize")));
		pathNamesMapping = config.getParameter("pathNamesMapping");
		pathRefDat = config.getParameter("pathRefData");
		pathModelData = config.getParameter("pathModelicaData");

		if (config.getParameter("writeFile") != null)
			writeFile = Boolean.parseBoolean(config.getParameter("writeFile"));
		if (config.getParameter("pathOutput") != null)
			pathOutput = config.getParameter("pathOutput");

	}

	public void calculate() throws IOException
	{
		rr.loadNamesMapping(stepSize, pathNamesMapping);

		rr.prepareOutputNames(pathRefDat);
		Map<String, List<TimeValue>> refData = rr.loadCvsData(pathRefDat);
		Map<String, List<TimeValue>> modelicaData = rr.loadCvsData(pathModelData);

		calculateStepSize(refData);

		rr.calculateDiff(stepSize, refData, modelicaData, absThreshold, toleranceTh);
	}

	private void calculateStepSize(Map<String, List<TimeValue>> refData) throws IOException
	{
		result.setOriginalStepSize(stepSize);
		result.setFinalStepSize(stepSize);
		List<TimeValue> time = refData.values().iterator().next();
		time.sort(Comparator.comparingDouble(TimeValue::getTime));

		double avg = 0.0;
		for (int i = 1; i < time.size(); i++)
		{
			avg += time.get(i).getTime() - time.get(i - 1).getTime();
		}
		double refStepSize = avg / (time.size() - 1);
		if (stepSize / refStepSize < 0.9)
		{
			result.setFinalStepSize(refStepSize);
			stepSize = refStepSize;
		}

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

		int nElements = result.getElements().keySet().stream()
				.filter(k -> !k.equalsIgnoreCase("Time")).collect(Collectors.toList()).size();
		int nValuesElement = ((Element) result.getElements().values().iterator().next()).getValues()
				.size();
		int totalValues = nElements * nValuesElement;

		List<String> elements = new ArrayList<>();
		elements.addAll(result.getElements().keySet());

		result.getVariables().forEach(vv -> {

			elements.forEach(ke -> {

				Element e = result.getElements().get(ke);
				if (e.getRefName().endsWith(vv.getName()))
				{
					vv.addSteps(e.getValues().size());
					vv.addAbsOffset(e.getAbsOffset());
					vv.addRelOffset(e.getRelOffset());

					vv.incRmesElements();
					if (e.getAbsRmes() > absThreshold)
						vv.incRmesKo();
				}
			});

			double offset = vv.getAbsOffset();
			vv.incAbsTotalOffset(offset / (double) totalValues);

			offset = vv.getRelOffset();
			vv.incRelTotalOffset(offset / (double) totalValues);

			vv.setRmesAbove(vv.getRmesKo() / (double) nElements);
		});

		rr.getOutputNames().forEach(ke -> {

			Element e = result.getElements().get(ke);

			absRmesw.append(e.getRefName() + "	" + e.getAbsRmes() + "\n");
			relRmesw.append(e.getRefName() + "	" + e.getRelRmes() + "\n");
		});

		result.getVariables().forEach(vv -> {

			LOG.debug(vv.getName() + " Points Above Thresold Error " + ": "
					+ vv.getAbsOffset() + " Total " + vv.getName() + " points: "
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
		
		if (debugFile)
		{

			BufferedWriter rmescsv = new BufferedWriter(
					new FileWriter(Paths.get(pathOutput).resolve("RMES1.csv").toString()));
			rmescsv.write(absRmesw.toString());
			rmescsv.close();
			rmescsv = new BufferedWriter(
					new FileWriter(Paths.get(pathOutput).resolve("RMES0.csv").toString()));
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
		try (InputStream inputStream = Files
				.newInputStream(PathUtils.CONFIG.resolve("case-validation.properties")))
		{
			properties.load(inputStream);

			this.debugFile = Boolean
					.parseBoolean(Optional.ofNullable(properties.getProperty("debugFile"))
							.orElse("false"));

			this.writeFile = Boolean
					.parseBoolean(Optional.ofNullable(properties.getProperty("writeFile"))
							.orElse("false"));

			this.pathOutput = Optional.ofNullable(properties.getProperty("pathOutput"))
					.orElse(PathUtils.DATA_TMP.toString());

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

	public void clear()
	{
		result.getElements().values().forEach(e -> e.resetValues());
	}

	private Properties				properties	= loadDefaultProperties();
	private double					stepSize;
	private String					pathRefDat;
	private String					pathModelData;
	private String					pathNamesMapping;
	private double					toleranceTh;
	private double					absThreshold;
	private double					relThreshold;
	private List<String>			valuesTest;
	private boolean					writeFile;
	private String					pathOutput;
	private boolean					debugFile;

	private CaseValidationLoader	rr;
	private ValidationResult		result		= new ValidationResult();

	private static final Logger		LOG			= LoggerFactory
			.getLogger(CaseValidation.class);

}
