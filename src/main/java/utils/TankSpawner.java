package main.java.utils;

import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Tank spawner.
 */
public class TankSpawner {
    private static final int ANIMATION_INTERVAL = 100;
    private final Map<BaseTank, Integer> spawnAnimationSteps;
    private final Map<BaseTank, Timer> spawnAnimationTimers;
    private java.util.List<Image> spawnImages = new ArrayList<>();

    /**
     * Instantiates a new Tank spawner.
     *
     * @param spawnImages the spawn images
     */
    public TankSpawner(java.util.List<Image> spawnImages) {
        spawnAnimationSteps = new HashMap<>();
        spawnAnimationTimers = new HashMap<>();
        this.spawnImages = spawnImages;
    }

    /**
     * Start spawn animation.
     *
     * @param tank the tank
     */
    public void startSpawnAnimation(BaseTank tank) {
        tank.setMovable(false);
        tank.setShootable(false);
        spawnAnimationSteps.putIfAbsent(tank, 0);

        Timer spawnAnimationTimer = new Timer(ANIMATION_INTERVAL, e -> {
            int step = spawnAnimationSteps.getOrDefault(tank, 0);
            spawnAnimationSteps.put(tank, step + 1);

            if (step >= spawnImages.size() - 1) {
                ((Timer) e.getSource()).stop();
                spawnAnimationSteps.remove(tank);
                tank.setMovable(true);
                tank.setShootable(true);
            }
        });

        spawnAnimationTimer.start();
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

            Image spawnImage = spawnImages.get(step);
            g.drawImage(spawnImage, tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
        } else {
            g.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
        }
    }
}
