package org.power_systems_modelica.psm.gui.utils;

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

public class AutoFillTextBoxSkin<T> extends SkinBase<AutoFillTextBox<T>>
		implements ChangeListener<String>, EventHandler {

	private final static int TITLE_HEIGHT = 28;

	private final static int WINDOW_BORDER = 8;

	private ListView listview;

	private TextField textbox;

	private AutoFillTextBox autofillTextbox;

	private ObservableList data;

	private Popup popup;

	public Window getWindow() {
		return autofillTextbox.getScene().getWindow();
	}

	private String temporaryTxt = "";

	/**
	 * **************************** CONSTRUCTOR
	 * <p>
	 * 
	 * @param text
	 *            AutoTextBox ****************************
	 */
	public AutoFillTextBoxSkin(AutoFillTextBox text) {
		super(text);

		autofillTextbox = text;

		listview = text.getListview();
		if (text.getFilterMode()) {
			listview.setItems(text.getData());
		}
		listview.itemsProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				if (listview.getItems().size() > 0 && listview.getItems() != null) {
					showPopup();
				} else {
					hidePopup();
				}
			}

		});

		listview.setOnMouseReleased(this);
		listview.setOnKeyReleased(this);

		listview.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> p) {

				final ListCell cell = new ListCell() {
					@Override
					public void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.toString());
						}
					}

				};

                cell.focusedProperty().addListener(new InvalidationListener() {

                    public void invalidated(Observable ove) {
                        ObservableValue<Boolean> ov = (ObservableValue<Boolean>) ove;
                        if (cell.getItem() != null && cell.isFocused()) {

                            String prev = null;

                            if (temporaryTxt.length() <= 0) {
                                if (listview.getItems().size() != data.size()) {
                                    temporaryTxt = textbox.getText();
                                }
                            }

                            prev = temporaryTxt;
                            textbox.selectRange(prev.length(), cell.getItem().toString().length());

                            if (listview.getItems().size() == 1) {
                            	textbox.textProperty().removeListener(AutoFillTextBoxSkin.this);
                            	textbox.textProperty().setValue(listview.getItems().get(0).toString());
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

		textbox.focusedProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue ov, Object t, Object t1) {
				textbox.end();
				if (Boolean.FALSE.equals(t) && Boolean.TRUE.equals(t1))
					showPopup();
			}

		});

		popup = new Popup();
		popup.setAutoHide(true);
		popup.getContent().add(listview);

		text.getData().addListener(new ListChangeListener() {

			@Override
			public void onChanged(Change c) {
				if (text.getFilterMode()) {
					listview.setItems(text.getData());
				}
			}
			
		});
		
		data = text.getData();
		FXCollections.sort(data);

		getChildren().addAll(textbox);
		
	}

	public void selectList() {
		Object i = listview.getSelectionModel().getSelectedItem();
		if (i != null) {
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
	public void handle(Event evt) {

		if (evt.getEventType() == KeyEvent.KEY_PRESSED) {

			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == textbox) {
				if (t.getCode() == KeyCode.DOWN) {
					if (popup.isShowing()) {
						listview.requestFocus();
						listview.getSelectionModel().select(0);
					}
				}

			}
		} else if (evt.getEventType() == KeyEvent.KEY_RELEASED) {

			KeyEvent t = (KeyEvent) evt;
			if (t.getSource() == listview) {
				if (t.getCode() == KeyCode.ENTER) {
					selectList();
				} else if (t.getCode() == KeyCode.UP) {
					if (listview.getSelectionModel().getSelectedIndex() == 0) {
						textbox.requestFocus();
					}
				}
			}
		} else if (evt.getEventType() == MouseEvent.MOUSE_RELEASED) {

			if (evt.getSource() == listview) {
				selectList();
			}
		}
	}

	public void showPopup() {
		listview.setPrefWidth(textbox.getWidth());

		if (listview.getItems().size() > 6) {
			listview.setPrefHeight((6 * 24));
		} else {
			listview.setPrefHeight((listview.getItems().size() * 24));
		}

		popup.show(getWindow(), getWindow().getX() + textbox.localToScene(0, 0).getX() + textbox.getScene().getX(),
				getWindow().getY() + textbox.localToScene(0, 0).getY() + textbox.getScene().getY() + TITLE_HEIGHT);

		listview.getSelectionModel().clearSelection();
		listview.getFocusModel().focus(-1);
	}

	public void hidePopup() {

		popup.hide();

	}

	@Override
	public void changed(ObservableValue<? extends String> ov, String t, String t1) {

		if (ov.getValue().toString().length() > 0) {
			String txtdata = (textbox.getText()).trim();

			int limit = 0;
			if (txtdata.length() > 0) {
				ObservableList list = FXCollections.observableArrayList();
				String compare = txtdata.toLowerCase();
				for (Object dat : data) {
					String str = dat.toString().toLowerCase();

					if (str.contains(compare)) {
						list.add(dat);
						limit++;
					}
				}
				if (listview.getItems().containsAll(list) && listview.getItems().size() == list.size()
						&& listview.getItems() != null) {
					showPopup();
				} else {
					listview.setItems(list);
				}
			} else {
				if (autofillTextbox.getFilterMode()) {
					listview.setItems(data);
				} else {
					listview.setItems(null);
				}
			}
		}

		if (ov.getValue().toString().length() <= 0) {
			temporaryTxt = "";
			if (autofillTextbox.getFilterMode()) {

				listview.setItems(data);
				showPopup();

			} else {
				hidePopup();
			}
		}
	}
}