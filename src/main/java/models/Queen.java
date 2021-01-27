package models;

import service.Colour;

public class Queen extends Piece {
  public Queen(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public static void calcQueenMoves(Board brd, Piece callee) {
    Bishop.calculateDiagonalMoves(brd, callee);
    Rook.calculateLeveledMoves(brd, callee);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();
    calcQueenMoves(brd, this);
  }
}