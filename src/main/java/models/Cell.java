package models;

import javafx.scene.layout.Pane;

public class Cell {
  public enum Mark { 
    CLEAR, MOVE, SELECTED, PINNED, TAKE; 
  
    public String getColour(boolean dark) { 
      switch (this) {
        case CLEAR: return dark ? "#D18B47" : "#FFCE9E";
        case MOVE: return "#DDDDDD";
        case SELECTED: return "#7f7f7f";
        case PINNED: return "#fc5a5a";
        case TAKE: return "#5afc68";
        default: return "";
      }
    }
  }

  private Piece piece;
  private Pane pane;
  private boolean dark;
  private boolean enPassant = false;

  public Cell(Piece piece, Pane pane, boolean dark) {
    this.piece = piece;
    this.pane = pane;
    this.dark = dark;
  }

  public void markCell(Mark mark) {
    pane.setStyle("-fx-background-color: " + mark.getColour(dark));
  }

  public Piece removePiece() { 
    Piece result = piece;
    piece = null;
    return result;
  }
  public void setPiece(Piece piece) { this.piece = piece; }
  public Piece getPiece() { return enPassant ? null : piece; }
  public Piece getPassing() { return piece; }

  public void setEnPassant(boolean enPassant) { this.enPassant = enPassant; }
  public boolean getEnPassant() { return enPassant; }
}
