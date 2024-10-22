package main.java.model.powerups;

import main.java.model.tanks.Tank;

import java.util.Random;

public class PowerupsManager {
    private PowerUps currentPowerUp;
    private Random random;

    public PowerupsManager() {
        this.random = new Random();
    }

    public void spawnPowerUp() {
        int x = random.nextInt();
        int y = random.nextInt();
        Type powerUpType = Type.getRandomType();
    }

    public void collectPowerUp(Tank tank) {
        if (currentPowerUp != null) {
            tank.addPoints(500);
            currentPowerUp.activate(tank);
            currentPowerUp = null;
        }
    }

    public void flashingTankHit(Tank tank) {
        if (currentPowerUp != null) {
            currentPowerUp = null;
        }
        spawnPowerUp();
    }

}
