package models;

import service.Colour;
import service.Move;

public class Queen extends Piece {

    public Queen(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    Bishop.calculateDiagonalMoves(brd, this);
    Rook.calculateVerticalAndHorizontalMoves(brd, this);
  }
}