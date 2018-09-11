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

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

/**
 * A syntax highlighting code editor for JavaFX created by wrapping a CodeMirror code editor in a WebView.
 *
 * See http://codemirror.net for more information on using the codemirror editor.
 *
 * @author Marcos de Miguel <demiguelm at aia.es>
 */
@SuppressWarnings("restriction")
public class CodeEditor extends StackPane
{

	/**
	 * Create a new code editor.
	 * 
	 * @param editingCode
	 *            the initial code to be edited in the code editor.
	 */
	public CodeEditor() {
		super();

		this.editingCode = new StringBuilder();

		webView.getEngine().loadContent(applyEditingTemplate());
		this.getChildren().add(webView);
		
		webView.heightProperty().addListener( new ChangeListener<Object>() {
		    public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) 
		    { 
		        adjustHeight(newValue);
		    }
		});
		
		webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@SuppressWarnings("rawtypes")
			public void changed(ObservableValue ov, State oldState, State newState) {
				if (newState == State.SUCCEEDED) {
					adjustHeight(webView.getHeight());
				}
			}
		});
	}

	/**
	 * sets the current code in the editor and creates an editing snapshot of the code which can be reverted to.
	 */
	public void setCode(StringBuilder newCode)
	{
		this.editingCode = newCode;
		webView.getEngine().loadContent(applyEditingTemplate());
	}

	/**
	 * returns the current code in the editor and updates an editing snapshot of the code which can be reverted to.
	 */
	public StringBuilder getCodeAndSnapshot()
	{
		this.editingCode.setLength(0);
		this.editingCode.append((String) webView.getEngine().executeScript("editor.getValue();"));
		return editingCode;
	}

	/** revert edits of the code to the last edit snapshot taken. */
	public void revertEdits()
	{
		setCode(editingCode);
	}

	public void find()
	{
		webView.getEngine().executeScript("editor.execCommand(\"find\");");
	}

	public void setEditingFile(String location, String file)
	{
		this.editingLocation = location;
		this.editingFile = file;
	}

	public String getEditingLocation()
	{
		return editingLocation;
	}

	public String getEditingFile()
	{
		return editingFile;
	}

	public WebView getWebView()
	{
		return webView;
	}

	/**
	 * applies the editing template to the editing code to create the html+javascript source for a code editor.
	 */
	private String applyEditingTemplate()
	{
		return editingTemplate.replace("${code}", editingCode);
	}

	private void adjustHeight(Object newValue) {
	    Platform.runLater(new Runnable(){
	        @Override                                
	        public void run() {
            	int height = ((Double) newValue).intValue() - 20;
            	try {
            		webView.getEngine().executeScript("editor.setSize(\"100%\"," + height + ");");
            	}
            	catch (JSException e) {
            		//e.printStackTrace();
            	}
	        }               
	    });
	}

	/** a webview used to encapsulate the CodeMirror JavaScript. */
	private final WebView	webView			= new WebView();

	/**
	 * a snapshot of the code to be edited kept for easy initilization and reversion of editable code.
	 */
	private StringBuilder	editingCode;
	private String			editingLocation;
	private String			editingFile;

	/**
	 * a template for editing code - this can be changed to any template derived from the supported modes at http://codemirror.net to allow syntax highlighted editing of a wide variety of languages.
	 */
	private final String	editingTemplate	=
													"<!doctype html>"
															+
															"<html>"
															+
															"<head>"
															+
															"  <link rel=\"stylesheet\" href=\""
															+ getClass().getResource(
																	"/css/codemirror.css")
															+ "\">"
															+
															"  <link rel=\"stylesheet\" href=\""
															+ getClass().getResource(
																	"/css/dialog.css")
															+ "\">"
															+
															"  <link rel=\"stylesheet\" href=\""
															+ getClass().getResource(
																	"/css/matchesonscrollbar.css")
															+ "\">"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/codemirror.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass()
																	.getResource("/js/clike.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/dialog.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/searchcursor.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/search.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/annotatescrollbar.js")
															+ "\"></script>"
															+
															"  <script src=\""
															+ getClass().getResource(
																	"/js/matchesonscrollbar.js")
															+ "\"></script>"
															+
															"</head>"
															+
															"<body>"
															+
															"<form><textarea style=\"height:475px;\" id=\"code\" name=\"code\">\n"
															+
															"${code}"
															+
															"</textarea></form>"
															+
															"<script>"
															+
															"  var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), {"
															+
															"    lineNumbers: true," +
															"    matchBrackets: true," +
															"    mode: \"text/x-java\"" +
															"  });" +
															"</script>" +
															"</body>" +
															"</html>";

}