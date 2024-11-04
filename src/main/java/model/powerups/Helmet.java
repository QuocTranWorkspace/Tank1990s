package main.java.model.powerups;

import main.java.model.PlayerTank;

public class Helmet {
    private final PlayerTank player;

    public Helmet(PlayerTank tank) {
        this.player = tank;
    }

    public void activate() {
        player.setShield(true);
    }
}
