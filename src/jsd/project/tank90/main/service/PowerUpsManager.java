package jsd.project.tank90.main.service;

import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.model.PlayerTank;
import jsd.project.tank90.main.model.Point2D;
import jsd.project.tank90.main.model.powerups.PowerUps;
import jsd.project.tank90.main.model.powerups.Shovel;
import jsd.project.tank90.main.model.powerups.Type;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Power ups manager.
 */
public class PowerUpsManager {
    private static final int MAX_POWER_UPS = 3;
    private static final int POWER_UP_EXPIRATION = 12000;
    private static final int POWER_UP_SPAWN_INTERVAL = 5000;

    private final List<PowerUps> activePowerUps;
    private final Random random;
    private final TankManager tankManager;
    /**
     * The Power up spawn timer.
     */
    Timer powerUpSpawnTimer;

    /**
     * Instantiates a new Power ups manager.
     *
     * @param tankManager the tank manager
     */
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

    /**
     * Spawn power up.
     */
    public void spawnPowerUp() {
        Point2D position = new Point2D((int) Math.max(Main.FRAME_HEIGHT / 27.9, Math.min((int) (25 * Main.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * Main.FRAME_HEIGHT / 27.9)))), (int) Math.max(Main.FRAME_HEIGHT / 27.9, Math.min((int) (25 * Main.FRAME_HEIGHT / 27.9), random.nextInt((int) (25 * Main.FRAME_HEIGHT / 27.9)))));
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

    /**
     * Collect power up.
     *
     * @param tank    the tank
     * @param powerUp the power up
     */
    public void collectPowerUp(PlayerTank tank, PowerUps powerUp) {
        GameplayManager.score += 500;
        tank.addPoints(500);
        powerUp.activate(tank);
        activePowerUps.remove(powerUp);
    }

    /**
     * Stop all timer.
     */
    public void stopAllTimer() {
        if (powerUpSpawnTimer.isRunning()) {
            powerUpSpawnTimer.stop();
        }
        Shovel.getInstance().deactivate();
    }

    /**
     * Gets active power ups.
     *
     * @return the active power ups
     */
    public List<PowerUps> getActivePowerUps() {
        return activePowerUps;
    }
}
