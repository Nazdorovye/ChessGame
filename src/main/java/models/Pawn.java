package models;

import service.Colour;
import service.Move;

public class Pawn extends Piece {
  public Pawn(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();
    if (!status.free()) return;

    byte dir = colour.direction();
    byte nextRow = (byte)(row + dir);

    if (brd.getCells()[col][nextRow].getPiece() == null) {
      moves.add(new Move(Move.Type.TRANSLATE, col, row, col, nextRow));

      byte plustwo = (byte)(row + dir * 2);
      switch (plustwo) {
        case 3: if (colour.white()) break;
        case 4: if (colour.black()) break;
        default:
          if (brd.getCells()[col][plustwo].getPiece() != null) break;
          moves.add(new Move(Move.Type.PASSING, col, row, col, plustwo));
      }
    }

    Move.Type mt = Move.Type.TAKE;

    if (col + 1 <= 7 && 
      brd.getCells()[col + 1][nextRow].getPassing() != null && 
      !brd.getCells()[col + 1][nextRow].getPassing().colour.equals(colour)) {

      if (brd.getCells()[col + 1][nextRow].getEnPassant()) mt = Move.Type.PASSING;
      moves.add(new Move(mt, col, row, (byte)(col + 1), nextRow));
    }

    if (col - 1 >= 0 && 
        brd.getCells()[col - 1][nextRow].getPassing() != null && 
        !brd.getCells()[col - 1][nextRow].getPassing().colour.equals(colour)) {

      if (brd.getCells()[col - 1][nextRow].getEnPassant()) mt = Move.Type.PASSING;      
      moves.add(new Move(mt, col, row, (byte)(col - 1), nextRow));
    }
  }
}
