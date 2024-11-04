package main.java.model.environments;

import main.java.App;
import main.java.model.Point2D;

import java.awt.*;

public abstract class Environment {
    private Image image;
    private Point2D position;
    private boolean isDestroyable;
    private boolean isWalkable;
    private int width = Math.round((float) App.FRAME_HEIGHT / 26);
    private int height = Math.round((float) App.FRAME_HEIGHT / 26);
    private int health = 99999;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public boolean isDestroyable() {
        return isDestroyable;
    }

    public void setDestroyable(boolean destroyable) {
        isDestroyable = destroyable;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public void setWalkable(boolean walkable) {
        isWalkable = walkable;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
