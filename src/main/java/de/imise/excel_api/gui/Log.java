package de.imise.excel_api.gui;

import javafx.application.Platform;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

final class Log {

  public static void warn(String message, Window window) {
    Platform.runLater(
        () -> {
          Notifications.create()
              .owner(window)
              .title("Warnung")
              .text(message)
              .hideAfter(Duration.seconds(10 + Math.log10(message.length())))
              .showWarning();
        });

    System.err.println("Warnung: " + message);
  }

  public static void warn(String message) {
    System.err.println(message);
  }

  public static void info(String message) {
    System.out.println(message);
  }

  public static void error(String title, Exception ex) {
    Platform.runLater(
        () -> {
          new ExceptionAlert(title, ex).show();
        });
    // ex.printStackTrace();
  }
}
