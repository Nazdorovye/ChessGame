package service;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import models.Board;

public class Game {
  public enum GameState {
    MENU,
    HOTSEAT
  }

  public enum TurnState {
    SELECT_PIECE,
    SELECT_MOVE
  }

  public final MainBoardCtrl boardCtrl;
  private Board gameBoard;
  private GameState gameState;
  private TurnState turnState;
  private Colour playerTurn;

  public void setNextPlayer() {
    gameBoard.switchTransparency(playerTurn);
    playerTurn = Colour.values()[1 - playerTurn.ordinal()];
    turnState = TurnState.SELECT_PIECE;
  }
  
  public void reset() {
    gameState = GameState.MENU;
    gameBoard.setAllPiecesTransparent();
    boardCtrl.setAllPiecesOnTaken();
  }

  public void start(GameState gameState) {
    this.gameState = gameState;
    playerTurn = Colour.WHITES;

    switch (this.gameState) {
      case HOTSEAT:        
        turnState = TurnState.SELECT_PIECE;
        boardCtrl.setAllPiecesOnBoard();
        gameBoard.switchTransparency(playerTurn);

        break;
      default:
    }   

  }

  public Game(MainBoardCtrl boardCtrl) {
    this.boardCtrl = boardCtrl;
    gameBoard = new Board(this, boardCtrl);
    reset();
  }

  public Board getGameBoard() { return gameBoard; }

  public Colour getNextPlayer() { return playerTurn; }
  public GridPane getGridPane() { return boardCtrl.getGridPane(); }

  public void setTurnState(TurnState turnState) { this.turnState = turnState; }
  public TurnState getTurnState() { return turnState; }

  public void setGameState(GameState gameState) { this.gameState = gameState; }
  public GameState getGameState() { return gameState; }
}
