package service;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MoveMenuCtrl {

    @FXML private Pane mainPane;
    @FXML private Pane moveMenuPane;
    @FXML private VBox mainVbox;
    @FXML private HBox colorBox;
    @FXML private HBox colorBoxBuffer;
    @FXML private Label labelW;
    @FXML private Label labelB;
    @FXML private HBox timerBox;
    @FXML private Label timerW;
    @FXML private Label timerB;
    @FXML private ScrollPane scrollPane;
    @FXML private AnchorPane scrollApane;
    @FXML private TableView moveTable;
    @FXML private TableColumn columnNr;
    @FXML private TableColumn tableW;
    @FXML private TableColumn tableB;
    @FXML private HBox buttonBox;
    @FXML private Button giveUp;
    @FXML private HBox spaceBox;
    @FXML private Button draw;

    @FXML private void initialize(){

      AnchorPane.setBottomAnchor(mainPane, .0);
      AnchorPane.setTopAnchor(mainPane, .0);
      AnchorPane.setLeftAnchor(mainPane, .0);
      AnchorPane.setRightAnchor(mainPane, .0);
      
      mainVbox.prefWidthProperty().bind(mainPane.widthProperty());
      mainVbox.prefHeightProperty().bind(mainPane.heightProperty());
      colorBox.prefHeightProperty().bind(mainVbox.heightProperty().multiply(0.1));
      timerBox.prefHeightProperty().bind(mainVbox.heightProperty().multiply(0.1));
      buttonBox.prefHeightProperty().bind(mainVbox.heightProperty().multiply(0.1));
      labelW.prefHeightProperty().bind(colorBox.heightProperty().multiply(0.8));
      labelW.prefWidthProperty().bind(colorBox.widthProperty().divide(2).multiply(0.921));
      labelB.prefHeightProperty().bind(colorBox.heightProperty().multiply(0.8));
      labelB.prefWidthProperty().bind(colorBox.widthProperty().divide(2).multiply(0.921));
      colorBoxBuffer.prefWidthProperty().bind(colorBox.widthProperty().multiply(0.078));
      
      scrollPane.prefHeightProperty().bind(mainVbox.heightProperty().multiply(0.6));
      scrollPane.prefWidthProperty().bind(mainVbox.widthProperty().subtract(20));
      scrollApane.prefHeightProperty().bind(scrollPane.heightProperty());
      scrollApane.prefWidthProperty().bind(scrollPane.widthProperty());
      moveTable.prefWidthProperty().bind(scrollApane.widthProperty().subtract(10));
      moveTable.prefHeightProperty().bind(scrollApane.heightProperty().subtract(10));

      columnNr.prefWidthProperty().bind(scrollPane.widthProperty().multiply(0.1));
      tableW.prefWidthProperty().bind(scrollPane.widthProperty().subtract(columnNr.widthProperty().divide(2).subtract(10)));
      tableB.prefWidthProperty().bind(tableW.widthProperty());   
    }

    public void onClickDrawButton(EventHandler<? super MouseEvent> value) {
      draw.onMousePressedProperty().set(value);
    }

    public void onClickGiveUpButton(EventHandler<? super MouseEvent> value) {
      giveUp.onMousePressedProperty().set(value);
    }
    
}
