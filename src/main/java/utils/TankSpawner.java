package main.java.utils;

import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Tank spawner.
 */
public class TankSpawner {
    private static final int SPAWN_ANIMATION_STEPS = 20;
    private static final int SPAWN_DELAY = 1000; // 1-second delay before spawn animation starts
    private static final int ANIMATION_INTERVAL = 50; // Animation interval for smooth transition

    // Map to track each tank’s spawn animation progress
    private final Map<BaseTank, Integer> spawnAnimationSteps;
    private final Map<BaseTank, Timer> spawnAnimationTimers;

    /**
     * Instantiates a new Tank spawner.
     */
    public TankSpawner() {
        spawnAnimationSteps = new HashMap<>();
        spawnAnimationTimers = new HashMap<>();
    }

    /**
     * Start spawn animation.
     *
     * @param tank the tank
     */
    public void startSpawnAnimation(BaseTank tank) {
        spawnAnimationSteps.put(tank, 0);

        Timer spawnAnimationTimer = new Timer(ANIMATION_INTERVAL, e -> {
            int step = spawnAnimationSteps.get(tank);
            spawnAnimationSteps.put(tank, ++step);

            if (step >= SPAWN_ANIMATION_STEPS) {
                ((Timer) e.getSource()).stop();
                spawnAnimationSteps.remove(tank);
            }
        });

        // Delay before starting animation
        Timer spawnDelayTimer = new Timer(SPAWN_DELAY, e -> {
            spawnAnimationTimer.start();
            ((Timer) e.getSource()).stop();
        });

        spawnDelayTimer.start();
        spawnAnimationTimers.put(tank, spawnAnimationTimer);
    }

    /**
     * Draw tank.
     *
     * @param g    the g
     * @param tank the tank
     */
    public void drawTank(Graphics g, BaseTank tank) {
        if (spawnAnimationSteps.containsKey(tank)) {
            int step = spawnAnimationSteps.get(tank);
            float alpha = (float) step / SPAWN_ANIMATION_STEPS;
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
            g2d.dispose();
        } else {
            g.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
        }
    }
}
