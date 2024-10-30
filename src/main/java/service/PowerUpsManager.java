package main.java.service;

import main.java.model.powerups.PowerUps;
import main.java.model.powerups.Type;
import main.java.model.tanks.BaseTank;

import java.util.Random;

public class PowerUpsManager {
    private PowerUps currentPowerUp;
    private final Random random;

    public PowerUpsManager() {
        this.random = new Random();
    }

    public void spawnPowerUp() {
        int x = random.nextInt();
        int y = random.nextInt();
        Type powerUpType = Type.getRandomType();
    }

    public void collectPowerUp(BaseTank tank) {
        if (currentPowerUp != null) {
            tank.addPoints(500);
            currentPowerUp.activate(tank);
            currentPowerUp = null;
        }
    }

    public void flashingTankHit(BaseTank tank) {
        if (currentPowerUp != null) {
            currentPowerUp = null;
        }
        spawnPowerUp();
    }

}
