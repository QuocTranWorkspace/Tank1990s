package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;
import main.java.service.TankManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

import static main.java.service.GameplayManager.currentEnemies;

public class Timer {
    private final Set<EnemyTank> enemyTanks = currentEnemies;
    public static final Image image = new ImageIcon(Objects.requireNonNull(Timer.class.getResource("../../../resource/img/bonus/bonus_clock.png"))).getImage();

    public void activate(TankManager tankManager) {
        for (EnemyTank enemy : currentEnemies) {
            int enemyIndex = tankManager.getTankList().indexOf(enemy);
            if (enemyIndex >= 0) {
                tankManager.getTankList().get(enemyIndex).setMovable(false);
                tankManager.getTankList().get(enemyIndex).setShootable(false);
            }
        }

        javax.swing.Timer timer = new javax.swing.Timer(10000, e -> {
            for (EnemyTank enemy : currentEnemies) {
                int enemyIndex = tankManager.getTankList().indexOf(enemy);
                if (enemyIndex >= 0) {
                    tankManager.getTankList().get(enemyIndex).setMovable(true);
                    tankManager.getTankList().get(enemyIndex).setShootable(true);
                }
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    public Image getImage() {
        return image;
    }
}
