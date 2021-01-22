package models;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.Colour;
import service.Game.TurnState;

public class Pawn extends Piece {
  public Pawn(Board board, Colour colour, ImageView pieceImage, int x, int y) {
    super(board, colour, pieceImage, x, y);
  }

  @Override
  protected void OnMousePressed(MouseEvent e) {
    super.OnMousePressed(e);

    // consumed in parent class
    if (e.isConsumed()) return;

    board.getGame().setTurnState(TurnState.SELECT_MOVE);
    board.setNowSelected(this);
    markCells();
  }

  @Override
  public void calcAvalableCells() {
    int direction = colour.direction();
    availableMoves.clear();
    
    // cell next to pawn along direction
    if ( board.cells[x][y + direction].getPiece() == null ) { 
      availableMoves.add(new BoardCell(x, (byte)(y + direction), false));
    }
    
    // en passant along direction
    if ( (colour.white() && y == 6 && board.cells[x][5].getPiece() == null &&
          board.cells[x][4].getPiece() == null ) || 

         (colour.black() && y == 1 && board.cells[x][2].getPiece() == null &&
          board.cells[x][3].getPiece() == null )) {
      availableMoves.add(new BoardCell(x, (byte)(y + (direction * 2)), false));
    }        
    
    // kill right
    if (x + 1 <= 7 && board.cells[x + 1][y + direction].getPiece() != null &&
        !board.cells[x + 1][y + direction].getPiece().colour.equals(this.colour)) { 
      availableMoves.add(new BoardCell((byte)(x + 1), (byte)(y + (direction)), false)); 
    }
    
    // kill left
    if (x - 1 >= 0 && board.cells[x - 1][y + direction].getPiece() != null &&
        !board.cells[x - 1][y + direction].getPiece().colour.equals(this.colour)) {
      availableMoves.add(new BoardCell((byte)(x - 1), (byte)(y + (direction)), false));
    }
  }
}
