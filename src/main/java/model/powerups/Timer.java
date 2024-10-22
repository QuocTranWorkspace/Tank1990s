package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

public class Timer {
    private EnemyTank enemyTanks;

    public Timer() {
        this.enemyTanks = enemyTanks;
    }

    public void activate() {
        enemyTanks.setPosition(enemyTanks.getPosition());
    }
}
