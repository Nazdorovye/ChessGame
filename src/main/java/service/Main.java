package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  private static Parent loadFXML(String filename) {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(filename + ".fxml"));

    try {
      return fxmlLoader.load();
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
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
