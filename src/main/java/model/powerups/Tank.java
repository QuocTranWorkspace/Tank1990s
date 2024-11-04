package main.java.model.powerups;

import main.java.model.PlayerTank;

/**
 * The type Tank.
 */
public class Tank {
    private final PlayerTank player;

    /**
     * Instantiates a new Tank.
     */
    public Tank(PlayerTank player) {
        this.player = player;
    }

    /**
     * Activate.
     */
    public void activate() {
        if (player.getPoint() >= 20000) {
            player.setLife(player.getLife() + 1);
        }
    }
}
