package com.twenties.twenties;

import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public abstract class Timer {
    Integer start;
    Timeline tl;
    Duration dur;
    Label label;
    IntegerProperty timeSeconds;


    public Timer(Integer start, Duration dur, Timeline tl) {
        this.start = start;
        this.tl = tl;
        this.dur = dur;
        this.timeSeconds = new SimpleIntegerProperty(start);
        label = new Label();
    }

    public Integer getStartTime() {
        return start;
    }
    public IntegerProperty getTimeSeconds() {
        return timeSeconds;
    }

    public Timeline getTimeline() {
        return tl;
    }

    public Duration getDuration() {
        return dur;
    }

    public Label getLabel() {
        return label;
    }

    public void styleLabel() {
        label.setTextFill(Color.RED);
        label.setStyle("-fx-font-size: 7em;");
    }

    public StringBinding getFormattedTime() {
        timeSeconds = getTimeSeconds();
        StringBinding timeString = Bindings.createStringBinding(() -> {
            int minutes = timeSeconds.get() / 60;
            int seconds = timeSeconds.get() % 60;
            return String.format("%d:%02d", minutes, seconds);
        }, timeSeconds);
        return timeString;
    }
}