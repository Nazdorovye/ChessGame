package models;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Piece.BoardCell;
import service.Colour;
import service.Game;
import service.MainBoardCtrl;

public class Board {
  private Game game;
  private Piece nowSelected;
  public Cell[][] cells;
  public static Piece[] pieces;

  private void OnPaneMouseDown(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) {
      e.consume();
      return;
    }

    if (nowSelected == null) {
      e.consume();
      return;
    }

    Pane clicked = (Pane)e.getSource();
    byte paneX = GridPane.getColumnIndex(clicked).byteValue();
    byte paneY = GridPane.getRowIndex(clicked).byteValue();

    for (BoardCell cell : nowSelected.availableMoves) {
      if (cell.x == paneX && cell.y == paneY) {

        nowSelected.unmarkCells();
        nowSelected.x = paneX;
        nowSelected.y = paneY;
        cells[cell.x][cell.y].setPiece(null);
        cells[paneX][paneY].setPiece(nowSelected);
        GridPane.setConstraints(nowSelected.visual, (int)paneX, (int)paneY);

        for (Piece piece : pieces) {
          if (piece == null) continue;
          piece.calcAvalableCells();
        }

        nowSelected = null;
        game.setNextPlayer();
        break;
      }
    }
  }

  public Board(Game game, MainBoardCtrl boardCtrl) {
    this.game = game;
    
    cells = new Cell[8][8];
    pieces = new Piece[32];
    Pane[][] temp = boardCtrl.getCellPanes();
    
    // pieces[0]  = new Rook(this, Colour.Blacks, boardCtrl.getRookBL(), 0, 0);
    // pieces[1]  = new Knight(this, Colour.Blacks, boardCtrl.getKnightBL(), 1, 0);
    // pieces[2]  = new Bishop(this, Colour.Blacks, boardCtrl.getBishopBL(), 2, 0);
    // pieces[3]  = new Queen(this, Colour.Blacks, boardCtrl.getQueenB(), 3, 0);
    // pieces[4]  = new King(this, Colour.Blacks, boardCtrl.getKingB(), 4, 0);
    // pieces[5]  = new Bishop(this, Colour.Blacks, boardCtrl.getBishopBR(), 5, 0);
    // pieces[6]  = new Knight(this, Colour.Blacks, boardCtrl.getKnightBR(), 6, 0);
    // pieces[7]  = new Rook(this, Colour.Blacks, boardCtrl.getRookBR(), 7, 1);
    pieces[8]  = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBA(), 0, 1);
    pieces[9]  = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBB(), 1, 1);
    pieces[10] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBC(), 2, 1);
    pieces[11] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBD(), 3, 1);
    pieces[12] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBE(), 4, 1);
    pieces[13] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBF(), 5, 1);
    pieces[14] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBG(), 6, 1);
    pieces[15] = new Pawn(this, Colour.BLACKS, boardCtrl.getPawnBH(), 7, 1);

    pieces[16] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWA(), 0, 6);
    pieces[17] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWB(), 1, 6);
    pieces[18] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWC(), 2, 6);
    pieces[19] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWD(), 3, 6);
    pieces[20] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWE(), 4, 6);
    pieces[21] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWF(), 5, 6);
    pieces[22] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWG(), 6, 6);
    pieces[23] = new Pawn(this, Colour.WHITES, boardCtrl.getPawnWH(), 7, 6);
    // pieces[24] = new Rook(this, Colour.WHITES, boardCtrl.getRookWL(), 0, 7);
    // pieces[25] = new Knight(this, Colour.WHITES, boardCtrl.getKnightWL(), 1, 7);
    // pieces[26] = new Bishop(this, Colour.WHITES, boardCtrl.getBishopWL(), 2, 7);
    // pieces[27] = new Queen(this, Colour.WHITES, boardCtrl.getQueenW(), 3, 7);
    // pieces[28] = new King(this, Colour.WHITES, boardCtrl.getKingW(), 4, 7);
    // pieces[29] = new Bishop(this, Colour.WHITES, boardCtrl.getBishopWR(), 5, 7);
    // pieces[30] = new Knight(this, Colour.WHITES, boardCtrl.getKnightWR(), 6, 7);
    // pieces[31] = new Rook(this, Colour.WHITES, boardCtrl.getRookWR(), 7, 7);

    int idx = 0;
    for (int row = 0; row <= 7; row++) {
      if (row == 2) { 
        row = 5; 
        continue; 
      }

      for (int col = 0; col <= 7; col++) {
        cells[col][row] = new Cell(pieces[idx], temp[col][row]);
        temp[col][row].setOnMousePressed(this::OnPaneMouseDown);

        idx++;
      }
    }

    for (int row = 2; row < 6; row++) {
      for (int col = 0; col < 8; col++) {
        cells[col][row] = new Cell(null, temp[col][row]);

        temp[col][row].setOnMousePressed(this::OnPaneMouseDown);
      }
    }

    // Move piece visuals to their corresponding indices in GridPane
    boardCtrl.resetPieceIndices();

    for (Piece piece : pieces) {
      if (piece == null) continue;
      piece.calcAvalableCells();
    }
  }

  public boolean isTurnCorrect(Colour colr) { 
    return game.getNextPlayer().equals(colr); 
  }

  public Game getGame() { return game; }
  public Cell[][] getCells() { return cells; }

  public void setNowSelected(Piece nowSelected) { this.nowSelected = nowSelected; }
  public Piece getNowSelected() { return nowSelected; }
}
