module ChessGame {
  requires transitive javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;

  opens service to javafx.fxml;
  exports models;
  exports service;
}
