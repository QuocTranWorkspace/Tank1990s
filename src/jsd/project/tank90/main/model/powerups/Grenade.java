package jsd.project.tank90.main.model.powerups;

import jsd.project.tank90.main.model.tanks.EnemyTank;
import jsd.project.tank90.main.service.TankManager;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Objects;

import static jsd.project.tank90.main.service.GameplayManager.currentEnemies;
import static jsd.project.tank90.main.service.GameplayManager.destroyTanks;

/**
 * The type Grenade.
 */
public class Grenade {
    /**
     * The constant image.
     */
    public static Image image = new ImageIcon(Objects.requireNonNull(Grenade.class.getResource("../../../common/resources/img/bonus/bonus_grenade.png"))).getImage();

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
