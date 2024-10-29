package main.java.model.powerups;

import main.java.model.tanks.BaseTank;

public class Helmet {
    private final BaseTank player;

    public Helmet(BaseTank tank) {
        this.player = tank;
    }

    public void activate() {
        player.setHealth(player.getHealth() + 1);
    }
}
