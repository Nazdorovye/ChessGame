package service;

import javafx.scene.image.ImageView;
import models.Board;
import models.Cell;
import models.Pawn;
import models.Piece.Status;

public class Move {
  public enum Type { 
    TRANSLATE, 
    TAKE, 
    PASSING, 
    TAKEPASSING, 
    CHECKED; 
  
  public boolean checked() { return this.equals(CHECKED); }
  }

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

  public void execute(Board brd, MainBoardCtrl brdCtrl, ImageView[] vPieces) throws Exception {
    String excAccum = "";
    
    /* null argument reference exception */
    if (brd == null)     excAccum += "\targ: brd == null\n";
    if (brdCtrl == null) excAccum += "\targ: brtCtrl == null\n";
    if (vPieces == null) excAccum += "\targ: vPieces == null\n";

    if (excAccum != "") throw new Exception(excAccum);
    /* --------------------------------- */

    final Cell c_from = brd.getCells()[col_from][row_from];
    final Cell c_dest = brd.getCells()[col_dest][row_dest];
    
    /* null cell reference exception     */
    if (c_from == null)  excAccum += String.format("\tvar: c_from (Cells[%d][%d]) == null\n",
        col_from, row_from);
    if (c_dest == null)  excAccum += String.format("\tvar: c_dest (Cells[%d][%d]) == null\n",
        col_dest, row_dest);
    
    if (excAccum != "") throw new Exception(excAccum);
    /* --------------------------------- */
        
    try {
      switch (type) {
        case TAKE:
            brdCtrl.setPieceTaken(vPieces[c_dest.getPiece().visualIdx]);
            c_dest.getPiece().setStatus(Status.TAKEN);        
            c_dest.resetEnPassant();
          break;

        case TAKEPASSING: // for pawn
          brdCtrl.setPieceTaken(vPieces[c_dest.getPassing().visualIdx]);
          c_dest.getPassing().setStatus(Status.TAKEN);
          c_dest.resetEnPassant();
          brd.getCells()[col_dest][row_from].removePiece();
          break;

        case PASSING: // for pawn
          brd.getCells()[col_from][row_from + c_from.getPiece().colour.direction()].setEnPassant  (c_from.getPiece());
          Pawn pwn = (Pawn)brd.getCells()[col_from][row_from].getPiece();
          pwn.setPassingCounter();
          break;

        case TRANSLATE:
          break;
        
        case CHECKED:
          return; // for king calc only


        default:
          break;         
      }
    } catch (Exception e) { throw new Exception(e.getMessage()); } 

    brdCtrl.setPieceIndex(vPieces[c_from.getPiece().visualIdx], col_dest, row_dest);
    c_dest.setPiece(c_from.removePiece());
    c_dest.getPiece().setCol(col_dest);         
    c_dest.getPiece().setRow(row_dest);

    // special pawn case for transform
    if (c_dest.getPiece().getClass().isNestmateOf(Pawn.class)) {
      // TODO conversion
    }
  }
}
