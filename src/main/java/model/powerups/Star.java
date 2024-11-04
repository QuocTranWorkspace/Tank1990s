package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.BaseTank;

public class Star {
    private final PlayerTank player;

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
}
