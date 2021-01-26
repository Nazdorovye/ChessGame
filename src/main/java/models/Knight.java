package models;

import service.Colour;
import service.Move;

public class Knight extends Piece {
    public Knight(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();

    Piece piece = null;
    byte col_dest, row_dest;
    
    for (byte col_dir = -2; col_dir <= 2; col_dir++) {
      if (col_dir == 0) col_dir = 1;

      col_dest = (byte)(col + col_dir);
      if (col_dest < 0 || col_dest > 7) continue;
      
      for (byte row_dir = (col_dir % 2 == 0) ? (byte)-1 : (byte)-2, count = 0; count < 2; count++, row_dir = (byte)(0 - row_dir)) {
        row_dest = (byte)(row + row_dir);
        if (row_dest < 0 || row_dest > 7) continue;

        piece = brd.getCells()[col_dest][row_dest].getPiece();

        if (piece != null) {
          // found an ally
          if (piece.colour.equals(colour)) {
            piece.setStatus(Status.GUARDED);

          // found the rival king
          } else if (piece.getClass().equals(King.class)) {
            King king = (King)piece;
            king.assignCheckedPiece(this);

          // found any other rival piece
          } else {
            moves.add(new Move(Move.Type.TAKE, col, row, col_dest, row_dest));
          }
        } else moves.add(new Move(Move.Type.TRANSLATE, col, row, col_dest, row_dest));
      }
    }
  }
}