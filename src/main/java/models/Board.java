package models;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Cell.Mark;
import service.Colour;
import service.Game;
import service.MainBoardCtrl;
import service.Move;
import service.Game.TurnState;

public class Board {
  private Game game;
  private Piece nowSelected;
  public Cell[][] cells;
  public Pane[][] visual_cells;
  public Piece[] pieces;
  public ImageView[] visual_pieces;

  private void highlightMoveCells(Piece piece, boolean clear) {
    ArrayList<Move> availableMoves = piece.getMoves();

    Mark mrk = Mark.CLEAR;
    for (Move move : availableMoves) {
      if (!clear) {
        switch (move.type) {
          case TAKE: mrk = Mark.TAKE; break;
          case TAKEPASSING: mrk = Mark.TAKE; break;
          case PASSING: mrk = Mark.MOVE; break;
          case TRANSLATE: mrk = Mark.MOVE; break;
          default: mrk = Mark.CLEAR;
        }
      }

      cells[move.col_dest][move.row_dest].markCell(mrk);
    }
  }

/* === ON PIECE MOUSE DOWN EVENT HANDLER ======================================================== */
  private void OnPieceMouseDown(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) {
      e.consume();
      return;
    }

    ImageView visual_piece = (ImageView)e.getSource();
    int row = GridPane.getRowIndex(visual_piece);
    int col = GridPane.getColumnIndex(visual_piece);

    nowSelected = cells[col][row].getPiece();
    ArrayList<Move> availableMoves = nowSelected.getMoves();

    if (availableMoves.size() == 0) {
      nowSelected = null;
      e.consume();
      return;
    }

    cells[col][row].markCell(Mark.SELECTED);
    highlightMoveCells(nowSelected, false);
    setAllPiecesTransparent();
    game.setTurnState(TurnState.SELECT_MOVE);
  }


/* === ON PIECE MOUSE ENTER EVENT HANDLER ======================================================= */
  private void OnPieceMouseEntered(MouseEvent e) {
    // piece selected, no further highlight needed
    if (nowSelected != null) {
      e.consume();
      return;
    }

    ImageView visual_piece = (ImageView)e.getSource();
    int row = GridPane.getRowIndex(visual_piece);
    int col = GridPane.getColumnIndex(visual_piece);

    cells[col][row].markCell(Mark.SELECTED);
    highlightMoveCells(cells[col][row].getPiece(), false);
  }


/* === ON PIECE MOUSE EXIT EVENT HANDLER ======================================================== */
  private void OnPieceMouseExited(MouseEvent e) {
    // piece selected, no further highlight needed
    if (nowSelected != null) {
      e.consume();
      return;
    }
    
    ImageView visual_piece = (ImageView)e.getSource();
    int row = GridPane.getRowIndex(visual_piece);
    int col = GridPane.getColumnIndex(visual_piece);

    cells[col][row].markCell(Mark.CLEAR);
    highlightMoveCells(cells[col][row].getPiece(), true);
  }


