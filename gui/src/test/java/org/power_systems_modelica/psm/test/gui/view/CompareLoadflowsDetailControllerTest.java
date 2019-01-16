package org.power_systems_modelica.psm.test.gui.view;

/*
 * #%L
 * Power Systems on Modelica GUI
 * %%
 * Copyright (C) 2017 - 2018 RTE (http://rte-france.com)
 * %%
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * #L%
 */

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.power_systems_modelica.psm.commons.CsvReader;
import org.power_systems_modelica.psm.commons.CsvReaderPopulator;
import org.power_systems_modelica.psm.commons.PathUtils;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.fx.UtilsFX;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsDetailController;
import org.supercsv.io.ICsvListReader;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class CompareLoadflowsDetailControllerTest extends ApplicationTest
{

	@Override
	public void start(Stage stage) throws Exception
	{

		FXMLLoader loader = null;
		try
		{

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CompareLoadflowsDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);

			Scene scene = new Scene(workflowsOverview);
			stage.setScene(scene);
			stage.show();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testAddSeries()
	{

		WorkflowResult results = new WorkflowResult();

		List<BusData> allBusesValues = new ArrayList<>();
		for (int i = 1; i <= 14; i++)
		{
			Map<String, double[]> bvalues = new HashMap<>();
			double[] Vs = new double[2];
			double[] As = new double[2];
			double[] Ps = new double[2];
			double[] Qs = new double[2];

			Vs[0] = 1.0f;
			As[0] = 2.0f;
			Ps[0] = 10.0f;
			Qs[0] = 10.0f;
			Vs[1] = 1.1f;
			As[1] = 2.1f;
			Ps[1] = 10.1f;
			Qs[1] = 10.1f;
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.add(new BusData("" + i, "bus__BUS___" + i + "_TN", bvalues));
		}

		allBusesValues.forEach(bv -> {
			double[] values = bv.getData().get("V");
			double err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("V", err);
			values = bv.getData().get("A");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("A", err);
			values = bv.getData().get("P");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("P", err);
			values = bv.getData().get("Q");
			err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
			bv.setError("Q", err);
		});

		results.setId("1");
		results.setAllBusesValues(allBusesValues);

		try
		{
			Optional<Path> path = Files
					.walk(Paths.get(
							PathUtils.DATA_TEST.resolve("ieee14").resolve("expected").toString()),
							1, FileVisitOption.FOLLOW_LINKS)
					.filter((p) -> !p.toFile().isDirectory()
							&& p.toFile().getAbsolutePath().endsWith(".csv"))
					.findFirst();
			if (path.isPresent())
			{

				Map<String, List<DsData>> values = CsvReader.readVariableColumnsWithCsvListReader(
						path.get().toFile(),
						new CsvReaderPopulator<DsData>()
						{

							@Override
							public void prepare(ICsvListReader listReader,
									Map<String, List<DsData>> values)
							{
								columns = listReader.length();
								columnNames = new String[columns];
								for (int i = 2; i <= columns; i++)
								{
									List<DsData> dsData = new ArrayList<DsData>();
									columnNames[i - 1] = listReader.get(i);
									values.put(columnNames[i - 1], dsData);
								}
							}

							@Override
							public void populate(List<Object> columnValues,
									Map<String, List<DsData>> values)
							{
								Double time = (Double) columnValues.get(0);
								for (int i = 1; i < columns; i++)
								{
									List<DsData> dsData = values.get(columnNames[i]);
									dsData.add(new DsData(time, (Double) columnValues.get(i)));
								}
							}

							private int	columns;
							private String[]	columnNames;
						});
				results.setDsValues(values);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		interact(new Runnable()
		{

			@Override
			public void run()
			{

				Label avgVoltageDiffLabel = lookup("#avgVoltageDiffLabel").query();
				Label maxVoltageDiffLabel = lookup("#maxVoltageDiffLabel").query();

				ScatterChart<String, Number> voltageDiffChart = lookup("#voltageDiffChart").query();
				ScatterChart<String, Number> phaseDiffChart = lookup("#phaseDiffChart").query();
				ScatterChart<String, Number> activeDiffChart = lookup("#activeDiffChart").query();
				ScatterChart<String, Number> reactiveDiffChart = lookup("#reactiveDiffChart")
						.query();
				ScatterChart<String, Number> voltageCurvesChart = lookup("#voltageCurvesChart")
						.query();
				ScatterChart<String, Number> phaseCurvesChart = lookup("#phaseCurvesChart").query();
				ScatterChart<String, Number> activeCurvesChart = lookup("#activeCurvesChart")
						.query();
				ScatterChart<String, Number> reactiveCurvesChart = lookup("#reactiveCurvesChart")
						.query();

				DoubleSummaryStatistics voltageStats = results.getAllBusesValues().stream()
						.map(bus -> bus.getAbsError("V"))
						.collect(Collectors.summarizingDouble(Double::doubleValue));
				avgVoltageDiffLabel
						.setText(String.format("%,.4f%%", voltageStats.getAverage() * 100));
				maxVoltageDiffLabel.setText(String.format("%,.4f%%", voltageStats.getMax() * 100));

				controller.addDiffSeries(results);
				controller.addCurvesSeries(results);
				UtilsFX.addTooltipComparisonChart(voltageDiffChart, results, "V", "pu");
				UtilsFX.addTooltipComparisonChart(phaseDiffChart, results, "A", "deg");
				UtilsFX.addTooltipComparisonChart(activeDiffChart, results, "P", "MW");
				UtilsFX.addTooltipComparisonChart(reactiveDiffChart, results, "Q", "MVar");
				UtilsFX.addTooltipScatterChart(voltageCurvesChart, "pu");
				UtilsFX.addTooltipScatterChart(phaseCurvesChart, "deg");
				UtilsFX.addTooltipScatterChart(activeCurvesChart, "MW");
				UtilsFX.addTooltipScatterChart(reactiveCurvesChart, "MVar");

				assertEquals(1, voltageDiffChart.getData().size());
				XYChart.Series<String, Number> valuesV = voltageDiffChart.getData().get(0);
				assertEquals(14, valuesV.getData().size());
				assertEquals(1, phaseDiffChart.getData().size());
				XYChart.Series<String, Number> valuesA = phaseDiffChart.getData().get(0);
				assertEquals(14, valuesA.getData().size());
				assertEquals(1, activeDiffChart.getData().size());
				XYChart.Series<String, Number> valuesP = activeDiffChart.getData().get(0);
				assertEquals(14, valuesP.getData().size());
				assertEquals(1, reactiveDiffChart.getData().size());
				XYChart.Series<String, Number> valuesQ = reactiveDiffChart.getData().get(0);
				assertEquals(14, valuesQ.getData().size());
			}
		});

	}

	private CompareLoadflowsDetailController controller;
}
