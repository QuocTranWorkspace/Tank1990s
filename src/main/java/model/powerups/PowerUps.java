package main.java.model.powerups;

import main.java.model.PlayerTank;
import main.java.model.tanks.BaseTank;
import main.java.model.tanks.EnemyTank;

import java.util.List;

/**
 * The type Power ups.
 */
public class PowerUps {
    private final Type type;
    private int x;
    private int y;

    /**
     * Instantiates a new Power ups.
     *
     * @param x    the x
     * @param y    the y
     * @param type the type
     */
    public PowerUps(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }

    /**
     * Activate.
     *
     * @param tank the tank
     */
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

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }
}
