package de.imise.excel_api.gui;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/** Shows an exception to the user. */
public class ExceptionAlert extends Alert {

  public ExceptionAlert(String title, Exception ex) {
    super(AlertType.ERROR);
    this.setTitle(title);

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    String exceptionText = sw.toString();

    Label label = new Label("The exception stacktrace was:");

    TextArea textArea = new TextArea(exceptionText);
    textArea.setEditable(false);
    textArea.setWrapText(true);

    textArea.setMaxWidth(Double.MAX_VALUE);
    textArea.setMaxHeight(Double.MAX_VALUE);
    GridPane.setVgrow(textArea, Priority.ALWAYS);
    GridPane.setHgrow(textArea, Priority.ALWAYS);

    GridPane expContent = new GridPane();
    expContent.setMaxWidth(Double.MAX_VALUE);
    expContent.setMinSize(800, 600);
    expContent.add(label, 0, 0);
    expContent.add(textArea, 0, 1);

    this.getDialogPane().setContent(expContent);
  }
}
