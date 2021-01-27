package service;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PawnTransMenuCtrl {

  @FXML private Pane mainPiecePane;
  @FXML private GridPane gridP;


  @FXML private void initialize(){

    AnchorPane.setBottomAnchor(mainPiecePane, 5.);
    AnchorPane.setTopAnchor(mainPiecePane, 5.);
    AnchorPane.setLeftAnchor(mainPiecePane, 5.);
    AnchorPane.setRightAnchor(mainPiecePane, 5.);

    gridP.prefWidthProperty().bind(mainPiecePane.widthProperty().subtract(10));
    gridP.prefHeightProperty().bind(mainPiecePane.heightProperty().subtract(10));
  }

  public GridPane getGridPane() { return gridP; }
}
