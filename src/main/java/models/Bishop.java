package models;

import java.util.ArrayList;

import service.Colour;
import service.Move;
public class Bishop extends Piece {
  public Bishop(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public static void calculateDiagonalMoves(Board brd, Piece callee) {
    callee.getMoves().clear();
    if (callee.getStatus().taken()) return;

    boolean pieceFound = false;
    Piece potPinnedPiece = null;

    for (byte col_dir = -1; col_dir <= 1; col_dir += 2) {
      for (byte row_dir = -1; row_dir <= 1; row_dir += 2) {
        for (byte col_dest = (byte)(callee.getCol() + col_dir), 
                  row_dest = (byte)(callee.getRow() + row_dir),
                  step = 0;

          col_dest >= 0 && col_dest <= 7 && row_dest >= 0 && row_dest <= 7;
          col_dest += col_dir, row_dest += row_dir, step++) {
        
          if (pieceFound == true) {

            if (brd.getCells()[col_dest][row_dest].getPiece() == null) {
              callee.getMoves().add(
                new Move(Move.Type.CHECKED, callee.getCol(), callee.getRow(), col_dest, row_dest)
              );
              potPinnedPiece.setPinned(null);
              continue; 

            } else if (brd.getCells()[col_dest][row_dest].getPiece().getClass() == King.class
                  && !brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {

              ArrayList<Move> mv = new ArrayList<Move>();
              for (int i = callee.getMoves().size() - step; i < callee.getMoves().size(); i++){
                mv.add(callee.getMoves().get(i));
              }

              potPinnedPiece.setPinned(mv);
            } 

            pieceFound = false;
            break;

          } else if (brd.getCells()[col_dest][row_dest].getPiece() == null) {
            callee.getMoves().add(
              new Move(Move.Type.TRANSLATE, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );

          } else if (brd.getCells()[col_dest][row_dest].getPiece().getClass().equals(King.class)) {
            King king = (King)brd.getCells()[col_dest][row_dest].getPiece();
            king.assignCheckedPiece(callee);
            pieceFound = false;
            break;

          } else if (!brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {
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
