package main.java.model.powerups;

import main.java.model.tanks.BaseTank;

/**
 * The type Helmet.
 */
public class Helmet {
    private final BaseTank player;

    /**
     * Instantiates a new Helmet.
     *
     * @param tank the tank
     */
    public Helmet(BaseTank tank) {
        this.player = tank;
    }

    /**
     * Activate.
     */
    public void activate() {
        player.setHealth(player.getHealth() + 1);
    }
}
