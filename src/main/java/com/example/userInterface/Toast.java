package com.example.userInterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * Toast defines all the popup/toast messages of the game
 * */
public class Toast {

    private static final Stage stage = createStage();

    private static Stage createStage(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOpacity(0.8);
        return stage;
    }

    public static void show(String message) {
        Label label = new Label(message);
        label.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-padding: 10px;");
        StackPane root = (StackPane) stage.getScene().getRoot();
        root.getChildren().setAll(label);
        stage.show();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> stage.close()));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
