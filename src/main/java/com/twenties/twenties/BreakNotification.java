package com.twenties.twenties;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class BreakNotification extends Stage {
    Stage breakStage;
    StackPane stackPane;
    Timeline countdown;
    Label countdownLabel = new Label("2");


    public BreakNotification(Button btn) {
        stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 120, 100);
        breakStage = new Stage();
        breakStage.setTitle("BreakTime");
        breakStage.initStyle(StageStyle.UTILITY);
        breakStage.setResizable(true);

        Duration dur = new Duration(0);
        Timeline tl = new Timeline();
        BreakTimer breakTimer = new BreakTimer(2,dur,tl);

        Label label = breakTimer.getLabel();
        breakTimer.styleLabel();
        StringBinding stringBinding = breakTimer.getFormattedTime();
        label.textProperty().bind(stringBinding);

        this.countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    int count = Integer.parseInt(countdownLabel.getText());
                    if (count > 0) {
                        countdownLabel.setText(String.valueOf(count - 1));
                        if (count == 1) {
                            btn.fire();
                        }
                    } else {
                        breakStage.close();
                    }
                })
        );

        countdown.setCycleCount(Animation.INDEFINITE);
        countdown.play();
        stackPane.getChildren().add(countdownLabel);
        breakStage.setScene(scene);
        breakStage.show();
    }

    public void setScreenCoordinates() {
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();
        double stageWidth = breakStage.getWidth();
        double stageHeight = breakStage.getHeight();
        this.setX(screenWidth - stageWidth);
        this.setY(0);
    }
}