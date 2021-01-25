package models;

import service.Colour;
import service.Move;

public class Bishop extends Piece {
  public Bishop(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public void calculateDiagonalMoves(Board brd, Piece callee) {
    callee.getMoves().clear();
    if (callee.getStatus().taken()) return;

    // todo: special case for pinned piece
    if (callee.getStatus().pinned()) return;

    boolean pieceFound = false;
    Piece potPinnedPiece = null;

    for (byte col_dir = -1; col_dir <= 1; col_dir += 2) {
      for (byte row_dir = -1; row_dir <= 1; row_dir += 2) {
        for (byte col_dest = (byte)(callee.getCol() + col_dir), 
                  row_dest = (byte)(callee.getRow() + row_dir); 

          col_dest >= 0 && col_dest <= 7 && row_dest >= 0 && row_dest <= 7;
          col_dest += col_dir, row_dest += row_dir) {
        
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
            break;
          }
        }
      }
    }
  }

  @Override
  public void calcAvalableCells(Board brd) {
    calculateDiagonalMoves(brd, this);
  }
}
