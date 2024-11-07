package main.java.service;

import main.java.App;
import main.java.model.PlayerTank;
import main.java.model.Point2D;
import main.java.model.powerups.PowerUps;
import main.java.model.powerups.Shovel;
import main.java.model.powerups.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PowerUpsManager {
    private static final int MAX_POWER_UPS = 3;
    private static final int POWER_UP_EXPIRATION = 12000;
    private static final int POWER_UP_SPAWN_INTERVAL = 5000;

    private final List<PowerUps> activePowerUps;
    private final Random random;

    Timer powerUpSpawnTimer;

    private final TankManager tankManager;

    public PowerUpsManager(TankManager tankManager) {
        activePowerUps = new ArrayList<>();
        random = new Random();
        this.tankManager = tankManager;

        powerUpSpawnTimer = new Timer(POWER_UP_SPAWN_INTERVAL, e -> {
            if (activePowerUps.size() < MAX_POWER_UPS) {
                spawnPowerUp();
            }
        });
        powerUpSpawnTimer.start();
    }

    public void spawnPowerUp() {
        Point2D position = new Point2D(Math.max(0, Math.min((int) (25 * App.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * App.FRAME_HEIGHT / 27.9)))), Math.max(0, Math.min((int) (25 * App.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * App.FRAME_HEIGHT / 27.9)))));
        Type powerUpType = Type.getRandomType();
        PowerUps newPowerUp = new PowerUps(position.getX(), position.getY(), powerUpType, tankManager);
        activePowerUps.add(newPowerUp);

        Timer expirationTimer = new Timer(POWER_UP_EXPIRATION, e -> {
            activePowerUps.remove(newPowerUp);
            ((Timer) e.getSource()).stop();
        });
        expirationTimer.setRepeats(false);
        expirationTimer.start();
    }

    public void collectPowerUp(PlayerTank tank, PowerUps powerUp) {
        GameplayManager.score += 500;
        tank.addPoints(500);
        powerUp.activate(tank);
        activePowerUps.remove(powerUp);
    }

    public void stopAllTimer() {
        if (powerUpSpawnTimer.isRunning()) {
            powerUpSpawnTimer.stop();
        }
        Shovel.getInstance().deactivate();
    }

    public List<PowerUps> getActivePowerUps() {
        return activePowerUps;
    }
}
