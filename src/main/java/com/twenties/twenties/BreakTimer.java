package com.twenties.twenties;

import javafx.animation.Timeline;
import javafx.util.Duration;

public class BreakTimer extends Timer{
    public BreakTimer(Integer start, Duration dur, Timeline tl) {
        super(start, dur, tl);
    }

}
