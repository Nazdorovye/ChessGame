package models;

public class Cell {
  private Piece piece;
  private boolean enPassant = false;

  public Cell(Piece piece) {
    this.piece = piece;
  }

  public Piece getPiece() { return piece; }
  public boolean getEnPassant() { return enPassant; }
}
