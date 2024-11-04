package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

import java.util.List;

/**
 * The type Timer.
 */
public class Timer {
    private final List<EnemyTank> enemyTanks;

    /**
     * Instantiates a new Timer.
     */
    public Timer(List<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    /**
     * Activate.
     */
    public void activate() {
        for (EnemyTank enemy : enemyTanks) {
            enemy.setMovementSpeed(0);
        }
    }
}
