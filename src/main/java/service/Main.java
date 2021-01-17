package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  private static MainBoardCtrl boardCtrl;

  private Parent loadFXML(String filename) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(filename + ".fxml"));

    try {
      Parent result = loader.load();
      boardCtrl = (MainBoardCtrl)loader.getController();      
      return result;
    } catch(Exception e) {
      System.out.print(e.getMessage());
      return null;
    }
  }

  @Override public void start(Stage stage) {
    Scene scene = new Scene(loadFXML("mainboard") , 1280, 720);

    if (scene.getRoot() == null) {
      System.exit(0);
    }

    stage.setScene(scene);
    stage.minHeightProperty().set(480.);
    stage.minWidthProperty().set(854.);

    RootResizeListener listener = new RootResizeListener(scene, boardCtrl.getAspectPane(), 16. / 9);
    scene.widthProperty().addListener(listener);
    scene.heightProperty().addListener(listener);

    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
