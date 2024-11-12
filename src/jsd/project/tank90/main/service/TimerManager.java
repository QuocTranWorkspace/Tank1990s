package jsd.project.tank90.main.service;

import javax.swing.*;

/**
 * The type Timer manager.
 */
public class TimerManager {
    private static Timer timer;

    /**
     * Gets shared timer.
     *
     * @return the shared timer
     */
    public static Timer getSharedTimer() {
        if (timer == null) {
            timer = new Timer(1000 / 120, e -> {
            });
        }
        return timer;
    }
}
