package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
  private static MainBoardCtrl boardCtrl;
  private static MainMenuCtrl mainMenuCtrl;
  private Scene scene;
  private Pane mainMenu;
  private Game game;

  private boolean loadFXMLs() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("mainboard.fxml"));
    try {
      scene = new Scene(loader.load(), 1280, 720);
      boardCtrl = (MainBoardCtrl)loader.getController();
    } catch(Exception e) {
      System.out.print(e.getMessage());
      return false;
    }

    loader = new FXMLLoader(getClass().getResource("mainmenu.fxml"));
    try {
      boardCtrl.getUtilPane().getChildren().add(loader.load());
      mainMenuCtrl = (MainMenuCtrl)loader.getController();
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

    // ------------------------------ SCENE ----------------------------------------------------- //
    stage.setScene(scene);
    stage.minWidthProperty().set(854.);
    stage.minHeightProperty().set(480.);

    RootResizeListener listener = new RootResizeListener(scene, boardCtrl.getAspectPane(), 16. / 9);
    scene.widthProperty().addListener(listener);
    scene.heightProperty().addListener(listener);


    // --------------------------- MAIN MENU ---------------------------------------------------- //

    game = new Game(boardCtrl);

    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
