package com.twenties.twenties;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BreakTimer extends Timer {
    Timeline countdown;
    Label label;
    IntegerProperty timeSeconds;
    Button btn;
    Stage stage;
    Duration dur;

    public BreakTimer(Integer start, Duration dur, Timeline tl, Button btn, Stage stage) {
        super(start, dur, tl);
        this.timeSeconds = new SimpleIntegerProperty(start);
        this.btn = btn;
        this.stage = stage;
        this.dur = dur;
        label = new Label(timeSeconds.toString());
    }

    // EFFECTS: initiates break timer
    public Timeline getTimeline() {
        countdown = new Timeline();
        countdown.getKeyFrames().add(new KeyFrame(dur.seconds(1), event1 -> {
            this.getTimeSeconds().set(this.getTimeSeconds().get() - 1);
        }));
        countdown.getKeyFrames().add(
                new KeyFrame(dur.seconds(this.getStartTime() + 1),
                        new KeyValue(this.getTimeSeconds(), 0)
                ));
        countdown.setOnFinished(actionEvent -> {
            this.stage.close();
            this.btn.fire();
        });
        return countdown;
    }

    public Label getLabel() {
        return label;
    }

    public IntegerProperty getTimeSeconds() {
        return timeSeconds;
    }

    public StringBinding getFormattedTime() {
        timeSeconds = this.getTimeSeconds();
        StringBinding timeString = Bindings.selectString(timeSeconds);
        return timeString;
    }
}
