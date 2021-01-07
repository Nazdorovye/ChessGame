package models;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece {
  protected class BoardCell {
    public boolean capture;
    public final byte x;
    public final byte y;

    public BoardCell(byte x, byte y, boolean capture) {
      this.x = x;
      this.y = y;
      this.capture = capture;
    }
  }

  protected Board board;
  protected ImageView visual;
  // protected ArrayList<??> availableMoves;
  protected boolean white;
  protected byte x;
  protected byte y;

  private void OnMousePressed(MouseEvent e) {}
  private void OnMouseReleased(MouseEvent e) {}

  public Piece(ImageView pieceImage /*..*/) {
    pieceImage.setOnMousePressed(this::OnMousePressed);
    pieceImage.setOnMouseReleased(this::OnMouseReleased);
  }
}
