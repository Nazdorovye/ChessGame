package models;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.StateGame;

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

  private void OnMousePressed(MouseEvent e) {
    // Check if player moves HIS piece
    if (board.getGame().getGameState() == StateGame.SELECT_PIECE
        && !board.isTurnCorrect(white)) {
      System.out.println("Invalid turn!");
      return;
    }
  }
  private void OnMouseReleased(MouseEvent e) {}

  public Piece(Board board, boolean white, ImageView pieceImage, int x, int y) {
    pieceImage.setOnMousePressed(this::OnMousePressed);
    pieceImage.setOnMouseReleased(this::OnMouseReleased);

    this.x = (byte)x;
    this.y = (byte)y;
    this.board = board;
    this.white = white;
  }
}
