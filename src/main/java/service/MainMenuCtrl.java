package service;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MainMenuCtrl {
  @FXML private AnchorPane hotSeatPane;
  @FXML private Pane root;
  @FXML private TabPane tabPane;
  @FXML private Button startHSBtn;

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

  public void onStartHotSeatPressed(EventHandler<? super MouseEvent> value) {
    startHSBtn.onMousePressedProperty().set(value);
  }
}
