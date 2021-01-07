package models;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece {
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
