package jsd.project.tank90.main.model.powerups;

import jsd.project.tank90.main.model.PlayerTank;
import jsd.project.tank90.common.utils.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Tank.
 */
public class Tank {
    /**
     * The constant image.
     */
    public static final Image image = new ImageIcon(Objects.requireNonNull(Tank.class.getResource("../../../common/resources/img/bonus/bonus_tank.png"))).getImage();
    private final PlayerTank player;

    /**
     * Instantiates a new Tank.
     *
     * @param player the player
     */
    public Tank(PlayerTank player) {
        this.player = player;
    }

    /**
     * Activate.
     */
    public void activate() {
        if (player.getPoint() >= 20000) {
            SoundEffect.lifeSound();
            player.setLife(player.getLife() + 1);
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
