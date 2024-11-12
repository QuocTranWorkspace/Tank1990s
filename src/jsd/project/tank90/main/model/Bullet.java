package jsd.project.tank90.main.model;

import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.model.tanks.Directions;
import jsd.project.tank90.main.service.GameplayManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Bullet.
 */
public class Bullet {
    private int x;
    private int y;
    private int speed;
    private Directions direction;
    private boolean isActive = true;
    private Image image;
    private int width = Main.FRAME_HEIGHT / 13 / 4;
    private int height = Main.FRAME_HEIGHT / 13 / 4;

    /**
     * Instantiates a new Bullet.
     *
     * @param x         the x
     * @param y         the y
     * @param speed     the speed
     * @param direction the direction
     */
    public Bullet(int x, int y, int speed, Directions direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Move.
     */
    public void move() {
        int defaultSpeed = GameplayManager.VELOCITY_SHOOT;
        switch (direction) {
            case UP -> {
                y -= speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/gameplay/bullet_up.png"))).getImage();
            }
            case DOWN -> {
                y += speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/gameplay/bullet_down.png"))).getImage();
            }
            case LEFT -> {
                x -= speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/gameplay/bullet_left.png"))).getImage();
            }
            case RIGHT -> {
                x += speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/gameplay/bullet_right.png"))).getImage();
            }
        }
        // Set isActive to false if bullet moves outside the panel
        if (x < Main.FRAME_HEIGHT / 27.9 || x > 26.5 * Main.FRAME_HEIGHT / 27.9 || y < Main.FRAME_HEIGHT / 27.9 || y > 26.5 * Main.FRAME_HEIGHT / 27.9) {
            isActive = false;
        }
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        isActive = active;
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

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
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

