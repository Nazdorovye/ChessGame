package service;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class RootResizeListener implements ChangeListener<Number> {
  public final Pane scene;
  public final Pane aspect;

  @Override 
  public void changed(ObservableValue<? extends Number> value, Number oldVal, Number newVal) {
    final double w = scene.getWidth();
    final double h = scene.getHeight();

    if (w > h * 0.5625) {
      aspect.setPrefHeight(h);
      aspect.setPrefWidth(h * 1.77777777778);
    } else {
      aspect.setPrefWidth(w);
      aspect.setPrefHeight(w * 0.5625);
    }
  }

  public RootResizeListener(Pane scene, Pane aspect) {
    this.scene = scene;
    this.aspect = aspect;
  }
}