/* === ON CELL MOUSE DOWN EVENT HANDLER ========================================================= */
  private void OnCellMouseDown(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) {
      e.consume();
      return;
    }

    Pane clicked = (Pane)e.getSource();
    int col = GridPane.getColumnIndex(clicked);
    int row = GridPane.getRowIndex(clicked);

    Move foundMove = null;
    for (Move move : nowSelected.getMoves()) {
      if (move.col_dest == col && move.row_dest == row) {
        foundMove = move;
        break;
      }
    }

    if (foundMove == null) {
      e.consume();
      return;
    }

    cells[nowSelected.col][nowSelected.row].markCell(Mark.CLEAR);
    highlightMoveCells(cells[nowSelected.col][nowSelected.row].getPiece(), true);
    
    try {
      foundMove.execute(this, game.boardCtrl, visual_pieces);
    } catch (Exception exc) {
      System.out.printf("OnCellMouseDown (Cells[%d][%d]) exception {\n%s}\n", 
          col, row, exc.getMessage());
    }

    game.setNextPlayer();    

    nowSelected = null;
  }

  public void setPieces() {
    // Set pawns
    for (byte col = 0, pbi = 8, pwi = 16; col <= 7; col++) {
      pieces[pbi] = new Pawn(pbi++, Colour.BLACKS, col, (byte)1);
      pieces[pwi] = new Pawn(pwi++, Colour.WHITES, col, (byte)6);
    }

    // Rest of pieces
    // pieces[0]  = new Rook(Colour.BLACKS, (byte)0, (byte)0);
    // pieces[1]  = new Knight(Colour.BLACKS, (byte)1, (byte)0);
    // pieces[2]  = new Bishop(Colour.BLACKS, (byte)2, (byte)0);
    // pieces[3]  = new Queen(Colour.BLACKS, (byte)3, (byte)0);
    // pieces[4]  = new King(Colour.BLACKS, (byte)4, (byte)0);
    // pieces[5]  = new Bishop(Colour.BLACKS, (byte)5, (byte)0);
    // pieces[6]  = new Knight(Colour.BLACKS, (byte)6, (byte)0);
    // pieces[7]  = new Rook(Colour.BLACKS, (byte)7, (byte)1);
    // pieces[24] = new Rook(Colour.WHITES, (byte)0, (byte)7);
    // pieces[25] = new Knight(Colour.WHITES, (byte)1, (byte)7);
    // pieces[26] = new Bishop(Colour.WHITES, (byte)2, (byte)7);
    // pieces[27] = new Queen(Colour.WHITES, (byte)3, (byte)7);
    // pieces[28] = new King(Colour.WHITES, (byte)4, (byte)7);
    // pieces[29] = new Bishop(Colour.WHITES, (byte)5, (byte)7);
    // pieces[30] = new Knight(Colour.WHITES, (byte)6, (byte)7);
    // pieces[31] = new Rook(Colour.WHITES, (byte)7, (byte)7);

    int idx = 0;
    boolean dark = false;
    for (int row = 0; row <= 7; row++) {      
      for (int col = 0; col <= 7; col++) {
        if (row < 2 || row > 5) {
          if (col == 3 && row == 4) {
            col = 3;
          }

          cells[col][row] = new Cell(pieces[idx], visual_cells[col][row], dark);
          visual_pieces[idx].setOnMousePressed(this::OnPieceMouseDown);
          visual_pieces[idx].setOnMouseEntered(this::OnPieceMouseEntered);
          visual_pieces[idx++].setOnMouseExited(this::OnPieceMouseExited);

        } else {
          cells[col][row] = new Cell(null, visual_cells[col][row], dark);
        }
        
        dark = !dark;
      }

      dark = !dark;
    }

    recalcMoves();
  }

  public void recalcMoves() {
    for (Piece piece : pieces) {
      if (piece == null) continue;
      piece.calcAvalableCells(this);
    }
  }

  public void resetBoard() {
    byte idx = 0;
    for (byte row = 0; row < 8; row++) {
      if (row == 2) row = 6;
      
      for (byte col = 0; col < 8; col++, idx++) {
        cells[col][row].resetEnPassant();
        cells[col][row].setPiece(pieces[idx]);

        if (pieces[idx] == null) continue;

        pieces[idx].setCol(col);
        pieces[idx].setRow(row);
      }
    }
  }

  public Board(Game game, MainBoardCtrl boardCtrl) {
    this.game = game;
    
    cells = new Cell[8][8];
    pieces = new Piece[32];
    visual_pieces = boardCtrl.getPieceImages();    
    visual_cells = boardCtrl.getCellPanes();  
    
    setPieces();

    for (int col = 0; col < 8; col++) {
      for (int row = 0; row < 8; row++) {
        visual_cells[col][row].setOnMousePressed(this::OnCellMouseDown);
      }
    }
  }

  public void switchTransparency(Colour colour) {
    for (int i = 0; i < pieces.length; i++) {
      // temporary (remove after all pieces complete)
      if (pieces[i] == null) {
        visual_pieces[i].setMouseTransparent(true);
      } else {
        visual_pieces[i].setMouseTransparent(!pieces[i].colour.equals(colour));
      }
    }
  }

  public void setCellsTransparency(boolean transparent) {
    for (int col = 0; col < 8; col++) {
      for (int row = 0; row < 8; row++) {
        visual_cells[col][row].setMouseTransparent(transparent);
      }
    }
  }

  public void setAllPiecesTransparent() {
    for (ImageView visual_piece: visual_pieces) {
      visual_piece.setMouseTransparent(true);
    }
  }

  public Game getGame() { return game; }
  public Cell[][] getCells() { return cells; }
  public Piece[] getPieces() { return pieces; }

  public void setNowSelected(Piece nowSelected) { this.nowSelected = nowSelected; }
  public Piece getNowSelected() { return nowSelected; }
}
