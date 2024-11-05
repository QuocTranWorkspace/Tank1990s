package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;
import main.java.service.GameplayManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Timer {
    private final Set<EnemyTank> enemyTanks = GameplayManager.currentEnemies;
    public static final Image image = new ImageIcon(Objects.requireNonNull(Timer.class.getResource("../../../resource/img/bonus/bonus_clock.png"))).getImage();

    public void activate() {
        for (EnemyTank enemy : enemyTanks) {
            enemy.setMovementSpeed(0);
        }
    }

    public Image getImage() {
        return image;
    }
}
