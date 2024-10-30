package main.java.model.powerups;

import main.java.model.tanks.BaseTank;

public class PowerUps {
    private int x;
    private int y;
    private final Type type;

    public PowerUps(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }

    public void activate(BaseTank tank) {
        switch (type) {
            case GRENADE -> new Grenade();
            case HELMET -> new Helmet(tank);
            case SHOVEL -> new Shovel();
            case STAR -> new Star();
            case TANK -> new Tank();
            case TIMER -> new Timer();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
