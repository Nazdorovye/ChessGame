package models;

import service.Colour;

public class Queen extends Piece {
  public Queen(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();
    Bishop.calculateDiagonalMoves(brd, this);
    Rook.calculateLeveledMoves(brd, this);
  }
}