package main.java.model.powerups;

import main.java.model.PlayerTank;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Tank {
    public static final Image image = new ImageIcon(Objects.requireNonNull(Tank.class.getResource("../../../resource/img/bonus/bonus_tank.png"))).getImage();
    private final PlayerTank player;

    public Tank(PlayerTank player) {
        this.player = player;
    }

    public void activate() {
        if (player.getPoint() >= 20000) {
            player.setLife(player.getLife() + 1);
        }
    }

    public Image getImage() {
        return image;
    }
}
