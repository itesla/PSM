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
import org.power_systems_modelica.psm.gui.view.SimulationDetailController;
import org.power_systems_modelica.psm.test.gui.GuiFileChooserFake;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SimulationDetailControllerTest extends ApplicationTest
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
			loader.setLocation(MainApp.class.getResource("view/SimulationDetail.fxml"));
			AnchorPane workflowsOverview = (AnchorPane) loader.load();

			controller = loader.getController();
			controller.setMainService(mainService);
			controller.setFileChooser(new GuiFileChooserFake("test.properties"));

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

		interact(new Runnable()
		{
			@Override
			public void run()
			{
				LineChart<Number, Number> dsChart = lookup("#dsChart").query();

				controller.addSeries(results);
				Utils.addTooltipLineChartPosition(dsChart, "Time", "s", "Voltage", "pu");

				assertEquals(14, dsChart.getData().size());
				XYChart.Series<Number, Number> valuesDS = dsChart.getData().get(0);
				assertEquals(512, valuesDS.getData().size());
			}
		});
	}

	private SimulationDetailController controller;
}
