package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;
import main.java.service.GameplayManager;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Grenade {
    private final Set<EnemyTank> enemyTanks = GameplayManager.currentEnemies;
    public static Image image = new ImageIcon(Objects.requireNonNull(Grenade.class.getResource("../../../resource/img/bonus/bonus_grenade.png"))).getImage();

    public void activate() {
        Iterator<EnemyTank> enemyTanksIterator = enemyTanks.iterator();
        while (enemyTanksIterator.hasNext()) {
            enemyTanksIterator.remove();
        }
    }

    public Image getImage() {
        return image;
    }
}
