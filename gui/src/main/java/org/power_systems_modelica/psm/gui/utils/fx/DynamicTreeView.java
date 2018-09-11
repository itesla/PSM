package org.power_systems_modelica.psm.gui.utils.fx;

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

import java.util.HashMap;
import java.util.Map;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.WeakListChangeListener;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class DynamicTreeView<T extends HierarchyData<T>> extends TreeView<T> {

	private final Map<TreeItem<T>, ListChangeListener<T>> hardReferences = new HashMap<TreeItem<T>, ListChangeListener<T>>();

	private final Map<TreeItem<T>, WeakListChangeListener<T>> weakListeners = new HashMap<TreeItem<T>, WeakListChangeListener<T>>();

	private ObjectProperty<ObservableList<? extends T>> items = new SimpleObjectProperty<ObservableList<? extends T>>(
			this, "items");

	public DynamicTreeView() {
		super();
		init();
	}

	public DynamicTreeView(TreeItem<T> root) {
		super(root);
		init();
	}

	private void init() {
		rootProperty().addListener(new ChangeListener<TreeItem<T>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<T>> observableValue, TreeItem<T> oldRoot,
					TreeItem<T> newRoot) {
				clear(oldRoot);
				updateItems();
			}
		});

		setItems(FXCollections.<T>observableArrayList());

		// Do not use ChangeListener, because it won't trigger if old list
		// equals new list (but in fact different references).
		items.addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				clear(getRoot());
				updateItems();
			}
		});
	}

	private void clear(TreeItem<T> root) {
		if (root != null) {
			for (TreeItem<T> treeItem : root.getChildren()) {
				removeRecursively(treeItem);
			}

			removeRecursively(root);
			root.getChildren().clear();
		}
	}

	private void updateItems() {

		if (getItems() != null) {
			for (T value : getItems()) {
				getRoot().getChildren().add(addRecursively(value));
			}

			ListChangeListener<T> rootListener = getListChangeListener(getRoot().getChildren());
			WeakListChangeListener<T> weakListChangeListener = new WeakListChangeListener<T>(rootListener);
			hardReferences.put(getRoot(), rootListener);
			weakListeners.put(getRoot(), weakListChangeListener);
			getItems().addListener(weakListChangeListener);
		}
	}

	private ListChangeListener<T> getListChangeListener(final ObservableList<TreeItem<T>> treeItemChildren) {
		return new ListChangeListener<T>() {
			@Override
			public void onChanged(final Change<? extends T> change) {
				while (change.next()) {
					if (change.wasUpdated()) {
						// http://javafx-jira.kenai.com/browse/RT-23434
						continue;
					}
					if (change.wasRemoved()) {
						for (int i = change.getRemovedSize() - 1; i >= 0; i--) {
							removeRecursively(treeItemChildren.remove(change.getFrom() + i));
						}
					}
					// If items have been added
					if (change.wasAdded()) {
						// Get the new items
						for (int i = change.getFrom(); i < change.getTo(); i++) {
							treeItemChildren.add(i, addRecursively(change.getList().get(i)));
						}
					}
					// If the list was sorted.
					if (change.wasPermutated()) {
						// Store the new order.
						Map<Integer, TreeItem<T>> tempMap = new HashMap<Integer, TreeItem<T>>();

						for (int i = change.getTo() - 1; i >= change.getFrom(); i--) {
							int a = change.getPermutation(i);
							tempMap.put(a, treeItemChildren.remove(i));
						}

						getSelectionModel().clearSelection();

						// Add the items in the new order.
						for (int i = change.getFrom(); i < change.getTo(); i++) {
							treeItemChildren.add(tempMap.remove(i));
						}
					}
				}
			}
		};
	}

	private TreeItem<T> removeRecursively(TreeItem<T> item) {
		if (item.getValue() != null && item.getValue().getChildren() != null) {

			if (weakListeners.containsKey(item)) {
				item.getValue().getChildren().removeListener(weakListeners.remove(item));
				hardReferences.remove(item);
			}
			for (TreeItem<T> treeItem : item.getChildren()) {
				removeRecursively(treeItem);
			}
		}
		return item;
	}

	private TreeItem<T> addRecursively(T value) {

		TreeItem<T> treeItem = new TreeItem<T>();
		treeItem.setValue(value);
		treeItem.setExpanded(value.isExpanded());
		treeItem.setGraphic(value.getGraphic());

		if (value != null && value.getChildren() != null) {
			ListChangeListener<T> listChangeListener = getListChangeListener(treeItem.getChildren());
			WeakListChangeListener<T> weakListener = new WeakListChangeListener<T>(listChangeListener);
			value.getChildren().addListener(weakListener);

			hardReferences.put(treeItem, listChangeListener);
			weakListeners.put(treeItem, weakListener);
			for (T child : value.getChildren()) {
				treeItem.getChildren().add(addRecursively(child));
			}
		}
		return treeItem;
	}

	public ObservableList<? extends T> getItems() {
		return items.get();
	}

	public void setItems(ObservableList<? extends T> items) {
		this.items.set(items);
	}
}