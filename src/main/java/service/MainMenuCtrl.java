package service;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainMenuCtrl {
  @FXML AnchorPane hotSeatPane;
  @FXML Pane root;
  @FXML TabPane tabPane;

  @FXML private void initialize() {
    AnchorPane.setBottomAnchor(root, .0);
    AnchorPane.setTopAnchor(root, .0);
    AnchorPane.setLeftAnchor(root, .0);
    AnchorPane.setRightAnchor(root, .0);

    tabPane.prefWidthProperty().bind(root.widthProperty());
    tabPane.prefHeightProperty().bind(root.heightProperty());
    tabPane.tabMinWidthProperty().bind(root.widthProperty().subtract(19).divide(3));
    tabPane.tabMaxWidthProperty().bind(tabPane.tabMinWidthProperty());
  }
}
