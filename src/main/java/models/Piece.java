package models;

import java.util.ArrayList;
import service.Colour;
import service.Move;

public class Piece {
  public enum Status { 
    FREE, PINNED, CHECKED, TAKEN, GUARDED; 

    public boolean pinned() { return this.equals(PINNED); }
    public boolean taken() { return this.equals(TAKEN); }
    public boolean free() { return this.equals(FREE); }
    public boolean checked() { return this.equals(CHECKED); }
    public boolean guarded() { return this.equals(GUARDED); }
  }

  public final Colour colour;
  public final byte visualIdx;

  protected ArrayList<Move> moves;
  protected Status status;
  protected byte row;
  protected byte col;

  public Piece(byte visualIdx, Colour colour, byte col, byte row) {
    moves = new ArrayList<Move>();

    this.visualIdx = visualIdx;
    this.row = row;
    this.col = col;
    this.colour = colour;
    this.status = Status.FREE;
  }

  public void calcAvalableCells(Board board) {}
  public void recalcCheckedMoves(Board board) {}

  public ArrayList<Move> getMoves() { return moves; }

  public boolean getCanMove() { return moves.size() > 0; }

  public void setRow(byte row) { this.row = row; }
  public byte getRow() { return row; }

  public void setCol(byte col) { this.col = col; }
  public byte getCol() { return col; }

  public void setStatus(Status status) { this.status = status; }
  public Status getStatus() { return status; }
}
