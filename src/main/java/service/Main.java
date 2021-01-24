package service;

import javafx.application.Application;
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
  private Scene scene;
  private Node mainMenu;
  private Node gameMenu;
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


  /** Removes previous side panel and adds selected one */
  private void swapMenuPanel(Node panel) {
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
    stage.minWidthProperty().set(854.);
    stage.minHeightProperty().set(480.);
    stage.show();

    swapMenuPanel(mainMenu); // set side panel to main menu
    game = new Game(boardCtrl);
  }

  public static void main(String[] args) {
    launch();
  }
}
