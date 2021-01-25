package models;

import service.Colour;
import service.Move;

public class Knight extends Piece {

    public Knight(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public void calculateKnightMoves(Board brd, Piece callee) {
    callee.getMoves().clear();
    if (callee.getStatus().taken()) return;

    // todo: special case for pinned piece
    if (callee.getStatus().pinned()) return;

    boolean pieceFound = false;
    Piece potPinnedPiece = null;

    for (byte col_dir = -2; col_dir <= 2; col_dir++) {
      for (byte row_dir = -2; row_dir <= 2; row_dir++) {
        if (row_dir == 0 || col_dir == 0 || Math.abs(row_dir) == Math.abs(col_dir)) { continue;}
        
        byte col_dest = (byte)(callee.getCol() + col_dir); 
        byte row_dest = (byte)(callee.getRow() + row_dir);
        if ( col_dest >= 0 && col_dest <= 7 && row_dest >= 0 && row_dest <= 7) {

          if (pieceFound == true) {
            if (brd.getCells()[col_dest][row_dest].getPiece() == null) { continue; }
            else if (brd.getCells()[col_dest][row_dest].getPiece().getClass() == King.class
                  && !brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {

              potPinnedPiece.setStatus(Status.PINNED);
            }
          } else if (brd.getCells()[col_dest][row_dest].getPiece() == null) {
            callee.getMoves().add(
              new Move(Move.Type.TRANSLATE, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );

          } else if (brd.getCells()[col_dest][row_dest].getPiece() != null
                 && !brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {
            callee.getMoves().add(
              new Move(Move.Type.TAKE, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );

            potPinnedPiece = brd.getCells()[col_dest][row_dest].getPiece();
            pieceFound = true;
          } else {
            pieceFound = false;
            
          }
        }
      }
    }
  }

  @Override
  public void calcAvalableCells(Board brd) {
    calculateKnightMoves(brd, this);
  }
}