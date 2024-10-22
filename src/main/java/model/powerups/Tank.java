package main.java.model.powerups;

public class Tank {
    private main.java.model.tanks.Tank player;

    public Tank() {
        this.player = player;
    }

    public void activate() {
        if (player.getPoint() >= 20000) {
            player.setLife(player.getLife() + 1);
        }
    }
}
