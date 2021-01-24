package models;

import java.util.zip.CheckedInputStream;

import service.Colour;
import service.Move;
import service.Move.Type;

public class King extends Piece {
  public King(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public void calcAvalableCells(Board brd) {
    // discard outdated moves
    moves.clear();

    Piece piece = null;

    /* iteratively check every cell around king */
    for (byte col_dest = (byte)(col - 1); col_dest <= col + 1; col_dest++) {
      for (byte row_dest = (byte)(row - 1); row_dest <= row + 1; row_dest++) {

        if (col_dest == 0 && row_dest == 0) {
          continue; // no move in place
        } else if (col_dest < 0 || col_dest > 7 ||
                   row_dest < 0 || row_dest > 7) {
            continue; // no move outside board
        }

        piece = brd.getCells()[col_dest][row_dest].getPiece();
        if (piece == null) {
          
        } else {
          if (piece.colour.equals(colour)) {
            continue; // can do nothing with ally piece
          } else if (!piece.status.guarded()) { // if not guarded, we can take it
            moves.add(new Move(Type.TAKE, col, row, col_dest, row_dest));
          }
        }
      }
    }
  }
}
