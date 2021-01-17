package service;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainBoardCtrl {
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
    fullBoard.prefHeightProperty().bind(aspect.heightProperty().subtract(40));
    fullBoard.prefWidthProperty().bind(fullBoard.heightProperty());

    charTop.prefHeightProperty().bind(aspect.prefHeightProperty().multiply(0.04411764706));
    charBottom.prefHeightProperty().bind(charTop.heightProperty());

    boardMid.prefHeightProperty().bind(aspect.heightProperty().multiply(0.91176470588));

    nrLeft.prefWidthProperty().bind(charTop.heightProperty());
    nrRight.prefWidthProperty().bind(charTop.heightProperty());

    board.prefHeightProperty().bind(boardMid.heightProperty());
    board.prefWidthProperty().bind(board.heightProperty());

    topA.prefWidthProperty().bind(
        charTop.widthProperty().subtract(nrLeft.widthProperty().multiply(2)).divide(8));

    topB.prefWidthProperty().bind(topA.widthProperty());
    topC.prefWidthProperty().bind(topA.widthProperty());
    topD.prefWidthProperty().bind(topA.widthProperty());
    topE.prefWidthProperty().bind(topA.widthProperty());
    topF.prefWidthProperty().bind(topA.widthProperty());
    topG.prefWidthProperty().bind(topA.widthProperty());
    topH.prefWidthProperty().bind(topA.widthProperty());

    bottomA.prefWidthProperty().bind(topA.widthProperty());
    bottomB.prefWidthProperty().bind(topA.widthProperty());
    bottomC.prefWidthProperty().bind(topA.widthProperty());
    bottomD.prefWidthProperty().bind(topA.widthProperty());
    bottomE.prefWidthProperty().bind(topA.widthProperty());
    bottomF.prefWidthProperty().bind(topA.widthProperty());
    bottomG.prefWidthProperty().bind(topA.widthProperty());
    bottomH.prefWidthProperty().bind(topA.widthProperty());

    left1.prefHeightProperty().bind(topA.widthProperty());
    left2.prefHeightProperty().bind(topA.widthProperty());
    left3.prefHeightProperty().bind(topA.widthProperty());
    left4.prefHeightProperty().bind(topA.widthProperty());
    left5.prefHeightProperty().bind(topA.widthProperty());
    left6.prefHeightProperty().bind(topA.widthProperty());
    left7.prefHeightProperty().bind(topA.widthProperty());
    left8.prefHeightProperty().bind(topA.widthProperty());

    right1.prefHeightProperty().bind(topA.widthProperty());
    right2.prefHeightProperty().bind(topA.widthProperty());
    right3.prefHeightProperty().bind(topA.widthProperty());
    right4.prefHeightProperty().bind(topA.widthProperty());
    right5.prefHeightProperty().bind(topA.widthProperty());
    right6.prefHeightProperty().bind(topA.widthProperty());
    right7.prefHeightProperty().bind(topA.widthProperty());
    right8.prefHeightProperty().bind(topA.widthProperty());

    rookWR.fitWidthProperty().bind(topA.widthProperty());
    rookWR.fitHeightProperty().bind(topA.widthProperty());
    rookWL.fitWidthProperty().bind(topA.widthProperty());
    rookWL.fitHeightProperty().bind(topA.widthProperty());
    rookBR.fitWidthProperty().bind(topA.widthProperty());
    rookBR.fitHeightProperty().bind(topA.widthProperty());
    rookBL.fitWidthProperty().bind(topA.widthProperty());
    rookBL.fitHeightProperty().bind(topA.widthProperty());

    bishopWR.fitWidthProperty().bind(topA.widthProperty());
    bishopWR.fitHeightProperty().bind(topA.widthProperty());
    bishopWL.fitWidthProperty().bind(topA.widthProperty());
    bishopWL.fitHeightProperty().bind(topA.widthProperty());
    bishopBR.fitWidthProperty().bind(topA.widthProperty());
    bishopBR.fitHeightProperty().bind(topA.widthProperty());    
    bishopBL.fitWidthProperty().bind(topA.widthProperty());
    bishopBL.fitHeightProperty().bind(topA.widthProperty());

    knightWR.fitWidthProperty().bind(topA.widthProperty());
    knightWR.fitHeightProperty().bind(topA.widthProperty());
    knightWL.fitWidthProperty().bind(topA.widthProperty());
    knightWL.fitHeightProperty().bind(topA.widthProperty());
    knightBR.fitWidthProperty().bind(topA.widthProperty());
    knightBR.fitHeightProperty().bind(topA.widthProperty());    
    knightBL.fitWidthProperty().bind(topA.widthProperty());
    knightBL.fitHeightProperty().bind(topA.widthProperty());

    kingW.fitWidthProperty().bind(topA.widthProperty());
    kingW.fitHeightProperty().bind(topA.widthProperty());
    kingB.fitWidthProperty().bind(topA.widthProperty());
    kingB.fitHeightProperty().bind(topA.widthProperty());

    queenW.fitWidthProperty().bind(topA.widthProperty());
    queenW.fitHeightProperty().bind(topA.widthProperty());
    queenB.fitWidthProperty().bind(topA.widthProperty());
    queenB.fitHeightProperty().bind(topA.widthProperty());

    pawnWA.fitWidthProperty().bind(topA.widthProperty());
    pawnWA.fitHeightProperty().bind(topA.widthProperty());
    pawnWB.fitWidthProperty().bind(topA.widthProperty());
    pawnWB.fitHeightProperty().bind(topA.widthProperty());
    pawnWC.fitWidthProperty().bind(topA.widthProperty());
    pawnWC.fitHeightProperty().bind(topA.widthProperty());
    pawnWD.fitWidthProperty().bind(topA.widthProperty());
    pawnWD.fitHeightProperty().bind(topA.widthProperty());
    pawnWE.fitWidthProperty().bind(topA.widthProperty());
    pawnWE.fitHeightProperty().bind(topA.widthProperty());
    pawnWF.fitWidthProperty().bind(topA.widthProperty());
    pawnWF.fitHeightProperty().bind(topA.widthProperty());
    pawnWG.fitWidthProperty().bind(topA.widthProperty());
    pawnWG.fitHeightProperty().bind(topA.widthProperty());
    pawnWH.fitWidthProperty().bind(topA.widthProperty());
    pawnWH.fitHeightProperty().bind(topA.widthProperty());

    pawnBA.fitWidthProperty().bind(topA.widthProperty());
    pawnBA.fitHeightProperty().bind(topA.widthProperty());
    pawnBB.fitWidthProperty().bind(topA.widthProperty());
    pawnBB.fitHeightProperty().bind(topA.widthProperty());
    pawnBC.fitWidthProperty().bind(topA.widthProperty());
    pawnBC.fitHeightProperty().bind(topA.widthProperty());
    pawnBD.fitWidthProperty().bind(topA.widthProperty());
    pawnBD.fitHeightProperty().bind(topA.widthProperty());
    pawnBE.fitWidthProperty().bind(topA.widthProperty());
    pawnBE.fitHeightProperty().bind(topA.widthProperty());
    pawnBF.fitWidthProperty().bind(topA.widthProperty());
    pawnBF.fitHeightProperty().bind(topA.widthProperty());
    pawnBG.fitWidthProperty().bind(topA.widthProperty());
    pawnBG.fitHeightProperty().bind(topA.widthProperty());
    pawnBH.fitWidthProperty().bind(topA.widthProperty());
    pawnBH.fitHeightProperty().bind(topA.widthProperty());
  }

  public Pane getAspectPane() { return aspect; }
}