package main.java.utils;

import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TankSpawner {
    private static final int SPAWN_DELAY = 50;
    private static final int ANIMATION_INTERVAL = 100;
    private java.util.List<Image> spawnImages = new ArrayList<>();

    private final Map<BaseTank, Integer> spawnAnimationSteps;
    private final Map<BaseTank, Timer> spawnAnimationTimers;

    public TankSpawner(java.util.List<Image> spawnImages) {
        spawnAnimationSteps = new HashMap<>();
        spawnAnimationTimers = new HashMap<>();
        this.spawnImages = spawnImages;
    }

    public void startSpawnAnimation(BaseTank tank) {
        spawnAnimationSteps.putIfAbsent(tank, 0);

        Timer spawnAnimationTimer = new Timer(ANIMATION_INTERVAL, e -> {
            if (spawnAnimationSteps.containsKey(tank)) {
                int step = spawnAnimationSteps.get(tank);

                spawnAnimationSteps.put(tank, step + 1);

                if (step >= spawnImages.size() - 1) {
                    ((Timer) e.getSource()).stop();
                    spawnAnimationSteps.remove(tank);
                }
            } else {
                ((Timer) e.getSource()).stop();
            }
        });

        Timer spawnDelayTimer = new Timer(SPAWN_DELAY, e -> {
            spawnAnimationTimer.start();
            ((Timer) e.getSource()).stop();
        });

        spawnDelayTimer.start();

        spawnAnimationTimers.put(tank, spawnAnimationTimer);
    }



    public void drawTank(Graphics g, BaseTank tank) {
        if (spawnAnimationSteps.containsKey(tank)) {
            int step = spawnAnimationSteps.get(tank);

            Image spawnImage = spawnImages.get(step);
            g.drawImage(spawnImage, tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
            tank.setMovable(false);
            tank.setShootable(false);
        } else {
            tank.setMovable(true);
            tank.setShootable(true);
            g.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
        }
    }

}
