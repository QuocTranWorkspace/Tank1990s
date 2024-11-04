package main.java.model.powerups;

import main.java.model.PlayerTank;

public class Tank {
    private final PlayerTank player;

    public Tank(PlayerTank player) {
        this.player = player;
    }

    public void activate() {
        if (player.getPoint() >= 20000) {
            player.setLife(player.getLife() + 1);
        }
    }
}
