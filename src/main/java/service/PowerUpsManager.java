package main.java.service;

import main.java.model.PlayerTank;
import main.java.model.powerups.PowerUps;
import main.java.model.powerups.Type;
import main.java.model.tanks.BaseTank;

import java.util.Random;

/**
 * The type Power ups manager.
 */
public class PowerUpsManager {
    private final Random random;
    private PowerUps currentPowerUp;

    /**
     * Instantiates a new Power ups manager.
     */
    public PowerUpsManager() {
        this.random = new Random();
    }

    /**
     * Spawn power up.
     */
    public void spawnPowerUp() {
        int x = random.nextInt();
        int y = random.nextInt();
        Type powerUpType = Type.getRandomType();
    }

    /**
     * Collect power up.
     *
     * @param tank the tank
     */
    public void collectPowerUp(PlayerTank tank) {
        if (currentPowerUp != null) {
            tank.addPoints(500);
            currentPowerUp.activate(tank);
            currentPowerUp = null;
        }
    }

    /**
     * Flashing tank hit.
     *
     * @param tank the tank
     */
    public void flashingTankHit(BaseTank tank) {
        if (currentPowerUp != null) {
            currentPowerUp = null;
        }
        spawnPowerUp();
    }

}
