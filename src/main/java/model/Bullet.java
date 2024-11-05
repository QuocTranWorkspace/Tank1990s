package main.java.model;

import main.java.App;
import main.java.model.tanks.Directions;
import main.java.service.GameplayManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Bullet {
    private int x;
    private int y;
    private int speed;
    private Directions direction;
    private boolean isActive = true;
    private Image image;
    private int width = App.FRAME_HEIGHT / 13 / 4;
    private int height = App.FRAME_HEIGHT / 13 / 4;

    public Bullet(int x, int y, int speed, Directions direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    public void move() {
        int defaultSpeed = GameplayManager.VELOCITY_SHOOT;
        switch (direction) {
            case UP -> {
                y -= speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/bullet_up.png"))).getImage();
            }
            case DOWN -> {
                y += speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/bullet_down.png"))).getImage();
            }
            case LEFT -> {
                x -= speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/bullet_left.png"))).getImage();
            }
            case RIGHT -> {
                x += speed * 2 * defaultSpeed;
                this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/bullet_right.png"))).getImage();
            }
        }
        // Set isActive to false if bullet moves outside the panel
        if (x < App.FRAME_HEIGHT / 27.9 || x >  26.5 * App.FRAME_HEIGHT / 27.9 || y < App.FRAME_HEIGHT / 27.9 || y > 26.5 * App.FRAME_HEIGHT / 27.9) {
            isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
}

