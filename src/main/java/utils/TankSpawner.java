package main.java.utils;

import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;

public class TankSpawner {
    private int spawnAnimationStep = 0;
    private Timer spawnDelayTimer;
    private Timer spawnAnimationTimer;
    private boolean isSpawning = false;

    public TankSpawner() {
        spawnDelayTimer = new Timer(1000, e -> {
            isSpawning = true;
            spawnAnimationStep = 0;
            spawnAnimationTimer.start();
            spawnDelayTimer.stop();
        });

        spawnAnimationTimer = new Timer(50, e -> {
            spawnAnimationStep++;

            if (spawnAnimationStep >= 20) {
                spawnAnimationTimer.stop();
                isSpawning = false;
            }
        });

        spawnDelayTimer.start();
    }

    public void drawTank(Graphics g, BaseTank tank) {
        if (isSpawning) {
            Graphics2D g2d = (Graphics2D) g.create();
            float alpha = (float) spawnAnimationStep / 20;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
            g2d.dispose();
        } else {
            g.drawImage(tank.getImage(), tank.getPosition().getX(), tank.getPosition().getY(), tank.getWidth(), tank.getHeight(), null);
        }
    }
}
