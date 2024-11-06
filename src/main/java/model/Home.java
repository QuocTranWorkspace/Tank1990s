package main.java.model;

import main.java.App;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Home {
    private boolean isAlive;
    private Point2D position;
    private Image image;
    private int width;
    private int height;

    public static Image deathImage = new ImageIcon(Objects.requireNonNull(Home.class.getResource("../../resource/img/gameplay/home_lose.png"))).getImage();

    public Home(Point2D position) {
        this.isAlive = true;
        this.position = position;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameplay/home.png"))).getImage();
        this.width = App.FRAME_HEIGHT / 14;
        this.height = App.FRAME_HEIGHT / 14;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
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
