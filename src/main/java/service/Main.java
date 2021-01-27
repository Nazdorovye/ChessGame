package service;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Game.GameState;

public class Main extends Application {
  private static MainBoardCtrl boardCtrl;
  private static MainMenuCtrl mainMenuCtrl;
  private static MoveMenuCtrl moveMenuCtrl;
  private static PawnTransMenuCtrl transMenuCtrl;
  private Scene scene;
  private Node mainMenu;
  private Node gameMenu;
  private Node transMenu;
  private Game game;

  public void startHotSeat(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) return;

    // generate confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Start new hotseat match?", 
        ButtonType.YES, ButtonType.NO); 
    
    // show and wait for response (X==NO)
    ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

    if (ButtonType.NO.equals(result)) return;

    swapMenuPanel(gameMenu);
    game.start(GameState.HOTSEAT);
  }

  public void gameGiveUpClick(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) return;  

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, String.format("%s: Already giving up?", 
        game.getNextPlayer().toString()), 
        ButtonType.YES, ButtonType.NO);
    
    ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
    if (ButtonType.NO.equals(result)) return;

    alert = new Alert(Alert.AlertType.INFORMATION, String.format("%s WON!", 
        game.getNextPlayer().toStringRev()), 
        ButtonType.OK);

    alert.showAndWait();

    swapMenuPanel(mainMenu);
    game.reset();
  }

  public void gameDrawClick(MouseEvent e) {
    if (!e.isPrimaryButtonDown()) return;

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wish to draw?", 
        ButtonType.YES, ButtonType.NO);
    
    ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
    if (ButtonType.NO.equals(result)) return;

    swapMenuPanel(mainMenu);
    game.reset();
  }


  /** Removes previous side panel and adds selected one */
  public void swapMenuPanel(Node panel) {    
    if (panel == null) panel = mainMenu;

    if (game.getMate()) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("%s WON!", 
      game.getNextPlayer().toStringRev()), 
      ButtonType.OK);
      
      alert.showAndWait();
      game.setMate(false);
    }

    boardCtrl.getUtilPane().getChildren().clear();
    boardCtrl.getUtilPane().getChildren().add(panel);
  }

  private boolean loadFXMLs() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("mainboard.fxml"));

      scene = new Scene(loader.load(), 1280, 720);
      boardCtrl = (MainBoardCtrl)loader.getController();
      boardCtrl.initRootResizer(scene, 16.0 / 9.0);

      loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));

      mainMenu = loader.load();
      mainMenuCtrl = (MainMenuCtrl)loader.getController();
      mainMenuCtrl.onStartHotSeatPressed(this::startHotSeat);

      loader = new FXMLLoader(getClass().getResource("movemenu.fxml"));

      gameMenu = loader.load();
      moveMenuCtrl = (MoveMenuCtrl)loader.getController();
      moveMenuCtrl.onClickDrawButton(this::gameDrawClick);
      moveMenuCtrl.onClickGiveUpButton(this::gameGiveUpClick);

      loader = new FXMLLoader(getClass().getResource("pawntransmenu.fxml"));
      
      transMenu = loader.load();
      transMenuCtrl = (PawnTransMenuCtrl)loader.getController();      

    } catch(Exception e) {
      System.out.print(e.getMessage());
      return false;
    }

    return true;
  }

  @Override public void start(Stage stage) {
    if (!loadFXMLs()) {
      System.exit(0);
    }

    stage.setScene(scene);
    stage.minWidthProperty().set(1280.);
    stage.minHeightProperty().set(720.);
    stage.show();

    // for chooser pane coordinates
    boardCtrl.getAspectPane().setOnMouseMoved(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent arg0) {
        if(arg0.getEventType() == MouseEvent.MOUSE_MOVED){
          game.setMouseCoords(arg0.getSceneX(), arg0.getSceneY());
        }  
      }

    });

    game = new Game(boardCtrl, transMenuCtrl, transMenu, this);
    swapMenuPanel(mainMenu); // set side panel to main menu
  }

  // entry point
  public static void main(String[] args) {
    launch();
  }
}
