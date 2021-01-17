package service;

import models.Board;

public class Game {
  private MainBoardCtrl boardCtrl;
  private Board gameBoard;
  private StateGame gameState;
  private boolean nextWhite;

  public void setNextPlayer() {
    nextWhite = !nextWhite;
  }
  
  public void reset() {
    gameBoard = new Board(this, boardCtrl);
    gameState = StateGame.SELECT_PIECE;
    nextWhite = true;
  }

  public Game(MainBoardCtrl boardCtrl) {
    this.boardCtrl = boardCtrl;
    reset();
  }

  public boolean getNextWhite() { return nextWhite; }
  public StateGame getGameState() { return gameState; }
}
