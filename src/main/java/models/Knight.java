package models;

import service.Colour;
import service.Move;

public class Knight extends Piece {
    public Knight(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public static void calcLMoves(Board brd, Piece callee) { 
    Piece piece = null;
    byte col_dest, row_dest;
    
    for (byte col_dir = -2; col_dir <= 2; col_dir++) {
      if (col_dir == 0) col_dir = 1;

      col_dest = (byte)(callee.getCol() + col_dir);
      if (col_dest < 0 || col_dest > 7) continue;
      
      for (byte row_dir = (col_dir % 2 == 0) ? (byte)-1 : (byte)-2, count = 0; count < 2; count++, row_dir = (byte)(0 - row_dir)) {
        row_dest = (byte)(callee.getRow() + row_dir);
        if (row_dest < 0 || row_dest > 7) continue;

        piece = brd.getCells()[col_dest][row_dest].getPiece();

        if (piece != null) {
          // found an ally
          if (piece.colour.equals(callee.colour)) {
            piece.setStatus(Status.GUARDED);

          // found the rival king
          } else if (piece.getClass().equals(King.class)) {
            King king = (King)piece;
            king.assignCheckedPiece(callee);

          // found any other rival piece
          } else {
            callee.getMoves().add(new Move(Move.Type.TAKE, callee.getCol(), callee.getRow(), col_dest, row_dest));
          }
        } else callee.moves.add(new Move(Move.Type.TRANSLATE, callee.getCol(), callee.getRow(), col_dest, row_dest));
      }
    }
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();
    Knight.calcLMoves(brd, this);
  }
}