package models;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Pawn extends Piece {
  public Pawn(Board board, boolean white, ImageView pieceImage, int x, int y) {
    super(board, white, pieceImage, x, y);
  }

  public void calcAvalableCells() {
    int direction = white ? -1 : 1;
    ArrayList<BoardCell> coords = new ArrayList<BoardCell>();
    
    // определяем доступные координаты        
    coords.add(new BoardCell(x, (byte)(y - 1), false));
    
    if ( (white && y == 6) || (!white && y == 1) ) {
        coords.add(new BoardCell(x, (byte)(y + (direction * 2)), false));
    }        
    
    if (x + 1 <= 7) { 
        coords.add(new BoardCell((byte)(x + 1), (byte)(y + (direction)), false)); 
    }
    
    if (x - 1 >= 0) {
        coords.add(new BoardCell((byte)(x - 1), (byte)(y + (direction)), false));
    }
    
    // откидываем занятые координаты
    Piece temp;
    for (int i = 0; i < coords.size(); i++) {
      temp = board.cells[coords.get(i).x][coords.get(i).y].getPiece();

      if (temp != null) {
        if (temp.white == this.white) {
          coords.remove(i--);
          continue;
        } 

        coords.get(i).capture = true;
      }
    }
  }
}
