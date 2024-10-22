package main.java.model.powerups;

public class Helmet {
    private final Tank player;

    public Helmet(Tank tank) {
        this.player = tank;
    }

    public void activate() {
        player.setHealth(player.getHealth() + 1);
    }
}
