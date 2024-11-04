package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.BaseTank;

/**
 * The type Helmet.
 */
public class Helmet {
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
    }
}
