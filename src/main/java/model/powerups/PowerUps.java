package main.java.model.powerups;

import main.java.model.tanks.BaseTank;

/**
 * The type Power ups.
 */
public class PowerUps {
    private int x;
    private int y;
    private final Type type;

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
