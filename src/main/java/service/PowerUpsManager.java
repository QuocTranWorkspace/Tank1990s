package main.java.service;

import main.java.App;
import main.java.model.PlayerTank;
import main.java.model.Point2D;
import main.java.model.powerups.PowerUps;
import main.java.model.powerups.Type;
import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class PowerUpsManager {
    private static final int MAX_POWER_UPS = 3;
    private static final int POWER_UP_EXPIRATION = 10000; // in milliseconds
    private static final int POWER_UP_SPAWN_INTERVAL = 5000; // in milliseconds

    private final List<PowerUps> activePowerUps;
    private final Random random;

    public PowerUpsManager() {
        activePowerUps = new ArrayList<>();
        random = new Random();

        Timer powerUpSpawnTimer = new Timer(POWER_UP_SPAWN_INTERVAL, e -> {
            if (activePowerUps.size() < MAX_POWER_UPS) {
                spawnPowerUp();
            }
        });
        powerUpSpawnTimer.start();
    }

    public void spawnPowerUp() {
        Point2D position = new Point2D(Math.max(0, Math.min((int) (25 * App.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * App.FRAME_HEIGHT / 27.9)))), Math.max(0, Math.min((int) (25 * App.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * App.FRAME_HEIGHT / 27.9)))));
        Type powerUpType = Type.getRandomType();
        PowerUps newPowerUp = new PowerUps(position.getX(), position.getY(), powerUpType);
        activePowerUps.add(newPowerUp);

        Timer expirationTimer = new Timer(POWER_UP_EXPIRATION, e -> {
            activePowerUps.remove(newPowerUp);
            ((Timer) e.getSource()).stop();
        });
        expirationTimer.setRepeats(false); // Only expires once
        expirationTimer.start();
    }

    public void collectPowerUp(PlayerTank tank, boolean isCollide) {
        Iterator<PowerUps> iterator = activePowerUps.iterator();
        while (iterator.hasNext()) {
            PowerUps powerUp = iterator.next();
            if (isCollide) {
                tank.addPoints(500);
                powerUp.activate(tank);
                iterator.remove();
            }
        }
    }

    public List<PowerUps> getActivePowerUps() {
        return activePowerUps;
    }
}
