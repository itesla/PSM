package org.power_systems_modelica.psm.test.gui.view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.power_systems_modelica.psm.gui.MainApp;
import org.power_systems_modelica.psm.gui.model.BusData;
import org.power_systems_modelica.psm.gui.model.DsData;
import org.power_systems_modelica.psm.gui.model.WorkflowResult;
import org.power_systems_modelica.psm.gui.service.MainService;
import org.power_systems_modelica.psm.gui.utils.CsvReader;
import org.power_systems_modelica.psm.gui.utils.PathUtils;
import org.power_systems_modelica.psm.gui.utils.Utils;
import org.power_systems_modelica.psm.gui.view.WorkflowDetailController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorkflowDetailControllerTest extends ApplicationTest {

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = null;
		try {

			MainService mainService = new MainService(null);
			mainService.setStage(stage);

			// Load cases overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WorkflowDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooserFake("test.properties"));

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
			float[] Vs = new float[1];
			float[] As = new float[1];
			float[] Ps = new float[1];
			float[] Qs = new float[1];

			Vs[0] = 1.0f;
			As[0] = 2.0f;
			Ps[0] = 10.0f;
			Qs[0] = 10.0f;
			bvalues.put("V", Vs);
			bvalues.put("A", As);
			bvalues.put("P", Ps);
			bvalues.put("Q", Qs);
			allBusesValues.add(new BusData("" + i, "bus__BUS___" + i + "_TN", bvalues));
		}

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
				
				ScatterChart voltageChart = lookup("#voltageChart").query();
				ScatterChart phaseChart = lookup("#phaseChart").query();
				ScatterChart activeChart = lookup("#activeChart").query();
				ScatterChart reactiveChart = lookup("#reactiveChart").query();
				
				LineChart dsChart = lookup("#dsChart").query();
				
				controller.addSeries(results);
	            Utils.addTooltipScatterChart(voltageChart, "pu");
	            Utils.addTooltipScatterChart(phaseChart, "º");
	            Utils.addTooltipScatterChart(activeChart, "MW");
	            Utils.addTooltipScatterChart(reactiveChart, "MVar");
	            Utils.addTooltipLineChartPosition(dsChart, "Time", "s", "Voltage", "pu");
				
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
				
				assertEquals(14, dsChart.getData().size());
				XYChart.Series<Double, Double> valuesDS = (Series<Double, Double>) dsChart.getData().get(0);
				assertEquals(512, valuesDS.getData().size());
			}
		});

	}
	
	private WorkflowDetailController controller;
}
