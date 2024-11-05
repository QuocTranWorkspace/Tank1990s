package main.java.model.powerups;

import main.java.App;
import main.java.model.PlayerTank;
import main.java.model.tanks.EnemyTank;

import java.awt.*;
import java.util.List;

public class PowerUps {
    private final Type type;
    private int x;
    private int y;
    private Image image;
    private int width = (int) (2 * App.FRAME_HEIGHT / 27.9);
    private int height = (int) (2 * App.FRAME_HEIGHT / 27.9);

    public PowerUps(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        setImage(type);
    }

    private void setImage(Type type) {
        switch (type) {
            case HELMET -> this.image = Helmet.image;
            case SHOVEL -> this.image = Shovel.image;
            case STAR -> this.image = Star.image;
            case TANK -> this.image = Tank.image;
            case GRENADE -> this.image = Grenade.image;
            case TIMER -> this.image = Timer.image;
        }
    }

    public void activate(PlayerTank tank) {
        switch (type) {
            case HELMET -> new Helmet(tank);
            case SHOVEL -> new Shovel();
            case STAR -> new Star(tank).activate(tank);
            case TANK -> new Tank(tank);
            case GRENADE -> new Grenade().activate();
            case TIMER -> new Timer().activate();
        }
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
