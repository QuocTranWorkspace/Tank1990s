package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

import java.util.List;

public class Timer {
    private final List<EnemyTank> enemyTanks;

    public Timer(List<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public void activate() {
        for (EnemyTank enemy : enemyTanks) {
            enemy.setMovementSpeed(0);
        }
    }
}
