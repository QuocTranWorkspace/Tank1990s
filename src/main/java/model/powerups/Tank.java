package main.java.model.powerups;

/**
 * The type Tank.
 */
public class Tank {
    private main.java.model.tanks.BaseTank player;

    /**
     * Instantiates a new Tank.
     */
    public Tank() {
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
