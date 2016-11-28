package org.power_systems_modelica.psm.gui.utils;

import javafx.collections.ObservableList;
 
public interface HierarchyData<T extends HierarchyData> {

    ObservableList<T> getChildren();
    
    Boolean isExpanded();
}