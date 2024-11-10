package main.java.model;

import main.java.App;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Home.
 */
public class Home {
    /**
     * The constant deathImage.
     */
    public static Image deathImage = new ImageIcon(Objects.requireNonNull(Home.class.getResource("../../resource/img/gameplay/home_lose.png"))).getImage();
    private boolean isAlive;
    private Point2D position;
    private Image image;
    private int width;
    private int height;

    /**
     * Instantiates a new Home.
     *
     * @param position the position
     */
    public Home(Point2D position) {
        this.isAlive = true;
        this.position = position;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/home.png"))).getImage();
        this.width = App.FRAME_HEIGHT / 14;
        this.height = App.FRAME_HEIGHT / 14;
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets alive.
     *
     * @param alive the alive
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
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
}
