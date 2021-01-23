package models;

import javafx.scene.layout.Pane;

public class Cell {
  private Piece piece;
  private Pane pane;
  private boolean enPassant = false;

  public Cell(Piece piece, Pane pane) {
    this.piece = piece;
    this.pane = pane;
  }

  public void setPiece(Piece piece) { this.piece = piece; }
  public Piece getPiece() { return piece; }
  public boolean getEnPassant() { return enPassant; }
  public void setPaneColor(String clr) {
    pane.setStyle("-fx-background-color: " + clr);
  }
}
