package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

import java.util.List;

public class Grenade {
    public void activate(List<EnemyTank> enemyTanks) {
        for (EnemyTank tank : enemyTanks) {
            tank.takeDamage(1);
        }

    }
}
