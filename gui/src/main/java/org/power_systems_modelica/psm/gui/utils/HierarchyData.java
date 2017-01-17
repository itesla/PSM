package org.power_systems_modelica.psm.gui.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface HierarchyData<T extends HierarchyData<T>>
{
	ObservableList<T> getChildren();

	Boolean isExpanded();
	
	Node getGraphic();
}