package models;

import java.util.ArrayList;

import service.Colour;
import service.Move;

public class Piece {
  public enum Status {
    FREE, PINNED, CHECKED, TAKEN, GUARDED, PINGUARD; 

    public boolean pinned() { return this.equals(PINNED) || this.equals(PINGUARD); }
    public boolean taken() { return this.equals(TAKEN); }
    public boolean free() { return this.equals(FREE); }
    public boolean checked() { return this.equals(CHECKED); }
    public boolean guarded() { return this.equals(GUARDED) || this.equals(PINGUARD); }

    public boolean canFree() { return !this.pinned() && !this.guarded(); }
  }

  public final Colour colour;
  public final byte visualIdx;

  protected ArrayList<Move> moves;
  protected ArrayList<Move> pinMoves;
  protected Status status;
  protected byte row;
  protected byte col;

  public Piece(byte visualIdx, Colour colour, byte col, byte row) {
    moves = new ArrayList<Move>();
    pinMoves = new ArrayList<Move>();

    this.visualIdx = visualIdx;
    this.row = row;
    this.col = col;
    this.colour = colour;
    this.status = Status.FREE;
  }

  public void forceStatus(Status status) { this.status = status; }
  
  // overriden
  public void calcAvalableCells(Board board) {}

  protected void recalcPinnedMoves(Board brd) {
    ArrayList<Move> new_moves = new ArrayList<Move>(); // list to store valid moves

    for (Move move : moves) {
      for (Move pinMove : pinMoves) {
        if (move.col_dest == pinMove.col_dest && move.row_dest == pinMove.row_dest)
          new_moves.add(move);
      }
    }

    moves = new_moves;
  }

  public void recalcCheckedMoves(Board brd) {
    ArrayList<Move> new_moves = new ArrayList<Move>(); // list to store valid moves
    
    // Define ally king with polymorphic cast Piece->King
    King king = (colour.white()) ? (King)brd.getPieces()[28] : (King)brd.getPieces()[4]; 
    Piece piece = king.getCheckedPiece(); // piece that checked ally king

    for (Move move : moves) {
      // keep take move
      if (move.col_dest == piece.getCol() && move.row_dest == piece.getRow() && move.type.take()) {
        new_moves.add(move);
      }

      for (Move riv_move : piece.moves) {
        // compare if any rival piece move can be intersected thus blocking check
        if (riv_move.col_dest == move.col_dest && riv_move.row_dest == move.row_dest
            && riv_move.type.checked()) {

          new_moves.add(move);
        } 
      }
    }
    
    moves = new_moves;
  }

  public ArrayList<Move> getMoves() { return moves; }
  public ArrayList<Move> getPinMoves() { return pinMoves; }

  public boolean getCanMove() { return moves.size() > 0; }

  public void setRow(byte row) { this.row = row; }
  public byte getRow() { return row; }

  public void setCol(byte col) { this.col = col; }
  public byte getCol() { return col; }

  public void setPinned(ArrayList<Move> pinMoves) {
    if (pinMoves != null) {
      setStatus(status.equals(Status.GUARDED) ? Status.PINGUARD : Status.PINNED);
      this.pinMoves.addAll(pinMoves);
    } else {
      this.pinMoves.clear();
      setStatus(status.equals(Status.PINGUARD) ? Status.GUARDED : Status.FREE);
    }
  }

  public void setStatus(Status status) {
    if (this.status.checked()) return;

    switch (status) {
      case GUARDED:
        this.status = this.status.pinned() ? Status.PINGUARD : Status.GUARDED;
        break;

      case PINNED:
        this.status = this.status.guarded() ? Status.PINGUARD : Status.PINNED;
        break;

      case FREE:
        if (this.status.pinned()) 
          this.status = Status.PINNED;
        else if (this.status.guarded()) 
          this.status = Status.FREE;
        break;

      default:
        this.status = status;
    }
  }
  public Status getStatus() { return status; }
}
