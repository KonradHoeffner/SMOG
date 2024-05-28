package de.imise.excel_api.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabDragPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

  // Running this directly may fail. Use "mvn javafx:run" instead or run GuiWrapper.main.
  public static void main(String[] args) {
    launch(args);
  }

  private final SplitPane textPane = new SplitPane();
  private final SplitPane rdfText = new SplitPane();
  private final SplitPane textArea = new SplitPane();

  private class UnclosableTab extends Tab {
    UnclosableTab(String text, Node content) {
      super(text, content);
      setClosable(false);
    }
  }

  /** Setup the GUI. Called automatically with "mvn javafx:run". */
  @Override
  public void start(Stage stage) {
    // this.stage = stage;
    stage.setTitle("SMOG GUI");

    var pane = new VBox();
    {
      pane.setAlignment(Pos.TOP_CENTER);
      Scene scene = new Scene(pane, 1600, 1000);
      // scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
      stage.setScene(scene);
      stage.setMaximized(true);
      stage.show();
      // this.window = scene.getWindow();
    }
    pane.getChildren().add(MainMenuBar.create(/*this*/ ));

    rdfText.setMinSize(300, 500);
    textArea.prefHeight(
        Double.MAX_VALUE); // goal is to fill parent vertically, does not work fully though
    textPane.setPrefWidth(Double.MAX_VALUE);
    //    textRelationPane.setMinWidth(400);
    //    textRelationPane.setMaxWidth(500);
    textPane.getItems().addAll(textArea /*, textRelationPane*/);
    {
      TabPane tabPane = new TabPane();
      tabPane.setTabDragPolicy(TabDragPolicy.REORDER);

      tabPane
          .getTabs()
          .addAll(
              new UnclosableTab("Text", textPane),
              //          new UnclosableTab("Klassen", tableView),
              //          new UnclosableTab("Verbindungen", tripleTable),
              new UnclosableTab("RDF", rdfText));

      pane.getChildren().add(tabPane);
    }
    // openDocx(new File("src/main/resources/eu/snik/tag/benchmark.docx")); // use resource after
    // finished refactoring
  }
}
