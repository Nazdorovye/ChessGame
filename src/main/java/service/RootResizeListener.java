package service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class RootResizeListener implements ChangeListener<Number> {
  public final Scene scene;
  public final Pane content;
  public final double aRatio;

  @Override 
  public void changed(ObservableValue<? extends Number> value, Number oldVal, Number newVal) {
    final double w = scene.getWidth();
    final double h = scene.getHeight();

    if (w < h * aRatio) {
      content.setPrefWidth(w);
      content.setPrefHeight(w / aRatio);
    } else {
      content.setPrefWidth(h * aRatio);
      content.setPrefHeight(h);
    }

    content.setLayoutX((w - content.getPrefWidth()) / 2);
    content.setLayoutY((h - content.getPrefHeight()) / 2);
  }

  public RootResizeListener(Scene scene, Pane content, double aRatio) {
    this.scene = scene;
    this.content = content;
    this.aRatio = aRatio;
  }
}
