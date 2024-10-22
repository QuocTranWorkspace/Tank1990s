package main.java.model.powerups;

import java.util.List;
import main.java.model.tanks.EnemyTank;

public class Grenade {
    public void activate(List<EnemyTank> enemyTanks) {
        for (EnemyTank tank : enemyTanks) {
            tank.takeDamage(1);
        }

    }

}
