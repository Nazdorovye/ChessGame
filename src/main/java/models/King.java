package models;

import service.Colour;
import service.Move;
import service.Move.Type;

public class King extends Piece {
  private Piece checkedPiece;
  public boolean moved = false;

  public King(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public void assignCheckedPiece(Piece piece) {
    if (piece == null) return;
    setStatus(Status.CHECKED);
    checkedPiece = piece;
  }

  public void uncheck() {
    status = Status.FREE;
    checkedPiece = null;
  }

  public Piece getCheckedPiece() { return checkedPiece; }

  @Override
  public boolean getCanMove() {
    return moves.size() > 0;
  }

  @Override
  public void calcAvalableCells(Board brd) {
    // discard outdated moves
    moves.clear();

    Piece piece = null;
    boolean canMove = true;
    
    // get rook indices (black): 0, 7; (white):24, 31
    int aRookIdx = (colour.black()) ? 0 : 24;
    Rook rookA = (Rook)brd.getPieces()[aRookIdx];    
    Rook rookH = (Rook)brd.getPieces()[aRookIdx + 7];    

    int horSpan = (moved) ? 1 : 2; // horizontal span to (check castling cells)
    boolean cstlA = rookA.castle && !moved;
    boolean cstlH = rookH.castle && !moved;

    /* iteratively check every cell around king */
    
    for (byte row_dest = (byte)(row - 1); row_dest <= row + 1; row_dest++) {
      for (byte col_dest = (byte)(col - horSpan); col_dest <= col + horSpan; col_dest++) {
        if ((col_dest == col + 2 || col_dest == col - 2) && row_dest != row) 
          continue; 

        if (col_dest == col && row_dest == row) {
          continue; // no move in place
        } else if (col_dest < 0 || col_dest > 7 ||
                   row_dest < 0 || row_dest > 7) {
            continue; // no move outside board
        }

        piece = brd.getCells()[col_dest][row_dest].getPiece();
        if (piece == null) {

          for (Piece rival_piece : brd.getPieces()) {
            if (!canMove) break;
            if (rival_piece == null || rival_piece.colour.equals(colour)) continue;

            for (Move move : rival_piece.moves) {
              if (move.col_dest == col_dest && move.row_dest == row_dest) {
                if (move.type.checked_dummy()) 
                canMove = false; // cell covered by rival piece

                if (!rival_piece.getClass().equals(Pawn.class)) 
                  canMove = false;
                else {
                  Pawn pawn = (Pawn)rival_piece;
                  if (!pawn.isPawn()) 
                    canMove = false;
                }

                if (rival_piece.getClass().equals(King.class)) {
                  rival_piece.getMoves().remove(move); // remove this move from another king
                }

                break;
              }
            }
          }

        } else {

          if (piece.colour.equals(colour)) {
            piece.setStatus(Status.GUARDED); // guard nearby allies against rival king
            continue;
          } else if (!piece.status.guarded()) { // if not guarded, the king can take it
            moves.add(new Move(Type.TAKE, col, row, col_dest, row_dest));
            canMove = false; // take move already set
          } else if (piece.status.guarded()) { // if guarded, the king cannot take it
            canMove = false; // 
          }
        }

        if (row_dest == row && !moved && !canMove) {
            cstlA = false;
            cstlH = false;
        }

        if (canMove && col - col_dest > - 2 && col - col_dest < 2) {
          moves.add(new Move(Type.TRANSLATE, col, row, col_dest, row_dest));
        }

        canMove = true; // reset for next cell
      }
    }

    if (cstlA && rookA.castle) {
      moves.add(new Move(Type.CASTLE, col, row, (byte)2, row));
    }
    if (cstlH && rookH.castle) {
      moves.add(new Move(Type.CASTLE, col, row, (byte)6, row));
    }
  }

  @Override
  public void recalcCheckedMoves(Board brd) {
    // nothing here
  }
}
