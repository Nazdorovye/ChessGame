package models;

import service.Colour;
import service.Move;
import service.Move.Type;

public class King extends Piece {
  private Piece checkedPiece;

  public King(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  public void assignCheckedPiece(Piece piece) {
    if (piece == null) return;
    status = Status.CHECKED;
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

    /* iteratively check every cell around king */
    for (byte col_dest = (byte)(col - 1); col_dest <= col + 1; col_dest++) {
      for (byte row_dest = (byte)(row - 1); row_dest <= row + 1; row_dest++) {

        if (col_dest == col && row_dest == row) {
          continue; // no move in place
        } else if (col_dest < 0 || col_dest > 7 ||
                   row_dest < 0 || row_dest > 7) {
            continue; // no move outside board
        }

        piece = brd.getCells()[col_dest][row_dest].getPiece();
        if (piece == null) {

          for (Piece _piece : brd.getPieces()) {
            if (!canMove) break;
            if (_piece == null || _piece.colour.equals(colour)) continue;

            for (Move move : _piece.moves) {
              if (move.col_dest == col_dest && move.row_dest == row_dest) {
                if (move.type.equals(Type.CHECKED)) canMove = false; // cell covered by rival piece
                break;
              }
            }
          }

        } else {

          if (piece.colour.equals(colour)) {
            piece.status = Status.GUARDED; // guard nearby allies against rival king
            continue;
          } else if (!piece.status.guarded()) { // if not guarded, the king can take it
            moves.add(new Move(Type.TAKE, col, row, col_dest, row_dest));
            canMove = false; // take move already set
          } else if (piece.status.guarded()) { // if guarded, the king cannot take it
            canMove = false; // 
          }
        }

        if (canMove) {
          moves.add(new Move(Type.TRANSLATE, col, row, col_dest, row_dest));
        }

        canMove = true; // reset for next cell
      }
    }
  }
}
