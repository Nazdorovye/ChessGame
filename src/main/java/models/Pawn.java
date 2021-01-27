package models;

import java.util.ArrayList;

import service.Colour;
import service.Move;
import service.Move.Type;

public class Pawn extends Piece {
  public enum Converted {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN;

    public boolean pawn() { return this.equals(PAWN); }
  }

  private byte epsm = 0;
  private boolean canMove = false; // pawn real allowed moves may differ from dummy moves
  private Converted conv;

  public Pawn(byte visualIdx, Colour colour, byte col, byte row) {
    super(visualIdx, colour, col, row);
    conv = Converted.PAWN;
  }

  @Override
  public boolean getCanMove() { 
    return (conv.pawn()) ? canMove : super.getCanMove(); 
  }

  public void setConverted(Converted conv) { 
    this.conv = conv;

    if (conv.pawn()) epsm = 0;
  }
  public Converted getConverted() { return conv; }
  public boolean isPawn() { return conv.pawn(); }

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
    
    switch (conv) {
      case ROOK: Rook.calculateLeveledMoves(brd, this); return;
      case KNIGHT: Knight.calcLMoves(brd, this); return;
      case BISHOP: Bishop.calculateDiagonalMoves(brd, this); return;
      case QUEEN: Queen.calcQueenMoves(brd, this); return;
      case PAWN: break;
    }
    
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
          moves.add(new Move(Type.CHECKED_DUMMY, col, row, nextCol, nextRow)); // add dummy move for rival king
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
    if (!conv.pawn()) {
      super.recalcCheckedMoves(brd);
      return;
    }

    ArrayList<Move> new_moves = new ArrayList<Move>(); // list to store valid moves
    
    // Define ally king with polymorphic cast Piece->King
    King king = (colour.white()) ? (King)brd.getPieces()[28] : (King)brd.getPieces()[4]; 
    Piece piece = king.getCheckedPiece(); // piece that checked ally king

    // iterate through all previously calculated moves
    for (Move move : moves) {
      // keep take move
      if ((move.col_dest == piece.getCol() && move.row_dest == piece.getRow()) &&
          (move.type.takepassing() || move.type.take())) {
        new_moves.add(move);
      }

      // iterate through checked piece moves
      for (Move riv_move : piece.moves) {
        // compare if any rival piece move can be intersected thus blocking check
        if (riv_move.col_dest == move.col_dest && riv_move.row_dest == move.row_dest
            && riv_move.type.checked() && (move.type.translate() || move.type.passing())) {

          new_moves.add(new Move(Type.TRANSLATE, col, row, move.col_dest, move.row_dest));
        } 
      }      
    }

    moves = new_moves;
    canMove = moves.size() > 0;
  }

  /**
   * Overrides standard method as it is needed to manually 
   *    handle canMove variable for cell highlighting
   */
  @Override
  protected void recalcPinnedMoves(Board brd) {  
    super.recalcPinnedMoves(brd);
    if (!conv.pawn()) return; // if converted, super method is enough

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
