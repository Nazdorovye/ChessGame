package models;

import java.util.ArrayList;

import service.Colour;
import service.Move;
import service.Move.Type;

public class Rook extends Piece {
  public Rook(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public static void calculateLeveledMoves(Board brd, Piece callee) {
    if (callee.getStatus().taken()) return;

    boolean pieceFound = false;
    boolean kingFound = false;
    Piece potPinnedPiece = null;
    ArrayList<Move> colCheckedMoves = new ArrayList<Move>();

    for (byte col_dir = -1, row_dir = 0, directions = 4, col_mod = 1, row_mod = 1; directions > 0; directions--) {
        
      // 0 -> 1 -> 0 -> -1 -> 0 ...
      if (col_dir + col_mod == 2) col_mod = -1; // reached top, now count down
      else if (col_dir + col_mod == -2) col_mod = 1; // reached bot, now count up

      if (row_dir + row_mod == 2) row_mod = -1; // reached top, now count down
      else if (row_dir + row_mod == -2) row_mod = 1; // reached bot, now count up

      col_dir += col_mod;
      row_dir += row_mod;
      pieceFound = false;
      kingFound = false;
    
      colCheckedMoves.clear();

      for (byte col_dest = (byte)(callee.getCol() + col_dir), 
                row_dest = (byte)(callee.getRow() + row_dir);                
        col_dest >= 0 && col_dest <= 7 && row_dest >= 0 && row_dest <= 7;
        col_dest += col_dir, row_dest += row_dir) {

        // this iterates after rival king was found
        if (kingFound && brd.getCells()[col_dest][row_dest].getPiece() == null) {
            callee.moves.add(
              new Move(Move.Type.CHECKED_DUMMY, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );
            continue;
        }

        if (!pieceFound) {
          // no first piece found, accumulate moves
          if (brd.getCells()[col_dest][row_dest].getPiece() == null) {
            colCheckedMoves.add(
              new Move(Move.Type.TRANSLATE, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );

          // did first find ally piece - add accumulated moves to moves list and iterate next direction
          } else if (brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {
            brd.getCells()[col_dest][row_dest].getPiece().setStatus(Status.GUARDED);
            break;

          // first found piece is rival king 
          } else if (brd.getCells()[col_dest][row_dest].getPiece().getClass().equals(King.class)) {
            King king = (King)brd.getCells()[col_dest][row_dest].getPiece();
            king.assignCheckedPiece(callee);

            for (Move move : colCheckedMoves) move.type = Type.CHECKED;

            kingFound = true;
            continue;

          // first found piece is rival non-king 
          } else {
            callee.getMoves().addAll(colCheckedMoves); // anyways valid moves before piece
            colCheckedMoves.clear(); // clear to collect pinned moves after piece
            
            callee.getMoves().add(
              new Move(Move.Type.TAKE, callee.getCol(), callee.getRow(), col_dest, row_dest)
              );
              
            potPinnedPiece = brd.getCells()[col_dest][row_dest].getPiece();
            pieceFound = true;
          }

        // first rival piece found
        } else {

          // empty cell after rival piece, no use
          if (brd.getCells()[col_dest][row_dest].getPiece() == null) {
            colCheckedMoves.add(
              new Move(Move.Type.CHECKED_DUMMY, callee.getCol(), callee.getRow(), col_dest, row_dest)
            );

          // rival king found after rival piece - pin rival piece
          } else if (brd.getCells()[col_dest][row_dest].getPiece().getClass().equals(King.class)
                && !brd.getCells()[col_dest][row_dest].getPiece().colour.equals(callee.colour)) {
            
            potPinnedPiece.setPinned(colCheckedMoves);
            break;

          // any non-king piece
          } else {
            break;
          }
        }
      }

      callee.moves.addAll(colCheckedMoves);
    }
  }

  @Override
  public void calcAvalableCells(Board brd) {
    moves.clear();
    calculateLeveledMoves(brd, this);
  }
}
