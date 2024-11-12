package jsd.project.tank90.main.model.powerups;

import jsd.project.tank90.main.model.PlayerTank;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Helmet.
 */
public class Helmet {
    /**
     * The constant image.
     */
    public static final Image image = new ImageIcon(Objects.requireNonNull(Helmet.class.getResource("../../../common/resources/img/bonus/bonus_helmet.png"))).getImage();
    private final PlayerTank player;

    /**
     * Instantiates a new Helmet.
     *
     * @param tank the tank
     */
    public Helmet(PlayerTank tank) {
        this.player = tank;
    }

    /**
     * Activate.
     */
    public void activate() {
        player.setShield(true);

        Timer timer = new Timer(10000, e -> player.setShield(false));
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
