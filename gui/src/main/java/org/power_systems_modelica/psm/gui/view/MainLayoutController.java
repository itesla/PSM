package org.power_systems_modelica.psm.gui.view;

import java.util.List;

import org.power_systems_modelica.psm.gui.service.MainService;

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
import javafx.scene.layout.AnchorPane;

public class MainLayoutController {

	@FXML
	private void initialize() {
		
		menuActions.graphicProperty().bind(
				Bindings.when(menuActions.hoverProperty())
					.then(new ImageView(whiteMenuImage))
					.otherwise(new ImageView(menuImage))
				);
	}

	@FXML
	protected void handleMainAction() {
		
		controller.handleMainAction();
	}
	
	public void setLayout(AnchorPane node, MainChildrenController controller) {

		mainLayout.getChildren().remove(this.node);
		summaryContent.getChildren().clear();
		menuActions.getItems().clear();
		
		this.node = node;
		this.controller = controller;
		String mainActionText = controller.getMainAction();
		List<String> menuActionsList = controller.getMenuActions();
		List<String> summaryLabelsList = controller.getSummaryLabels();
		
		double globalLayoutY = 0.0;
		mainAction.setVisible(true);
		if (mainActionText == null)
			mainAction.setVisible(false);
		else {
			mainAction.setText(mainActionText);
			globalLayoutY += 50.0;
		}

		menuActions.setVisible(true);
		if (menuActionsList == null)
			menuActions.setVisible(false);
		else {
			for (String action: menuActionsList) {
				if (action.toLowerCase().equals("separator")) {
					SeparatorMenuItem menuItem = new SeparatorMenuItem();
					menuActions.getItems().add(menuItem);
				}
				else {
					MenuItem menuItem = new MenuItem(action);
					menuItem.setOnAction(new EventHandler<ActionEvent>() {
	
						@Override
						public void handle(ActionEvent event) {
							controller.handleMenuAction(action);
						}
						
					});
					menuActions.getItems().add(menuItem);
				}
			}
		}
		
		summaryPane.setVisible(true);
		double layoutX = 24.0;
		double layoutY = 10.0;
		if (summaryLabelsList == null) {
			summaryPane.setVisible(false);
			layoutY = 0.0;
		}
		else {
			for (String summaryLabel: summaryLabelsList) {
				Label label = new Label(summaryLabel);
				summaryContent.getChildren().add(label);
				label.setLayoutX(layoutX);
				label.setLayoutY(layoutY);
				if (layoutX == 24.0) {
					layoutX = 200.0;
				}
				else {
					layoutX = 24.0;
					layoutY += 30.0;
				}
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

	public void setMainService(MainService mainService) {
		
	}

	@FXML
	private Button mainAction;
	
	@FXML
	private MenuButton menuActions;
	
	@FXML
	private TitledPane summaryPane;
	
	@FXML
	private AnchorPane summaryContent;

	@FXML
	private AnchorPane mainLayout;

	private AnchorPane node;
	private MainChildrenController controller;
	private MainService mainService;

	private Image whiteMenuImage = new Image(getClass().getResourceAsStream("/img/menu-white-button.png"));
	private Image menuImage = new Image(getClass().getResourceAsStream("/img/menu-button.png"));
}