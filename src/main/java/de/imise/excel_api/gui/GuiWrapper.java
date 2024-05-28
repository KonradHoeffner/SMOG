package de.imise.excel_api.gui;

/**
 * Workaround for the jar execution error "Error: JavaFX runtime components are missing, and are
 * required to run this application". See
 * https://stackoverflow.com/questions/52653836/maven-shade-javafx-runtime-components-are-missing .
 */
public class GuiWrapper {

  public static void main(String[] args) {
    Gui.main(args);
  }
}
