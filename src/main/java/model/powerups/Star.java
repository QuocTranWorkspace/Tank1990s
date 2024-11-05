package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Star {
    private final PlayerTank player;
    public static final Image image = new ImageIcon(Objects.requireNonNull(Star.class.getResource("../../../resource/img/bonus/bonus_star.png"))).getImage();

    public Star(PlayerTank player) {
        this.player = player;
    }

    public void activate(BaseTank tank) {
        if (player.getTier() < 4) {
            player.setTier(player.getTier() + 1);
        }
        switch (player.getTier()) {
            case 2 -> tank.setBulletSpeed(3);
            case 3 -> tank.setBullet(2);
            case 4 -> {
            }
        }
    }

    public Image getImage() {
        return image;
    }
}
