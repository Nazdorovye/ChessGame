package service;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

  @FXML private AnchorPane utilPane;
  @FXML private Pane root;
  @FXML private Pane aspect;
  @FXML private HBox charTop;
  @FXML private HBox boardMid;
  @FXML private HBox charBottom;
  @FXML private VBox nrLeft;
  @FXML private VBox nrRight;
  @FXML private VBox fullBoard;
  @FXML private VBox takenVBox;
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
    boardMid.prefWidthProperty().bind(boardMid.heightProperty());

    nrLeft.prefWidthProperty().bind(charTop.heightProperty());
    nrRight.prefWidthProperty().bind(charTop.heightProperty());

    board.prefHeightProperty().bind(aspect.heightProperty().multiply(0.88888888889));
    board.prefWidthProperty().bind(board.heightProperty());

    topA.prefWidthProperty().bind(
        charTop.widthProperty().subtract(nrLeft.widthProperty().multiply(2)).divide(8));

    takenVBox.layoutXProperty().bind(fullBoard.widthProperty().add(30));
    takenVBox.prefHeightProperty().bind(fullBoard.heightProperty());
    takenVBox.prefWidthProperty().bind(takenVBox.heightProperty().divide(30).add(10));

    utilPane.layoutXProperty().bind(
        fullBoard.widthProperty().add(takenVBox.widthProperty()).add(50));
    utilPane.prefHeightProperty().bind(fullBoard.heightProperty());
    utilPane.prefWidthProperty().bind(
        aspect.widthProperty().subtract(utilPane.layoutXProperty().add(20)));

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

    // piece indices and size bindings are set in Board constructor
  }

  /* =============== PIECE SIZE BINDINGS ======================================================== */
  public void pieceSizeBindBoard(ImageView piece) {
    piece.fitWidthProperty().bind(topA.widthProperty());
    piece.fitHeightProperty().bind(topA.widthProperty());
  }

  public void allPieceSizeBindBoard() {
    pieceSizeBindBoard(rookWR);
    pieceSizeBindBoard(rookWL);
    pieceSizeBindBoard(rookBR);
    pieceSizeBindBoard(rookBL);

    pieceSizeBindBoard(knightWR);
    pieceSizeBindBoard(knightWL);
    pieceSizeBindBoard(knightBR);
    pieceSizeBindBoard(knightBL);

    pieceSizeBindBoard(bishopWR);
    pieceSizeBindBoard(bishopWL);
    pieceSizeBindBoard(bishopBR);
    pieceSizeBindBoard(bishopBL);

    pieceSizeBindBoard(queenW);
    pieceSizeBindBoard(queenB); 

    pieceSizeBindBoard(kingW);
    pieceSizeBindBoard(kingB);

    pieceSizeBindBoard(pawnWA);
    pieceSizeBindBoard(pawnWB);
    pieceSizeBindBoard(pawnWC);
    pieceSizeBindBoard(pawnWD);
    pieceSizeBindBoard(pawnWE);
    pieceSizeBindBoard(pawnWF);
    pieceSizeBindBoard(pawnWG);
    pieceSizeBindBoard(pawnWH);

    pieceSizeBindBoard(pawnBA);
    pieceSizeBindBoard(pawnBB);
    pieceSizeBindBoard(pawnBC);
    pieceSizeBindBoard(pawnBD);
    pieceSizeBindBoard(pawnBE);
    pieceSizeBindBoard(pawnBF);
    pieceSizeBindBoard(pawnBG);
    pieceSizeBindBoard(pawnBH);
  }

  public void pieceSizeBindTaken(ImageView piece) {
    piece.fitWidthProperty().bind(takenVBox.widthProperty().subtract(10));
    piece.fitHeightProperty().bind(piece.fitHeightProperty());
  }

  public void allPieceSizeBindTaken() {
    pieceSizeBindTaken(rookWR);
    pieceSizeBindTaken(rookWL);
    pieceSizeBindTaken(rookBR);
    pieceSizeBindTaken(rookBL);

    pieceSizeBindTaken(knightWR);
    pieceSizeBindTaken(knightWL);
    pieceSizeBindTaken(knightBR);
    pieceSizeBindTaken(knightBL);

    pieceSizeBindTaken(bishopWR);
    pieceSizeBindTaken(bishopWL);
    pieceSizeBindTaken(bishopBR);
    pieceSizeBindTaken(bishopBL);

    pieceSizeBindTaken(queenW);
    pieceSizeBindTaken(queenB); 

    pieceSizeBindTaken(kingW);
    pieceSizeBindTaken(kingB);

    pieceSizeBindTaken(pawnWA);
    pieceSizeBindTaken(pawnWB);
    pieceSizeBindTaken(pawnWC);
    pieceSizeBindTaken(pawnWD);
    pieceSizeBindTaken(pawnWE);
    pieceSizeBindTaken(pawnWF);
    pieceSizeBindTaken(pawnWG);
    pieceSizeBindTaken(pawnWH);

    pieceSizeBindTaken(pawnBA);
    pieceSizeBindTaken(pawnBB);
    pieceSizeBindTaken(pawnBC);
    pieceSizeBindTaken(pawnBD);
    pieceSizeBindTaken(pawnBE);
    pieceSizeBindTaken(pawnBF);
    pieceSizeBindTaken(pawnBG);
    pieceSizeBindTaken(pawnBH);
  }
  /* ============== END PIECE SIZE BINDINGS ===================================================== */

  public void resetPieceIndices() {
    allPieceSizeBindBoard();

    GridPane.setConstraints(rookWR, 7, 7);
    GridPane.setConstraints(rookWL, 0, 7);
    GridPane.setConstraints(rookBR, 7, 0);
    GridPane.setConstraints(rookBL, 0, 0);

    GridPane.setConstraints(knightWR, 6, 7);
    GridPane.setConstraints(knightWL, 1, 7);
    GridPane.setConstraints(knightBR, 6, 0);
    GridPane.setConstraints(knightBL, 1, 0);

    GridPane.setConstraints(bishopWR,  5,  7);
    GridPane.setConstraints(bishopWL,  2,  7);
    GridPane.setConstraints(bishopBR,  5,  0);
    GridPane.setConstraints(bishopBL,  2,  0);

    GridPane.setConstraints(queenW, 3, 7);
    GridPane.setConstraints(queenB, 3, 0);

    GridPane.setConstraints(kingW, 4, 7);
    GridPane.setConstraints(kingB, 4, 0);

    GridPane.setConstraints(pawnWA, 0, 6);
    GridPane.setConstraints(pawnWB, 1, 6);
    GridPane.setConstraints(pawnWC, 2, 6);
    GridPane.setConstraints(pawnWD, 3, 6);
    GridPane.setConstraints(pawnWE, 4, 6);
    GridPane.setConstraints(pawnWF, 5, 6);
    GridPane.setConstraints(pawnWG, 6, 6);
    GridPane.setConstraints(pawnWH, 7, 6);

    GridPane.setConstraints(pawnBA, 0, 1);
    GridPane.setConstraints(pawnBB, 1, 1);
    GridPane.setConstraints(pawnBC, 2, 1);
    GridPane.setConstraints(pawnBD, 3, 1);
    GridPane.setConstraints(pawnBE, 4, 1);
    GridPane.setConstraints(pawnBF, 5, 1);
    GridPane.setConstraints(pawnBG, 6, 1);
    GridPane.setConstraints(pawnBH, 7, 1);
  }

  public void setPieceIndex(ImageView piece, int x, int y) {
    GridPane.setColumnIndex(piece, x);
    GridPane.setRowIndex(piece, y);
  }

  /* ================= GETTERS ================================================================== */
  public Pane getAspectPane() { return aspect; }
  public Pane getUtilPane() { return utilPane; }

  public ImageView getRookWR() { return rookWR; }
  public ImageView getRookWL() { return rookWL; }
  public ImageView getRookBR() { return rookBR; }
  public ImageView getRookBL() { return rookBL; }

  public ImageView getKnightWR() { return knightWR; }
  public ImageView getKnightWL() { return knightWL; }
  public ImageView getKnightBR() { return knightBR; }
  public ImageView getKnightBL() { return knightBL; }

  public ImageView getBishopWR() { return bishopWR; }
  public ImageView getBishopWL() { return bishopWL; }
  public ImageView getBishopBR() { return bishopBR; }
  public ImageView getBishopBL() { return bishopBL; }
  
  public ImageView getQueenW() { return queenW; }
  public ImageView getQueenB() { return queenB; }

  public ImageView getKingW() { return kingW; }
  public ImageView getKingB() { return kingB; }

  public ImageView getPawnWA() { return pawnWA; }
  public ImageView getPawnWB() { return pawnWB; }
  public ImageView getPawnWC() { return pawnWC; }
  public ImageView getPawnWD() { return pawnWD; }
  public ImageView getPawnWE() { return pawnWE; }
  public ImageView getPawnWF() { return pawnWF; }
  public ImageView getPawnWG() { return pawnWG; }
  public ImageView getPawnWH() { return pawnWH; }

  public ImageView getPawnBA() { return pawnBA; }
  public ImageView getPawnBB() { return pawnBB; }
  public ImageView getPawnBC() { return pawnBC; }
  public ImageView getPawnBD() { return pawnBD; }
  public ImageView getPawnBE() { return pawnBE; }
  public ImageView getPawnBF() { return pawnBF; }
  public ImageView getPawnBG() { return pawnBG; }
  public ImageView getPawnBH() { return pawnBH; }
  /* =============== END GETTERS ================================================================ */
}