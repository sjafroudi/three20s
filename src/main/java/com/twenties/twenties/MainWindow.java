package com.twenties.twenties;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainWindow {
    Stage stage;
    Scene scene;
    Group group;
//    Label label;

    Button startButton;
    Button pauseButton;
    Button resumeButton;

    HBox hbox;
    VBox vbox;

    public MainWindow(Stage stage) {
        this.stage = stage;
        group = new Group();
//        stage = new Stage();
        scene = new Scene(group, 400, 250);
        this.startButton = new Button();
        this.pauseButton = new Button();
        this.resumeButton = new Button();
    }

    public Button getStartButton() {
        return startButton;
    }
    public Button getPauseButton() {
        return pauseButton;
    }
    public Button getResumeButton() {
        return resumeButton;
    }

    public Scene getScene() {
        return scene;
    }
    public Group getGroup() {
        return group;
    }

    public void setButtonLabels() {

        startButton.setText("Start Timer");
        pauseButton.setText("Pause");
        resumeButton.setText("Resume");
    }

    public void setHbox() {
        hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(startButton,pauseButton,resumeButton);
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setVbox() {
        vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefWidth(scene.getWidth());

    }
    public VBox getVbox() {
        return vbox;
    }

    public Stage getStage() {
        stage.setTitle("20/20/20");
        setHbox();
        setVbox();
//        group.getChildren().add(vbox);
        stage.setScene(scene);
        return stage;
    }





}
