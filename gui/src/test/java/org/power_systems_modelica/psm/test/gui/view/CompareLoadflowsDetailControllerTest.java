package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CsvReader;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.view.CompareLoadflowsDetailController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CompareLoadflowsDetailControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddSeries() {

		WorkflowResult results = new WorkflowResult();

		List<BusData> allBusesValues = new ArrayList<>();
		for (int i = 1; i <= 14; i++) {
			Map<String, float[]> bvalues = new HashMap<>();
			float[] Vs = new float[2];
			float[] As = new float[2];
			float[] Ps = new float[2];
			float[] Qs = new float[2];

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
			float[] values = bv.getData().get("V");
			float err = (values[0] - values[1]) / (values[0] != 0.0f ? values[0] : 1.0f);
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
			Map<String, List<DsData>> values = CsvReader.readVariableColumnsWithCsvListReader(
					PathUtils.DATA_TEST.resolve("ieee14").resolve("expected").toString(), ".csv");
			results.setDsValues(values);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		interact(new Runnable() {

			@Override
			public void run() {
				
				Label avgVoltageErrorLabel = lookup("#avgVoltageErrorLabel").query();
				Label maxVoltageErrorLabel = lookup("#maxVoltageErrorLabel").query();
				
				ScatterChart voltageChart = lookup("#voltageChart").query();
				ScatterChart phaseChart = lookup("#phaseChart").query();
				ScatterChart activeChart = lookup("#activeChart").query();
				ScatterChart reactiveChart = lookup("#reactiveChart").query();
				
				DoubleSummaryStatistics voltageStats = results.getAllBusesValues().stream().map(bus -> bus.getAbsError("V"))
						.collect(Collectors.summarizingDouble(Float::doubleValue));
				
			    avgVoltageErrorLabel.setText(String.format("%,.4f%%", voltageStats.getAverage()*100));	
				maxVoltageErrorLabel.setText(String.format("%,.4f%%", voltageStats.getMax()*100));	

				DoubleSummaryStatistics phaseStats = results.getAllBusesValues().stream().map(bus -> bus.getAbsError("A"))
						.collect(Collectors.summarizingDouble(Float::doubleValue));
				

				DoubleSummaryStatistics activeStats = results.getAllBusesValues().stream().map(bus -> bus.getAbsError("P"))
						.collect(Collectors.summarizingDouble(Float::doubleValue));
				

				DoubleSummaryStatistics reactiveStats = results.getAllBusesValues().stream().map(bus -> bus.getAbsError("Q"))
						.collect(Collectors.summarizingDouble(Float::doubleValue));
				

				controller.addSeries(results);
				Utils.addTooltipComparisonChart(voltageChart, results, "V", "pu");
				Utils.addTooltipComparisonChart(phaseChart, results, "A", "º");
				Utils.addTooltipComparisonChart(activeChart, results, "P", "MW");
				Utils.addTooltipComparisonChart(reactiveChart, results, "Q", "MVar");
				
				assertEquals(1, voltageChart.getData().size());
				XYChart.Series<String, Float> valuesV = (Series<String, Float>) voltageChart.getData().get(0);
				assertEquals(14, valuesV.getData().size());
				assertEquals(1, phaseChart.getData().size());
				XYChart.Series<String, Float> valuesA = (Series<String, Float>) phaseChart.getData().get(0);
				assertEquals(14, valuesA.getData().size());
				assertEquals(1, activeChart.getData().size());
				XYChart.Series<String, Float> valuesP = (Series<String, Float>) activeChart.getData().get(0);
				assertEquals(14, valuesP.getData().size());
				assertEquals(1, reactiveChart.getData().size());
				XYChart.Series<String, Float> valuesQ = (Series<String, Float>) reactiveChart.getData().get(0);
				assertEquals(14, valuesQ.getData().size());
			}
		});

	}
	
	private CompareLoadflowsDetailController controller;
}
