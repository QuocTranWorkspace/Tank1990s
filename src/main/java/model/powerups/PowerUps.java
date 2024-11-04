package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.EnemyTank;

import java.util.List;

public class PowerUps {
    private final Type type;
    private int x;
    private int y;

    public PowerUps(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }

    public void activate(PlayerTank tank) {
        switch (type) {
            case HELMET -> new Helmet(tank);
            case SHOVEL -> new Shovel();
            case STAR -> new Star(tank).activate(tank);
            case TANK -> new Tank(tank);
        }
    }

    public void activate(List<EnemyTank> tanks) {
        switch (type) {
            case GRENADE -> new Grenade(tanks).activate();
            case TIMER -> new Timer(tanks).activate();
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
