package main.java.model.powerups;

import main.java.model.PlayerTank;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Helmet {
    public static final Image image = new ImageIcon(Objects.requireNonNull(Helmet.class.getResource("../../../resource/img/bonus/bonus_helmet.png"))).getImage();
    private final PlayerTank player;

    public Helmet(PlayerTank tank) {
        this.player = tank;
    }

    public void activate() {
        player.setShield(true);

        Timer timer = new Timer(10000, e -> player.setShield(false));
        timer.setRepeats(false);
        timer.start();
    }

    public Image getImage() {
        return image;
    }
}
