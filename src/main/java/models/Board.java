package models;

import service.Game;
import service.MainBoardCtrl;

public class Board {
  private Game game;
  public Cell[][] cells;
  public static Piece[] pieces;

  public Board(Game game, MainBoardCtrl boardCtrl) {
    this.game = game;
    
    cells = new Cell[8][8];
    pieces = new Piece[32];
    
    // pieces[0]  = new Rook(this, false, boardCtrl.getRookBL(), 0, 0);
    // pieces[1]  = new Knight(this, false, boardCtrl.getKnightBL(), 1, 0);
    // pieces[2]  = new Bishop(this, false, boardCtrl.getBishopBL(), 2, 0);
    // pieces[3]  = new Queen(this, false, boardCtrl.getQueenB(), 3, 0);
    // pieces[4]  = new King(this, false, boardCtrl.getKingB(), 4, 0);
    // pieces[5]  = new Bishop(this, false, boardCtrl.getBishopBR(), 5, 0);
    // pieces[6]  = new Knight(this, false, boardCtrl.getKnightBR(), 6, 0);
    // pieces[7]  = new Rook(this, false, boardCtrl.getRookBR(), 7, 1);
    pieces[8]  = new Pawn(this, false, boardCtrl.getPawnBA(), 0, 1);
    pieces[9]  = new Pawn(this, false, boardCtrl.getPawnBB(), 1, 1);
    pieces[10] = new Pawn(this, false, boardCtrl.getPawnBC(), 2, 1);
    pieces[11] = new Pawn(this, false, boardCtrl.getPawnBD(), 3, 1);
    pieces[12] = new Pawn(this, false, boardCtrl.getPawnBE(), 4, 1);
    pieces[13] = new Pawn(this, false, boardCtrl.getPawnBF(), 5, 1);
    pieces[14] = new Pawn(this, false, boardCtrl.getPawnBG(), 6, 1);
    pieces[15] = new Pawn(this, false, boardCtrl.getPawnBH(), 7, 1);

    pieces[16] = new Pawn(this, true, boardCtrl.getPawnWA(), 0, 6);
    pieces[17] = new Pawn(this, true, boardCtrl.getPawnWB(), 1, 6);
    pieces[18] = new Pawn(this, true, boardCtrl.getPawnWC(), 2, 6);
    pieces[19] = new Pawn(this, true, boardCtrl.getPawnWD(), 3, 6);
    pieces[20] = new Pawn(this, true, boardCtrl.getPawnWE(), 4, 6);
    pieces[21] = new Pawn(this, true, boardCtrl.getPawnWF(), 5, 6);
    pieces[22] = new Pawn(this, true, boardCtrl.getPawnWG(), 6, 6);
    pieces[23] = new Pawn(this, true, boardCtrl.getPawnWH(), 7, 6);
    // pieces[24] = new Rook(this, true, boardCtrl.getRookWL(), 0, 7);
    // pieces[25] = new Knight(this, true, boardCtrl.getKnightWL(), 1, 7);
    // pieces[26] = new Bishop(this, true, boardCtrl.getBishopWL(), 2, 7);
    // pieces[27] = new Queen(this, true, boardCtrl.getQueenW(), 3, 7);
    // pieces[28] = new King(this, true, boardCtrl.getKingW(), 4, 7);
    // pieces[29] = new Bishop(this, true, boardCtrl.getBishopWR(), 5, 7);
    // pieces[30] = new Knight(this, true, boardCtrl.getKnightWR(), 6, 7);
    // pieces[31] = new Rook(this, true, boardCtrl.getRookWR(), 7, 7);

    for (int y = 2; y < 6; y++) {
      for (int x = 0; x < 8; x++) {
        cells[x][y] = new Cell(null);
      }
    }

    // Move piece visuals to their corresponding indices in GridPane
    boardCtrl.resetPieceIndices();
  }

  public boolean isTurnCorrect(boolean white) { return white == game.getNextWhite(); }

  public Game getGame() { return game; }
}
