package main.java.model.powerups;

import main.java.model.tanks.EnemyTank;
import main.java.service.TankManager;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Objects;

import static main.java.service.GameplayManager.currentEnemies;
import static main.java.service.GameplayManager.destroyTanks;

/**
 * The type Grenade.
 */
public class Grenade {
    /**
     * The constant image.
     */
    public static Image image = new ImageIcon(Objects.requireNonNull(Grenade.class.getResource("../../../resource/img/bonus/bonus_grenade.png"))).getImage();

    /**
     * Activate.
     *
     * @param tankManager the tank manager
     */
    public void activate(TankManager tankManager) {
        Iterator<EnemyTank> enemyTanksIterator = currentEnemies.iterator();
        while (enemyTanksIterator.hasNext()) {
            EnemyTank enemyTank = enemyTanksIterator.next();
            destroyTanks.startDestroyAnimation(enemyTank.getPosition().getX(), enemyTank.getPosition().getY(), enemyTank.getDirection());
            tankManager.getTankList().remove(enemyTank);
            enemyTanksIterator.remove();
        }
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
