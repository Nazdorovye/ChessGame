package service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainBoardCtrl {
  private RootResizeListener rrl;

  private class RootResizeListener implements ChangeListener<Number> {
    public final Scene scene;
    public final double aRatio;
  
    @Override 
    public void changed(ObservableValue<? extends Number> value, Number oldVal, Number newVal) {
      final double w = scene.getWidth();
      final double h = scene.getHeight();
  
      if (w < h * aRatio) {
        aspect.setPrefWidth(w);
        aspect.setPrefHeight(w / aRatio);
      } else {
        aspect.setPrefWidth(h * aRatio);
        aspect.setPrefHeight(h);
      }
  
      aspect.setLayoutX((w - aspect.getPrefWidth()) / 2);
      aspect.setLayoutY((h - aspect.getPrefHeight()) / 2);
    }
  
    public RootResizeListener(Scene scene, double aRatio) {
      this.scene = scene;
      this.aRatio = aRatio;
    }
  }


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

  @FXML private Pane pn00;
  @FXML private Pane pn01;
  @FXML private Pane pn02;
  @FXML private Pane pn03;
  @FXML private Pane pn04;
  @FXML private Pane pn05;
  @FXML private Pane pn06;
  @FXML private Pane pn07;

  @FXML private Pane pn10;
  @FXML private Pane pn11;
  @FXML private Pane pn12;
  @FXML private Pane pn13;
  @FXML private Pane pn14;
  @FXML private Pane pn15;
  @FXML private Pane pn16;
  @FXML private Pane pn17;

  @FXML private Pane pn20;
  @FXML private Pane pn21;
  @FXML private Pane pn22;
  @FXML private Pane pn23;
  @FXML private Pane pn24;
  @FXML private Pane pn25;
  @FXML private Pane pn26;
  @FXML private Pane pn27;

  @FXML private Pane pn30;
  @FXML private Pane pn31;
  @FXML private Pane pn32;
  @FXML private Pane pn33;
  @FXML private Pane pn34;
  @FXML private Pane pn35;
  @FXML private Pane pn36;
  @FXML private Pane pn37;

  @FXML private Pane pn40;
  @FXML private Pane pn41;
  @FXML private Pane pn42;
  @FXML private Pane pn43;
  @FXML private Pane pn44;
  @FXML private Pane pn45;
  @FXML private Pane pn46;
  @FXML private Pane pn47;

  @FXML private Pane pn50;
  @FXML private Pane pn51;
  @FXML private Pane pn52;
  @FXML private Pane pn53;
  @FXML private Pane pn54;
  @FXML private Pane pn55;
  @FXML private Pane pn56;
  @FXML private Pane pn57;

  @FXML private Pane pn60;
  @FXML private Pane pn61;
  @FXML private Pane pn62;
  @FXML private Pane pn63;
  @FXML private Pane pn64;
  @FXML private Pane pn65;
  @FXML private Pane pn66;
  @FXML private Pane pn67;

  @FXML private Pane pn70;
  @FXML private Pane pn71;
  @FXML private Pane pn72;
  @FXML private Pane pn73;
  @FXML private Pane pn74;
  @FXML private Pane pn75;
  @FXML private Pane pn76;
  @FXML private Pane pn77;


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

    allPieceSizeBindBoard();
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

  public void initRootResizer(Scene scene, double aRatio) {
    rrl = new RootResizeListener(scene, aRatio);
    scene.widthProperty().addListener(rrl);
    scene.heightProperty().addListener(rrl);
  }

  /* ================= GETTERS ================================================================== */
  public Pane getAspectPane() { return aspect; }
  public Pane getUtilPane() { return utilPane; }
  public GridPane getGridPane() { return board; }

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

  public Pane[][] getCellPanes() { 
    Pane[][] result = new Pane[8][8];

    result[0][0] = pn00;
    result[0][1] = pn01;
    result[0][2] = pn02;
    result[0][3] = pn03;
    result[0][4] = pn04;
    result[0][5] = pn05;
    result[0][6] = pn06;
    result[0][7] = pn07;
    result[1][0] = pn10;
    result[1][1] = pn11;
    result[1][2] = pn12;
    result[1][3] = pn13;
    result[1][4] = pn14;
    result[1][5] = pn15;
    result[1][6] = pn16;
    result[1][7] = pn17;
    result[2][0] = pn20;
    result[2][1] = pn21;
    result[2][2] = pn22;
    result[2][3] = pn23;
    result[2][4] = pn24;
    result[2][5] = pn25;
    result[2][6] = pn26;
    result[2][7] = pn27;
    result[3][0] = pn30;
    result[3][1] = pn31;
    result[3][2] = pn32;
    result[3][3] = pn33;
    result[3][4] = pn34;
    result[3][5] = pn35;
    result[3][6] = pn36;
    result[3][7] = pn37;
    result[4][0] = pn40;
    result[4][1] = pn41;
    result[4][2] = pn42;
    result[4][3] = pn43;
    result[4][4] = pn44;
    result[4][5] = pn45;
    result[4][6] = pn46;
    result[4][7] = pn47;
    result[5][0] = pn50;
    result[5][1] = pn51;
    result[5][2] = pn52;
    result[5][3] = pn53;
    result[5][4] = pn54;
    result[5][5] = pn55;
    result[5][6] = pn56;
    result[5][7] = pn57;
    result[6][0] = pn60;
    result[6][1] = pn61;
    result[6][2] = pn62;
    result[6][3] = pn63;
    result[6][4] = pn64;
    result[6][5] = pn65;
    result[6][6] = pn66;
    result[6][7] = pn67;
    result[7][0] = pn70;
    result[7][1] = pn71;
    result[7][2] = pn72;
    result[7][3] = pn73;
    result[7][4] = pn74;
    result[7][5] = pn75;
    result[7][6] = pn76;
    result[7][7] = pn77;

    return result;
  }
  /* =============== END GETTERS ================================================================ */
}