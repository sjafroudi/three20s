package com.twenties.twenties;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;

public class HelloApplication extends Application {
    MainTimer mainTimer;
    MainWindow mainWindow;
    Label mainLabel;
    Duration mainDuration;
    Timeline mainTimeline;

    @Override
    public void start(Stage stage) {
        Integer startTime = 3;
        mainDuration = new Duration(0);
        mainTimeline = new Timeline();
        mainTimer = new MainTimer(startTime, mainDuration, mainTimeline);
        mainLabel = mainTimer.getLabel();
        mainTimeline = mainTimer.getTimeline();
        mainWindow = new MainWindow(stage);
        mainWindow.setButtonLabels();
        Stage mainStage = mainWindow.getStage();
        mainTimer.styleLabel();
        StringBinding stringBinding = mainTimer.getFormattedTime();
        // Bind the countdown time to the label
        mainLabel.textProperty().bind(stringBinding);

        // EFFECTS: starts MainTimer
        mainWindow.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (mainTimeline != null) {
                    mainTimeline.stop();
                }
                mainWindow.getStartButton().setText("Restart Timer");
                mainTimer.getTimeSeconds().set(mainTimer.getStartTime());
                // Establish the course of the timeline
                mainTimeline.getKeyFrames().add(
                        new KeyFrame(mainDuration.seconds(mainTimer.getStartTime() + 1),
                                new KeyValue(mainTimer.getTimeSeconds(), 0)
                        ));
                // Bind the time in MIN:SEC format to the timer label
                mainTimeline.getKeyFrames().add(new KeyFrame(mainDuration.seconds(1), event1 -> {
                    mainTimer.getTimeSeconds().set(mainTimer.getTimeSeconds().get() - 1);
                }));
                mainTimeline.playFromStart();
                // Close break notification window once it's timer is complete
                mainTimeline.setOnFinished(event1 -> {
                    BreakNotification bn = new BreakNotification(mainWindow.getStartButton());
                    bn.setScreenCoordinates();
                });
            }});

        // EFFECTS: pauses timeline in main window
        mainWindow.getPauseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainTimeline.pause();
            }
        });

        // EFFECTS: resumes timeline in main window
        mainWindow.getResumeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainTimeline.play();
            }
        });

        VBox vbox = mainWindow.getVbox();
        HBox hbox = mainWindow.getHbox();
        Group group = mainWindow.getGroup();
        Scene scene = mainWindow.getScene();

        vbox.getChildren().addAll(mainLabel);
        vbox.getChildren().add(hbox);
        group.getChildren().add(vbox);
        stage.setScene(scene);
        mainStage.show();
    }

    // Close and reopen the application if an error occurs
    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            Platform.exit();
            launch();
        }
    }
}