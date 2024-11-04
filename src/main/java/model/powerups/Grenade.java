package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;

import java.util.Iterator;
import java.util.List;

/**
 * The type Grenade.
 */
public class Grenade {
    private final List<EnemyTank> enemyTanks;

    public Grenade(List<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public void activate() {
        Iterator<EnemyTank> enemyTanksIterator = enemyTanks.iterator();
        while (enemyTanksIterator.hasNext()) {
            enemyTanksIterator.remove();
        }
    }
}
