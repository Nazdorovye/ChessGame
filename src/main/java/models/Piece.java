package models;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.Colour;
import service.Game;

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
  protected ArrayList<BoardCell> availableMoves;
  protected Colour colour;
  protected byte x;
  protected byte y;

  protected void markCells() {
    board.cells[x][y].setPaneColor("#DDDDDD");
    
    for (BoardCell cell : availableMoves) {
      board.cells[cell.x][cell.y].setPaneColor("#DDDDDD");
    }
  }

  protected void unmarkCells() {
    if (x % 2 == 0) {
      board.cells[x][y].setPaneColor((y % 2 == 0) ? "#FFCE9E" : "#D18B47");
    } else {
      board.cells[x][y].setPaneColor((y % 2 != 0) ? "#FFCE9E" : "#D18B47");
    }

    for (BoardCell cell : availableMoves) {
      if (cell.x % 2 == 0) {
        board.cells[cell.x][cell.y].setPaneColor((cell.y % 2 == 0) ? "#FFCE9E" : "#D18B47");
      } else {
        board.cells[cell.x][cell.y].setPaneColor((cell.y % 2 != 0) ? "#FFCE9E" : "#D18B47");
      }
    }
  }

  protected void OnMousePressed(MouseEvent e) {
    // Do nothing if in menu
    if (board.getGame().getGameState().equals(Game.GameState.MENU)) {
      System.out.println("No move in menu!");
      e.consume();
      return;
    }

    // Do nothing if wrong color
    if (!colour.equals(board.getGame().getNextPlayer())) {
      System.out.println("Wrong colour!");
      e.consume();
      return;
    }

    // Do nothing if already selected piece
    if (!board.getGame().getTurnState().equals(Game.TurnState.SELECT_PIECE)) {
      System.out.println("Select move!");
      e.consume();
      return;
    }
  }

  public Piece(Board board, Colour colour, ImageView pieceImage, int x, int y) {
    pieceImage.setOnMousePressed(this::OnMousePressed);

    availableMoves = new ArrayList<BoardCell>();

    this.x = (byte)x;
    this.y = (byte)y;
    this.board = board;
    this.colour = colour;
    this.visual = pieceImage;
  }

  public void calcAvalableCells() {}
}
