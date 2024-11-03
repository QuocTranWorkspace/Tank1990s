package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

import java.util.List;

/**
 * The type Grenade.
 */
public class Grenade {
    /**
     * Activate.
     *
     * @param enemyTanks the enemy tanks
     */
    public void activate(List<EnemyTank> enemyTanks) {
        for (EnemyTank tank : enemyTanks) {
            tank.takeDamage(1);
        }
    }
}
