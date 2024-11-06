package main.java.model.powerups;

import main.java.model.PlayerTank;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Helmet {
    private final PlayerTank player;
    public static final Image image = new ImageIcon(Objects.requireNonNull(Helmet.class.getResource("../../../resource/img/bonus/bonus_helmet.png"))).getImage();

    public Helmet(PlayerTank tank) {
        this.player = tank;
    }

    public void activate() {
        player.setShield(true);

        Timer timer = new Timer(3000, e -> player.setShield(false));
        timer.setRepeats(false);
        timer.start();
    }

    public Image getImage() {
        return image;
    }
}
