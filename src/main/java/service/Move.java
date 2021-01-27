package service;

import javafx.scene.image.ImageView;
import models.Board;
import models.Cell;
import models.King;
import models.Pawn;
import models.Rook;
import models.Piece.Status;

public class Move {
  public enum Type { 
    TRANSLATE,
    TAKE,
    CASTLE,
    CHECKED,
    PASSING,
    TAKEPASSING,
    CHECKED_DUMMY; 
  
    public boolean checked() { return this.equals(CHECKED); }
    public boolean castle() { return this.equals(CASTLE); }
    public boolean checked_dummy() { return this.equals(CHECKED_DUMMY); }
    public boolean take() { return this.equals(TAKE); }
    public boolean passing() { return this.equals(PASSING); }
    public boolean takepassing() { return this.equals(TAKEPASSING); }
    public boolean translate() { return this.equals(TRANSLATE); }
  }

  public final byte row_from;
  public final byte col_from;
  public final byte row_dest;
  public final byte col_dest;
  public Type type;

  public Move(Type type, byte col_from, byte row_from, byte col_dest, byte row_dest) {
    this.type = type;
    this.row_from = row_from;
    this.col_from = col_from;
    this.row_dest = row_dest;
    this.col_dest = col_dest;
  }

  public boolean execute(Board brd, MainBoardCtrl brdCtrl, ImageView[] vPieces) throws Exception {
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
        
        case CHECKED_DUMMY:
          return false; // for king calc only

        default:
          break;         
      }
    } catch (Exception e) { throw new Exception(e.getMessage()); } 

    brdCtrl.setPieceIndex(vPieces[c_from.getPiece().visualIdx], col_dest, row_dest);
    c_dest.setPiece(c_from.removePiece());
    c_dest.getPiece().setCol(col_dest);         
    c_dest.getPiece().setRow(row_dest);

    if (c_dest.getPiece().getClass().equals(King.class)) {
      King king = (King)c_dest.getPiece();
      king.moved = true;

      if (type.castle()) {
        byte rook_col_dest = (col_dest == 2) ? (byte)3 : 5;

        Cell c_rook_from = brd.getCells()[(col_dest == 2) ? 0 : 7][row_dest];
        Cell c_rook_dest = brd.getCells()[rook_col_dest][row_dest];
        
        brdCtrl.setPieceIndex(vPieces[c_rook_from.getPiece().visualIdx], rook_col_dest, row_dest);
        c_rook_dest.setPiece(c_rook_from.removePiece());
        c_rook_dest.getPiece().setCol(rook_col_dest);         
        c_rook_dest.getPiece().setRow(row_dest);
      }
    }

    if (c_dest.getPiece().getClass().equals(Rook.class)) {
      Rook rook = (Rook)c_dest.getPiece();
      rook.moved = true;
    }

    // special pawn case for transform
    if (c_dest.getPiece().getClass().equals(Pawn.class) && (row_dest == 0 || row_dest == 7)) {
      return true;
    }

    return false;
  }
}
