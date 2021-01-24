package service;

import javafx.scene.image.ImageView;
import models.Board;
import models.Cell;
import models.Pawn;

public class Move {
  public enum Type { TRANSLATE, TAKE, PASSING, TAKEPASSING; }

  public final Type type;
  public final byte row_from;
  public final byte col_from;
  public final byte row_dest;
  public final byte col_dest;

  public Move(Type type, byte col_from, byte row_from, byte col_dest, byte row_dest) {
    this.type = type;
    this.row_from = row_from;
    this.col_from = col_from;
    this.row_dest = row_dest;
    this.col_dest = col_dest;
  }

  public void execute(Board board, MainBoardCtrl boardCtrl, ImageView[] visual_pieces) { 
    final Cell c_from = board.getCells()[col_from][row_from];
    final Cell c_dest = board.getCells()[col_dest][row_dest];
    final boolean isPawn = c_from.getPiece().getClass() == Pawn.class;

    switch (type) {
      case TAKE:     
        boardCtrl.setPieceTaken(visual_pieces[c_dest.getPiece().visualIdx]);

      case TAKEPASSING:
        if (isPawn) {
          boardCtrl.setPieceTaken(visual_pieces[c_dest.getPiece().visualIdx]);
          c_dest.setEnPassant(false);
        }

      case PASSING:
        if (isPawn) {
          board.getCells()[col_from][row_from + c_from.getPiece().colour.direction()].setEnPassant(true);
        }

      case TRANSLATE:
        

      default:
        if (isPawn) {
          board.getCells()[col_from][row_from - c_from.getPiece().colour.direction()].setEnPassant(false);
        }

        boardCtrl.setPieceIndex(visual_pieces[c_from.getPiece().visualIdx], col_dest, row_dest);
        c_dest.setPiece(c_from.removePiece());            
    }
  }
}
