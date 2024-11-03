package main.java.model.environments;

/**
 * The interface Environment.
 */
public interface Environment {
    /**
     * Gets x.
     *
     * @return the x
     */
    int getX();

    /**
     * Gets y.
     *
     * @return the y
     */
    int getY();

    /**
     * Is colliding boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    boolean isColliding(int x, int y);
}
