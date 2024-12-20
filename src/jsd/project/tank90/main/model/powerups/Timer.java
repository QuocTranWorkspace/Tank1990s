package jsd.project.tank90.main.model.powerups;

import jsd.project.tank90.main.model.tanks.EnemyTank;
import jsd.project.tank90.main.service.TankManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

import static jsd.project.tank90.main.service.GameplayManager.currentEnemies;

/**
 * The type Timer.
 */
public class Timer {
    /**
     * The constant image.
     */
    public static final Image image = new ImageIcon(Objects.requireNonNull(Timer.class.getResource("../../../common/resources/img/bonus/bonus_clock.png"))).getImage();
    private final Set<EnemyTank> enemyTanks = currentEnemies;

    /**
     * Activate.
     *
     * @param tankManager the tank manager
     */
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

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }
}
