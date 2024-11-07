package main.java.model.powerups;

import main.java.App;
import main.java.model.PlayerTank;
import main.java.service.TankManager;

import java.awt.*;

public class PowerUps {
    private final Type type;
    private int x;
    private int y;
    private Image image;
    private int width = (int) (2 * App.FRAME_HEIGHT / 27.9);
    private int height = (int) (2 * App.FRAME_HEIGHT / 27.9);

    private final TankManager tankManager;

    public PowerUps(int x, int y, Type type, TankManager tankManager) {
        this.x = x;
        this.y = y;
        this.type = type;
        setImage(type);
        this.tankManager = tankManager;
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
            case HELMET -> new Helmet(tank).activate();
            case SHOVEL -> Shovel.getInstance().activate();
            case STAR -> new Star(tank).activate(tank);
            case TANK -> new Tank(tank).activate();
            case GRENADE -> new Grenade().activate(tankManager);
            case TIMER -> new Timer().activate(tankManager);
        }
    }

    public void deactivate() {
        if (type == Type.SHOVEL) {
            Shovel.getInstance().deactivate();
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

    public Type getType() {
        return type;
    }
}
