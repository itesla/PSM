package org.power_systems_modelica.psm.gui.utils;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

/**
 * A syntax highlighting code editor for JavaFX created by wrapping a CodeMirror
 * code editor in a WebView.
 *
 * See http://codemirror.net for more information on using the codemirror
 * editor.
 */
public class CodeEditor extends StackPane {

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
	}

	/**
	 * sets the current code in the editor and creates an editing snapshot of
	 * the code which can be reverted to.
	 */
	public void setCode(StringBuilder newCode) {
		this.editingCode.setLength(0);
		this.editingCode.append(newCode.toString());
		webView.getEngine().loadContent(applyEditingTemplate());
	}

	/**
	 * returns the current code in the editor and updates an editing snapshot of
	 * the code which can be reverted to.
	 */
	public StringBuilder getCodeAndSnapshot() {
		this.editingCode.setLength(0);
		this.editingCode.append((String) webView.getEngine().executeScript("editor.getValue();"));
		return editingCode;
	}
	
	/** revert edits of the code to the last edit snapshot taken. */
	public void revertEdits() {
		setCode(editingCode);
	}

	public void setEditingFile(String location, String file) {
		this.editingLocation = location;
		this.editingFile = file;
	}
	
	public String getEditingLocation() {
		return editingLocation;
	}

	public String getEditingFile() {
		return editingFile;
	}
	
	public WebView getWebView() {
		return webView;
	}

	/**
	 * applies the editing template to the editing code to create the
	 * html+javascript source for a code editor.
	 */
	private String applyEditingTemplate() {
		return editingTemplate.replace("${code}", editingCode);
	}

	/** a webview used to encapsulate the CodeMirror JavaScript. */
	private final WebView webView = new WebView();

	/**
	 * a snapshot of the code to be edited kept for easy initilization and
	 * reversion of editable code.
	 */
	private StringBuilder editingCode;
	private String editingLocation;
	private String editingFile;

	/**
	 * a template for editing code - this can be changed to any template derived
	 * from the supported modes at http://codemirror.net to allow syntax
	 * highlighted editing of a wide variety of languages.
	 */
	 private final String editingTemplate =
			    "<!doctype html>" +
			    "<html>" +
			    "<head>" +
			    "  <link rel=\"stylesheet\" href=\"http://codemirror.net/lib/codemirror.css\">" +
			    "  <script src=\"http://codemirror.net/lib/codemirror.js\"></script>" +
			    "  <script src=\"http://codemirror.net/mode/clike/clike.js\"></script>" +
			    "</head>" +
			    "<body>" +
			    "<form><textarea id=\"code\" name=\"code\">\n" +
			    "${code}" +
			    "</textarea></form>" +
			    "<script>" +
			    "  var editor = CodeMirror.fromTextArea(document.getElementById(\"code\"), {" +
			    "    lineNumbers: true," +
			    "    matchBrackets: true," + 
			    "    mode: \"text/x-java\"" +
			    "  });" + 
			    "  editor.setSize(\"100%\", \"100%\");" +
			    "</script>" +
			    "</body>" +
			    "</html>";


}