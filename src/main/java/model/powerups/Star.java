package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.BaseTank;
import main.java.utils.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Star {
    public static final Image image = new ImageIcon(Objects.requireNonNull(Star.class.getResource("../../../resource/img/bonus/bonus_star.png"))).getImage();
    private final PlayerTank player;

    public Star(PlayerTank player) {
        this.player = player;
    }

    public void activate(BaseTank tank) {
        SoundEffect.tBonusHitSound();
        player.setTier(Math.min(player.getTier() + 1, 4));
        switch (player.getTier()) {
            case 2 -> tank.setBulletSpeed(3);
            case 3 -> tank.setBullet(2);
            case 4 -> {
            }
            default -> {}
        }
    }

    public Image getImage() {
        return image;
    }
}
