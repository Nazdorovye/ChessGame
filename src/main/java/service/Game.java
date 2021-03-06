package service;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Board;

public class Game {
  public enum GameState {
    MENU,
    HOTSEAT
  }

  public enum TurnState {
    SELECT_PIECE,
    SELECT_MOVE;

    public boolean boardTransparent() { return this.equals(SELECT_PIECE) ? true : false; }
  }

  public final Main main;
  public final MainBoardCtrl boardCtrl;
  public final PawnTransMenuCtrl transCtrl;
  private Board gameBoard;
  private Node transMenu;
  private GameState gameState;
  private TurnState turnState;
  private Colour playerTurn;
  private double mouseX, mouseY;
  private boolean mate;

  public void setNextPlayer() {
    playerTurn = Colour.values()[1 - playerTurn.ordinal()];
    gameBoard.switchTransparency(playerTurn);
    
    setTurnState(TurnState.SELECT_PIECE);
    gameBoard.recalcMoves();
  }

  public void setMouseCoords(double mouseX, double mouseY) {
    this.mouseX = mouseX;
    this.mouseY = mouseY;
  }
  public double getMouseX() { return mouseX; }
  public double getMouseY() { return mouseY; }
  
  public void reset() {
    setTurnState(TurnState.SELECT_PIECE);
    playerTurn = Colour.WHITES;
    gameState = GameState.MENU;
    gameBoard.setAllPiecesTransparent();
    boardCtrl.setAllPiecesOnTaken();
    gameBoard.setCellsTransparency(true);
  }

  public void start(GameState gameState) {
    mate = false;
    this.gameState = gameState;
    playerTurn = Colour.WHITES;

    switch (this.gameState) {
      case HOTSEAT:        
        setTurnState(TurnState.SELECT_PIECE);
        boardCtrl.setAllPiecesOnBoard();
        gameBoard.resetBoard();
        gameBoard.switchTransparency(playerTurn);

        break;
      default:
    }
  }

  public void mate() {
    main.swapMenuPanel(null);
    reset();
  }

  public void showChooseMenu(boolean white, EventHandler<? super MouseEvent> handler) {
    boardCtrl.getChooserPane().getChildren().clear();
    boardCtrl.getChooserPane().getChildren().add(transMenu);

    GridPane tmen = transCtrl.getGridPane();
    ImageView rk, bi, kn, qn;

    if (white) {
      rk = new ImageView(getClass().getResource("Chess_rlt60.png").toString());
      bi = new ImageView(getClass().getResource("Chess_blt60.png").toString());
      kn = new ImageView(getClass().getResource("Chess_nlt60.png").toString());
      qn = new ImageView(getClass().getResource("Chess_qlt60.png").toString());
    } else {
      rk = new ImageView(getClass().getResource("Chess_rdt60.png").toString());
      bi = new ImageView(getClass().getResource("Chess_bdt60.png").toString());
      kn = new ImageView(getClass().getResource("Chess_ndt60.png").toString());
      qn = new ImageView(getClass().getResource("Chess_qdt60.png").toString());
    }

    rk.setOnMousePressed(handler);
    bi.setOnMousePressed(handler);
    kn.setOnMousePressed(handler);
    qn.setOnMousePressed(handler);

    tmen.add(rk, 0, 0);
    tmen.add(bi, 1, 0);
    tmen.add(kn, 0, 1);
    tmen.add(qn, 1, 1);

    double Y = mouseY;
    if (Y < 10) {
      Y = 10;
    }

    if (Y > boardCtrl.getAspectPane().getHeight() - boardCtrl.getChooserPane().getHeight() - 10) {
      Y = boardCtrl.getAspectPane().getHeight() - boardCtrl.getChooserPane().getHeight() - 10;
    }

    boardCtrl.getChooserPane().relocate(mouseX, Y);
  }

  public void hideChooseMenu() {
    boardCtrl.getChooserPane().getChildren().clear();
  }

  public Game(MainBoardCtrl boardCtrl, PawnTransMenuCtrl transCtrl, Node transMenu, Main main) {
    this.main = main;
    this.boardCtrl = boardCtrl;
    this.transCtrl = transCtrl;
    this.transMenu = transMenu;
    this.playerTurn = Colour.WHITES;
    this.mate = false;
    gameBoard = new Board(this, boardCtrl);
    reset();
  }

  public Board getGameBoard() { return gameBoard; }

  public void setMate(boolean mate) { this.mate = mate; }
  public boolean getMate() { return mate; }

  public Colour getNextPlayer() { return playerTurn; }
  public GridPane getGridPane() { return boardCtrl.getGridPane(); }

  public void setTurnState(TurnState turnState) { 
    this.turnState = turnState;
    gameBoard.setCellsTransparency(turnState.boardTransparent());
  }
  public TurnState getTurnState() { return turnState; }

  public void setGameState(GameState gameState) { this.gameState = gameState; }
  public GameState getGameState() { return gameState; }
}
