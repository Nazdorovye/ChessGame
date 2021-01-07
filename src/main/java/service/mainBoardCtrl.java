package service;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class mainBoardCtrl {
  @FXML private ImageView rookWR;
  @FXML private ImageView rookWL;
  @FXML private ImageView rookBR;
  @FXML private ImageView rookBL;

  @FXML private ImageView bishopWR;
  @FXML private ImageView bishopWL;
  @FXML private ImageView bishopBR;
  @FXML private ImageView bishopBL;

  @FXML private ImageView knightWR;
  @FXML private ImageView knightWL;
  @FXML private ImageView knightBR;
  @FXML private ImageView knightBL;
  
  @FXML private ImageView kingW;
  @FXML private ImageView kingB;
  
  @FXML private ImageView queenW;
  @FXML private ImageView queenB;

  @FXML private ImageView pawnWA;
  @FXML private ImageView pawnWB;
  @FXML private ImageView pawnWC;
  @FXML private ImageView pawnWD;
  @FXML private ImageView pawnWE;
  @FXML private ImageView pawnWF;
  @FXML private ImageView pawnWG;
  @FXML private ImageView pawnWH;

  @FXML private ImageView pawnBA;
  @FXML private ImageView pawnBB;
  @FXML private ImageView pawnBC;
  @FXML private ImageView pawnBD;
  @FXML private ImageView pawnBE;
  @FXML private ImageView pawnBF;
  @FXML private ImageView pawnBG;
  @FXML private ImageView pawnBH;

  @FXML private Pane root;
  @FXML private Pane aspect;
  @FXML private HBox charTop;
  @FXML private HBox boardMid;
  @FXML private HBox charBottom;
  @FXML private VBox nrLeft;
  @FXML private VBox nrRight;
  @FXML private VBox fullBoard;
  @FXML private GridPane board;

  @FXML private Label topA;
  @FXML private Label topB;
  @FXML private Label topC;
  @FXML private Label topD;
  @FXML private Label topE;
  @FXML private Label topF;
  @FXML private Label topG;
  @FXML private Label topH;

  @FXML private Label bottomA;
  @FXML private Label bottomB;
  @FXML private Label bottomC;
  @FXML private Label bottomD;
  @FXML private Label bottomE;
  @FXML private Label bottomF;
  @FXML private Label bottomG;
  @FXML private Label bottomH;

  @FXML private Label left1;
  @FXML private Label left2;
  @FXML private Label left3;
  @FXML private Label left4;
  @FXML private Label left5;
  @FXML private Label left6;
  @FXML private Label left7;
  @FXML private Label left8;

  @FXML private Label right1;
  @FXML private Label right2;
  @FXML private Label right3;
  @FXML private Label right4;
  @FXML private Label right5;
  @FXML private Label right6;
  @FXML private Label right7;
  @FXML private Label right8;

  @FXML private void initialize() {
    fullBoard.prefHeightProperty().bind(aspect.prefHeightProperty().multiply(0.94444444444));
    fullBoard.prefWidthProperty().bind(fullBoard.prefHeightProperty());

    charTop.prefHeightProperty().bind(aspect.prefHeightProperty().multiply(0.04411764706));
    charBottom.prefHeightProperty().bind(charTop.prefHeightProperty());

    boardMid.prefHeightProperty().bind(aspect.prefHeightProperty().multiply(0.91176470588));

    nrLeft.prefWidthProperty().bind(charTop.prefHeightProperty());
    nrRight.prefWidthProperty().bind(charTop.prefHeightProperty());

    board.prefHeightProperty().bind(boardMid.prefHeightProperty());
    board.prefWidthProperty().bind(board.prefHeightProperty());
  }
}