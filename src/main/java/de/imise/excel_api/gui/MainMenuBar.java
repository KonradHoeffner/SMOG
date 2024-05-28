package de.imise.excel_api.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Region;

/** File and Help menu. */
public class MainMenuBar {

  private MainMenuBar() {}

  static Menu fileMenu() {
    Menu fileMenu = new Menu("_Datei");

    {
      MenuItem openSnikGraphItem = new MenuItem("under construction");
      fileMenu.getItems().add(openSnikGraphItem);
      openSnikGraphItem.setOnAction(
          event -> {
            browse("https://github.com/Onto-Med/SMOG");
          });
    }
    {
      MenuItem undoItem = new MenuItem("_Undo under construction");
      fileMenu.getItems().add(undoItem);
      undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
      undoItem.setOnAction(
          e -> {
            // TODO
          });
    }

    return fileMenu;
  }

  //	static Menu optionsMenu()
  //	{
  //		Menu optionsMenu = new Menu("_Optionen");
  //		MenuItem developerItem = new CheckMenuItem("Developer Mode");
  //
  //		optionsMenu.getItems().addAll(developerItem);
  //		return optionsMenu;
  //	}

  static MenuItem about() {
    MenuItem about = new MenuItem("Ü_ber");

    try {
      Properties git = new Properties();
      git.load(MainMenuBar.class.getClassLoader().getResourceAsStream("git.properties"));

      Alert alert = new Alert(AlertType.INFORMATION);
      alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
      alert.getDialogPane().setMinWidth(1000);
      about.setOnAction(e -> alert.show());
      alert.setTitle("Über");
      alert.setHeaderText("SMOG " + git.getProperty("git.build.version"));
      alert.setContentText(
          "JavaFX "
              + System.getProperty("javafx.version")
              + ", running on Java "
              + System.getProperty("java.version")
              + ".\n"
              + "Built "
              + git.getProperty("git.build.time")
              + "\n"
              + "Commited "
              + git.getProperty("git.commit.time")
              + " ID "
              + git.getProperty("git.commit.id")
              + "\n"
              + "Commit Message "
              + git.getProperty("git.commit.message.short")
              + "\n"
              + "Committer "
              + git.getProperty("git.build.user.name")
              + " "
              + git.getProperty("git.build.user.email")
              + "\n"
              + "Nearest Tag "
              + git.getProperty("git.closest.tag.name"));
    } catch (IOException | NullPointerException e) {
      System.err.println("Couldn't load GIT information.");
    }

    return about;
  }

  static void browse(String uri) {
    if (!(Desktop.isDesktopSupported()
        && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))) {
      new Alert(AlertType.WARNING, "Kann Website nicht öffnen, nicht unterstützt.").show();
      System.err.println("Cannot open Website, not supported.");
      return;
    }
    // Using the JavaFX thread for desktop hangs the program.
    new Thread(
            () -> {
              try {
                Desktop.getDesktop().browse(new URI(uri));
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            })
        .start();
  }

  static Menu helpMenu() {
    Menu helpMenu = new Menu("_Hilfe");

    MenuItem helpItem = new MenuItem("_Hilfe");
    helpItem.setOnAction(
        event -> {
          browse("https://onto-med.github.io/SMOG/");
        });

    MenuItem reportItem = new MenuItem("Bug oder Vorschlag melden");
    reportItem.setOnAction(
        event -> {
          browse("https://github.com/Onto-Med/SMOG/issues");
        });

    helpMenu.getItems().addAll(helpItem, reportItem, about());

    return helpMenu;
  }

  static MenuBar create() {
    return new MenuBar(fileMenu(), helpMenu());
  }
}
