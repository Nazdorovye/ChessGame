package models;

import service.Colour;
import service.Move;

public class Pawn extends Piece {
  private byte epsm = 0;

  public Pawn(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    // discard outdated moves
    moves.clear();

    if (status.taken()) return;
    
    // pawn direction
    byte dir = colour.direction();

    if (epsm > 0 && --epsm == 0) {
      brd.getCells()[col][row - dir].resetEnPassant();
    }    
    
    // todo: special case for pinned piece
    if (!status.free()) return;

    byte nextRow = (byte)(row + dir);
    // check next cell availability
    if (brd.getCells()[col][nextRow].getPiece() == null) {
      moves.add(new Move(Move.Type.TRANSLATE, col, row, col, nextRow));

      byte plustwo = (byte)(row + (dir * 2));
      // check next cell on passing availability
      if ( (plustwo == 3 && colour.black()) ||
           (plustwo == 4 && colour.white())) {

        if (brd.getCells()[col][plustwo].getPiece() == null) {
          moves.add(new Move(Move.Type.PASSING, col, row, col, plustwo));
        }
      }
    }

    // check next diagonal cell availability; is there a rival piece
    if (col + 1 <= 7 &&
        brd.getCells()[col + 1][nextRow].getPassing() != null &&
        !brd.getCells()[col + 1][nextRow].getPassing().colour.equals(colour)) {

      if (brd.getCells()[col + 1][nextRow].getEnPassant()) {
        moves.add(new Move(Move.Type.TAKEPASSING, col, row, (byte)(col + 1), nextRow));
      } else {
        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col + 1), nextRow));
      }
    }

    // check next other diagonal cell availability; is there a rival piece
    if (col - 1 >= 0 && 
        brd.getCells()[col - 1][nextRow].getPassing() != null && 
        !brd.getCells()[col - 1][nextRow].getPassing().colour.equals(colour)) {

      if (brd.getCells()[col - 1][nextRow].getEnPassant()) {
        moves.add(new Move(Move.Type.TAKEPASSING, col, row, (byte)(col - 1), nextRow));
      } else {
        moves.add(new Move(Move.Type.TAKE, col, row, (byte)(col - 1), nextRow));
      }
    }
  }

  public void setPassingCounter() { epsm = 2; }
}
