package main.java.service;

import javax.swing.*;

public class TimerManager {
    private static Timer timer;

    public static Timer getSharedTimer() {
        if (timer == null) {
            timer = new Timer(1000/120, e -> {});
        }
        return timer;
    }
}
