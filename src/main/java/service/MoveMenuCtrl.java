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

    @FXML Pane mainPane;
    @FXML Pane moveMenuPane;
    @FXML VBox mainVbox;
    @FXML HBox colorBox;
    @FXML Label labelW;
    @FXML Label labelB;
    @FXML HBox timerBox;
    @FXML Label timerW;
    @FXML Label timerB;
    @FXML ScrollPane scrollPane;
    @FXML AnchorPane scrollApane;
    @FXML TableView moveTable;
    @FXML TableColumn columnNr;
    @FXML TableColumn tableW;
    @FXML TableColumn tableB;
    @FXML HBox buttonBox;
    @FXML Button giveUp;
    @FXML HBox spaceBox;
    @FXML Button draw;

    @FXML private void initialize(){

        AnchorPane.setBottomAnchor(mainPane, .0);
        AnchorPane.setTopAnchor(mainPane, .0);
        AnchorPane.setLeftAnchor(mainPane, .0);
        AnchorPane.setRightAnchor(mainPane, .0);

        mainVbox.prefWidthProperty().bind(mainPane.widthProperty());
        mainVbox.prefHeightProperty().bind(mainPane.heightProperty());

        moveTable.prefWidthProperty().bind(timerBox.widthProperty());
        moveTable.prefHeightProperty().bind(mainPane.heightProperty().multiply(0.6));

        tableW.prefWidthProperty().bind(moveTable.widthProperty().divide(2));
        tableB.prefWidthProperty().bind(moveTable.widthProperty().divide(2));
        
        

    }

    public void onClickDrawButton(EventHandler<? super MouseEvent> value) {
        draw.onMousePressedProperty().set(value);
      }

      public void onClickGiveUpButton(EventHandler<? super MouseEvent> value) {
        giveUp.onMousePressedProperty().set(value);
      }
    
}
