package jsd.project.tank90.main.model;

import jsd.project.tank90.main.Main;

/**
 * The type Point 2 d.
 */
public final class Point2D {
    private int x;
    private int y;

    /**
     * Instantiates a new Point 2 d.
     *
     * @param x the x
     * @param y the y
     */
    public Point2D(int x, int y) {
        this.x = clampToBounds(x);
        this.y = clampToBounds(y);
    }

    private int clampToBounds(int value) {
        return Math.max(0, Math.min(Main.FRAME_HEIGHT, value));
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
        this.x = clampToBounds(x);
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
        this.y = clampToBounds(y);
    }
}
