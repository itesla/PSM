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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class AutoFillTextBoxSkin<T extends Comparable<? super T>>
		extends SkinBase<AutoFillTextBox<T>>
		implements ChangeListener<String>, EventHandler<Event>
{
	private final static int	TITLE_HEIGHT	= 28;

	private ListView<T>			listview;
	private TextField			textbox;
	private AutoFillTextBox<T>	autofillTextbox;
	private ObservableList<T>	data;
	private Popup				popup;

	public Window getWindow()
	{
		return autofillTextbox.getScene().getWindow();
	}

	private String temporaryTxt = "";

	public AutoFillTextBoxSkin(AutoFillTextBox<T> text)
	{
		super(text);

		autofillTextbox = text;
		listview = text.getListview();
		if (text.getFilterMode())
		{
			listview.setItems(text.getData());
		}
		listview.itemsProperty().addListener(new ChangeListener<ObservableList<T>>()
		{
			@Override
			public void changed(ObservableValue<? extends ObservableList<T>> observable,
					ObservableList<T> oldValue,
					ObservableList<T> newValue)
			{
				if (listview.getItems().size() > 0 && listview.getItems() != null)
				{
					showPopup();
				}
				else
				{
					hidePopup();
				}
			}
		});
		listview.setOnMouseReleased(this);
		listview.setOnKeyReleased(this);
		listview.setCellFactory(new Callback<ListView<T>, ListCell<T>>()
		{
			@Override
			public ListCell<T> call(ListView<T> p)
			{
				final ListCell<T> cell = new ListCell<T>()
				{
					@Override
					public void updateItem(T item, boolean empty)
					{
						super.updateItem(item, empty);
						if (item != null)
						{
							setText(item.toString());
						}
					}
				};
				cell.focusedProperty().addListener(new InvalidationListener()
				{
					public void invalidated(Observable ove)
					{
						if (cell.getItem() != null && cell.isFocused())
						{
							String prev = null;
							if (temporaryTxt.length() <= 0)
							{
								if (listview.getItems().size() != data.size())
								{
									temporaryTxt = textbox.getText();
								}
							}
							prev = temporaryTxt;
							textbox.selectRange(prev.length(), cell.getItem().toString().length());
							if (listview.getItems().size() == 1)
							{
								textbox.textProperty().removeListener(AutoFillTextBoxSkin.this);
								textbox.textProperty()
										.setValue(listview.getItems().get(0).toString());
								textbox.textProperty().addListener(AutoFillTextBoxSkin.this);
							}
						}
					}
				});
				return cell;
			}
		});
		textbox = text.getTextbox();
		textbox.setOnKeyPressed(this);
		textbox.textProperty().addListener(this);
		textbox.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue,
					Boolean newValue)
			{
				textbox.end();
				if (Boolean.FALSE.equals(oldValue) && Boolean.TRUE.equals(newValue))
					showPopup();
			}
		});
		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(listview);
		text.getData().addListener(new ListChangeListener<T>()
		{
			@Override
			public void onChanged(Change<? extends T> c)
			{
				if (text.getFilterMode())
				{
					listview.setItems(text.getData());
				}
			}
		});
		data = text.getData();
		FXCollections.sort(data);
		getChildren().addAll(textbox);
	}

	public void selectList()
	{
		Object i = listview.getSelectionModel().getSelectedItem();
		if (i != null)
		{
			textbox.setText(listview.getSelectionModel().getSelectedItem().toString());
			listview.getItems().clear();
			textbox.requestFocus();
			textbox.requestLayout();
			textbox.end();
			temporaryTxt = "";
			hidePopup();
		}
	}

	@Override
	public void handle(Event evt)
	{
		if (evt.getEventType() == KeyEvent.KEY_PRESSED)
		{
			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == textbox)
			{
				if (t.getCode() == KeyCode.DOWN)
				{
					if (popup.isShowing())
					{
						listview.requestFocus();
						listview.getSelectionModel().select(0);
					}
				}
			}
		}
		else if (evt.getEventType() == KeyEvent.KEY_RELEASED)
		{
			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == listview)
			{
				if (t.getCode() == KeyCode.ENTER)
				{
					selectList();
				}
				else if (t.getCode() == KeyCode.UP)
				{
					if (listview.getSelectionModel().getSelectedIndex() == 0)
					{
						textbox.requestFocus();
					}
				}
			}
		}
		else if (evt.getEventType() == MouseEvent.MOUSE_RELEASED)
		{
			if (evt.getSource() == listview)
			{
				selectList();
			}
		}
	}

	public void showPopup()
	{
		listview.setPrefWidth(textbox.getWidth());
		if (listview.getItems().size() > 6)
		{
			listview.setPrefHeight((6 * 24));
		}
		else
		{
			listview.setPrefHeight((listview.getItems().size() * 24));
		}
		popup.show(getWindow(),
				getWindow().getX() + textbox.localToScene(0, 0).getX() + textbox.getScene().getX(),
				getWindow().getY() + textbox.localToScene(0, 0).getY() + textbox.getScene().getY()
						+ TITLE_HEIGHT);
		listview.getSelectionModel().clearSelection();
		listview.getFocusModel().focus(-1);
	}

	public void hidePopup()
	{
		popup.hide();
	}

	@Override
	public void changed(ObservableValue<? extends String> ov, String t, String t1)
	{
		if (ov.getValue().toString().length() > 0)
		{
			String txtdata = (textbox.getText()).trim();
			if (txtdata.length() > 0)
			{
				ObservableList<T> list = FXCollections.observableArrayList();
				String compare = txtdata.toLowerCase();
				for (T dat : data)
				{
					String str = dat.toString().toLowerCase();
					if (str.contains(compare))
					{
						list.add(dat);
					}
				}
				if (listview.getItems().containsAll(list)
						&& listview.getItems().size() == list.size()
						&& listview.getItems() != null)
				{
					showPopup();
				}
				else
				{
					listview.setItems(list);
				}
			}
			else
			{
				if (autofillTextbox.getFilterMode())
				{
					listview.setItems(data);
				}
				else
				{
					listview.setItems(null);
				}
			}
		}
		if (ov.getValue().toString().length() <= 0)
		{
			temporaryTxt = "";
			if (autofillTextbox.getFilterMode() && !data.isEmpty())
			{
				listview.setItems(data);
				showPopup();
			}
			else
			{
				hidePopup();
			}
		}
	}
}