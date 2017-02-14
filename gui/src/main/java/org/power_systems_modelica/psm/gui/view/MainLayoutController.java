package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.model.SummaryLabel;
import org.power_systems_modelica.psm.gui.service.fx.MainService;
import org.power_systems_modelica.psm.gui.utils.fx.GuiFileChooser;
import org.power_systems_modelica.psm.workflow.Workflow;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MainLayoutController
{

	public void setController(MainChildrenController controller)
	{
		this.controller = controller;

		controller.setMainService(mainService);
	}

	public void setLayout(AnchorPane node)
	{
		mainLayout.getChildren().remove(this.node);
		summaryContent.getChildren().clear();
		menuActions.getItems().clear();

		this.node = node;
		String mainActionText = controller.getMainAction();
		List<String> menuActionsList = controller.getMenuActions();
		List<SummaryLabel> summaryLabelsList = controller.getSummaryLabels();

		double globalLayoutY = 0.0;
		mainAction.disableProperty().bind(controller.disableBackground());
		mainAction.setVisible(true);
		if (mainActionText == null)
			mainAction.setVisible(false);
		else
		{
			mainAction.setText(mainActionText);
			globalLayoutY += 50.0;
		}

		menuActions.disableProperty().bind(controller.disableBackground());
		menuActions.setVisible(true);
		if (menuActionsList == null)
			menuActions.setVisible(false);
		else
		{
			for (String action : menuActionsList)
			{
				if (action.toLowerCase().equals("separator"))
				{
					SeparatorMenuItem menuItem = new SeparatorMenuItem();
					menuActions.getItems().add(menuItem);
				}
				else
				{
					MenuItem menuItem = new MenuItem(action);
					menuItem.setOnAction(new EventHandler<ActionEvent>()
					{

						@Override
						public void handle(ActionEvent event)
						{
							controller.handleMenuAction(action);
						}

					});
					menuActions.getItems().add(menuItem);
				}
			}
		}

		summaryPane.setVisible(true);
		double layoutY = 10.0;
		if (summaryLabelsList == null)
		{
			summaryPane.setVisible(false);
			layoutY = 0.0;
		}
		else
		{
			for (SummaryLabel summaryLabel : summaryLabelsList)
			{
				Label label = new Label(summaryLabel.getLabel());
				Label value = new Label(summaryLabel.getValue());
				summaryContent.getChildren().add(label);
				double xLabel = 24.0;
				double xValue = 200.0;
				if (summaryLabel.isSecondColumn())
				{
					xLabel += 450.0;
					xValue += 450.0;
				}
				label.setLayoutX(xLabel);
				label.setLayoutY(layoutY);
				summaryContent.getChildren().add(value);
				value.setLayoutX(xValue);
				value.setLayoutY(layoutY);
				if (!summaryLabel.isMultipleColumns() || summaryLabel.isSecondColumn())
					layoutY += 30.0;
			}

			layoutY += (5.0 + 26.0);

			summaryPane.setMinHeight(layoutY);
			summaryPane.setPrefHeight(layoutY);
			summaryPane.setMaxHeight(layoutY);
		}

		mainLayout.getChildren().add(node);
		node.setLayoutY(50);
		mainLayout.setRightAnchor(node, 0.0);
		mainLayout.setLeftAnchor(node, 0.0);
		mainLayout.setBottomAnchor(node, 0.0);
		if (mainActionText == null && menuActionsList == null)
			mainLayout.setTopAnchor(node, 0.0);
		else
			mainLayout.setTopAnchor(node, globalLayoutY + layoutY + 5.0);

	}

	public MainChildrenController getController()
	{
		return controller;
	}

	public void setMainService(MainService mainService)
	{
		this.mainService = mainService;
		setGlobalEventHandler();
	}

	public void setFileChooser(GuiFileChooser guiFileChooser)
	{
		controller.setFileChooser(guiFileChooser);
	}

	public void setDefaultInit()
	{
		controller.setDefaultInit();
	}

	public void setWorkflow(Workflow w, Object... objects)
	{
		controller.setWorkflow(w, objects);
	}

	@FXML
	protected void handleMainAction()
	{

		controller.handleMainAction();
	}

	@FXML
	private void initialize()
	{

		menuActions.graphicProperty().bind(
				Bindings.when(menuActions.hoverProperty())
						.then(new ImageView(whiteMenuImage))
						.otherwise(new ImageView(menuImage)));
	}

	private void setGlobalEventHandler()
	{
		mainService.getPrimaryStage().addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			if (ev.getCode() == KeyCode.ENTER)
			{
				Button button = controller.getDefaultEnterButton();
				if (button != null)
					button.fire();
				else
					mainAction.fire();

				ev.consume();
			}
		});
	}

	@FXML
	private Button					mainAction;

	@FXML
	private MenuButton				menuActions;

	@FXML
	private TitledPane				summaryPane;

	@FXML
	private AnchorPane				summaryContent;

	@FXML
	private AnchorPane				mainLayout;

	private AnchorPane				node;
	private MainChildrenController	controller;
	private MainService				mainService;

	private Image					whiteMenuImage	= new Image(
			getClass().getResourceAsStream("/img/menu-white-button.png"));
	private Image					menuImage		= new Image(
			getClass().getResourceAsStream("/img/menu-button.png"));
}
