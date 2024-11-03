package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

/**
 * The type Timer.
 */
public class Timer {
    private EnemyTank enemyTanks;

    /**
     * Instantiates a new Timer.
     */
    public Timer() {
        this.enemyTanks = enemyTanks;
    }

    /**
     * Activate.
     */
    public void activate() {
        enemyTanks.setPosition(enemyTanks.getPosition());
    }
}
