package models;

import java.util.ArrayList;

import service.Colour;
import service.Move;
import service.Move.Type;

public class Pawn extends Piece {
  private byte epsm = 0;
  private boolean canMove = false; // pawn real allowed moves may differ from dummy moves

  public Pawn(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
  }

  @Override
  public boolean getCanMove() { return canMove; }

  /**
   * Calculates every possible move for a pawn
   * Moves are stored in moves: ArrayList
   */
  @Override
  public void calcAvalableCells(Board brd) {

    // discard outdated moves
    moves.clear();
    canMove = false;

    if (status.taken()) return;
    
    // pawn direction
    byte dir = colour.direction();

    if (epsm > 0 && --epsm == 0) {
      brd.getCells()[col][row - dir].resetEnPassant();
    }

    byte nextRow = (byte)(row + dir);
    // check next cell availability
    if (brd.getCells()[col][nextRow].getPiece() == null) {
      moves.add(new Move(Type.TRANSLATE, col, row, col, nextRow));
      canMove = true;

      byte plustwo = (byte)(row + (dir * 2));
      // check next cell on passing availability
      if ( (plustwo == 3 && colour.black()) ||
           (plustwo == 4 && colour.white())) {

        if (brd.getCells()[col][plustwo].getPiece() == null) {
          moves.add(new Move(Type.PASSING, col, row, col, plustwo));
        }
      }
    }

    Cell cell = null; // variable to ease code reading

    // diagonal move (take, take on pass) availability
    // loop used to shorten code; iterates two times with:
    //  i(0): nextCol = col + 1
    //  i(1): nextCol = col - 1
    for (byte i = 0, nextCol = (byte)(col + 1); i < 2; i++) {
      if (nextCol <= 7 && nextCol >= 0) {
        cell = brd.getCells()[nextCol][nextRow]; // cell under take/ take on pass

        if (cell.getPassing() == null) { // if cell is free, disallow the rival king to move here
          moves.add(new Move(Type.CHECKED, col, row, nextCol, nextRow)); // add dummy move for rival king
          // no canMove here, because pawn does not translate diagonally without take
        }
        
        if (cell.getPassing() != null) {
          if (!cell.getPassing().colour.equals(colour)) {
            if (cell.getEnPassant()) { // if cell is on pass by a rival pawn
              moves.add(new Move(Type.TAKEPASSING, col, row, nextCol, nextRow)); // add take on pass move
              canMove = true;

            } else {

              if (cell.getPiece().getClass().equals(King.class)) { // if cell contains a rival king
                King king = (King)cell.getPiece(); // Piece->King cast (polymorphism)
                king.assignCheckedPiece(this); // make rival king checked
              } else { // if any other rival piece
                moves.add(new Move(Type.TAKE, col, row, nextCol, nextRow)); // add take move
                canMove = true;
              }
            }
          } else if (cell.getPiece() != null && cell.getPiece().colour.equals(colour)) {
              cell.getPiece().setStatus(Status.GUARDED);
          }
        }
      }

      nextCol = (byte)(col - 1); // for i(1)
    }
  }
  
  /**
   * Special method to remove all invalid moves if ally king is checked
   */
  @Override
  public void recalcCheckedMoves(Board brd) {
    if (status.taken()) return;

    ArrayList<Move> new_moves = new ArrayList<Move>(); // list to store valid moves
    
    // Define ally king with polymorphic cast Piece->King
    King king = (colour.white()) ? (King)brd.getPieces()[27] : (King)brd.getPieces()[3]; 
    Piece piece = king.getCheckedPiece(); // piece that checked ally king

    // iterate through all previously calculated moves
    for (Move move : moves) {
      // keep take move
      if (brd.getCells()[move.col_dest][move.row_dest].getPassing() != null 
          && brd.getCells()[move.col_dest][move.row_dest].getPassing().equals(piece)) {

        // keep take on pass
        if (brd.getCells()[move.col_dest][move.row_dest].getEnPassant()) {
          new_moves.add(new Move(Type.TAKEPASSING, col, row, move.col_dest, move.row_dest));
        } else {
          new_moves.add(new Move(Type.TAKE, col, row, piece.col, piece.row));
        }
      }

      // iterate through checked piece moves
      for (Move riv_move : piece.moves) {
        // compare if any rival piece move can be intersected thus blocking check
        if (riv_move.col_dest == move.col_dest && riv_move.row_dest == move.row_dest
            && riv_move.type.equals(Type.TRANSLATE)) {

          new_moves.add(new Move(Type.MOVE_CHECKED, col, row, move.col_dest, move.row_dest));
        } 
      }      
    }

    moves = new_moves;
  }

  /**
   * Overrides standard method as it is needed to manually 
   *    handle canMove variable for cell highlighting
   */
  @Override
  protected void recalcPinnedMoves(Board brd) {    
    super.recalcPinnedMoves(brd);

    canMove = false;
    
    for (Move move : moves) {
      if (move.type.take() || move.type.takepassing() || move.type.translate())
        canMove = true;
    }
  }

  /**
   * Method to set passing counter
   */
  public void setPassingCounter() { epsm = 2; }
}
