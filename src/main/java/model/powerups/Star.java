package main.java.model.powerups;

import main.java.model.tanks.BaseTank;

public class Star {
    private int tier;

    public Star() {
        this.tier = 1;
    }

    public void activate(BaseTank tank) {
        if (tier < 4) {
            tier++;
        }
        switch (tier) {
            case 2:
                tank.setBulletSpeed(3);
            case 3:
                tank.setBullet(2);
            case 4:

        }
    }
}
