package service;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PawnTransMenuCtrl {

  @FXML Pane mainPawnPane;
  @FXML GridPane gridP;


  @FXML private void initialize(){

    AnchorPane.setBottomAnchor(mainPawnPane, .0);
    AnchorPane.setTopAnchor(mainPawnPane, .0);
    AnchorPane.setLeftAnchor(mainPawnPane, .0);
    AnchorPane.setRightAnchor(mainPawnPane, .0);

    gridP.prefWidthProperty().bind(mainPawnPane.widthProperty());
    gridP.prefHeightProperty().bind(mainPawnPane.heightProperty());
  }
}
