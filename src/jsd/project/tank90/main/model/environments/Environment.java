package jsd.project.tank90.main.model.environments;

import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.model.Point2D;

import java.awt.*;

/**
 * The type Environment.
 */
public abstract class Environment {
    private Image image;
    private Point2D position;
    private boolean isDestroyable;
    private boolean isWalkable;
    private int width = Main.FRAME_HEIGHT / 28;
    private int height = Main.FRAME_HEIGHT / 28;
    private int health = 99999;

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Is destroyable boolean.
     *
     * @return the boolean
     */
    public boolean isDestroyable() {
        return isDestroyable;
    }

    /**
     * Sets destroyable.
     *
     * @param destroyable the destroyable
     */
    public void setDestroyable(boolean destroyable) {
        isDestroyable = destroyable;
    }

    /**
     * Is walkable boolean.
     *
     * @return the boolean
     */
    public boolean isWalkable() {
        return isWalkable;
    }

    /**
     * Sets walkable.
     *
     * @param walkable the walkable
     */
    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
