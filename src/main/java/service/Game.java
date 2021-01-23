package service;

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

  private MainBoardCtrl boardCtrl;
  private Board gameBoard;
  private GameState gameState;
  private TurnState turnState;
  private Colour playerTurn; 

  public void setNextPlayer() {
    // next = 1 - prev (0 = 1 - 1) (1 = 1 - 0)
    playerTurn = Colour.values()[1 - playerTurn.ordinal()];
    turnState = TurnState.SELECT_PIECE;
  }
  
  public void reset() {
    gameBoard = new Board(this, boardCtrl);
    turnState = TurnState.SELECT_PIECE; 
    playerTurn = Colour.WHITES;
  }

  public Game(MainBoardCtrl boardCtrl) {
    this.boardCtrl = boardCtrl;
    gameState = GameState.MENU;
    reset();
  }

  public Colour getNextPlayer() { return playerTurn; }
  public GridPane getGridPane() { return boardCtrl.getGridPane(); }

  public void setTurnState(TurnState turnState) { this.turnState = turnState; }
  public TurnState getTurnState() { return turnState; }

  public void setGameState(GameState gameState) { this.gameState = gameState; }
  public GameState getGameState() { return gameState; }
}
