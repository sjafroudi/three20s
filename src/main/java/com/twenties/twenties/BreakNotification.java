package com.twenties.twenties;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.binding.StringBinding;
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
    Label countdownLabel;
    StringBinding sb;

    public BreakNotification(Button btn) {
        stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 170, 120);
        breakStage = new Stage();
        breakStage.setTitle("BreakTime");
        breakStage.initStyle(StageStyle.UTILITY);
        breakStage.setResizable(true);

        Duration dur = new Duration(0);
        Timeline tl = new Timeline();
        BreakTimer breakTimer = new BreakTimer(3,dur,tl, btn, breakStage);


//        Label label = breakTimer.getLabel();
//        breakTimer.styleLabel();
//        StringBinding stringBinding = breakTimer.getFormattedTime();
//        label.textProperty().bind(stringBinding);
//        breakTimer.startBreakTimer();

        countdown = breakTimer.getTimeline();
        sb = breakTimer.getFormattedTime();
        countdownLabel = breakTimer.getLabel();
        countdownLabel.textProperty().bind(sb);
//        breakTimer.setLabel();
//        countdown.setCycleCount(Animation.INDEFINITE);
//        countdown.play();
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