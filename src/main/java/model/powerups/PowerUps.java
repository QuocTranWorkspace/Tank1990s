package main.java.model.powerups;

import main.java.App;
import main.java.model.PlayerTank;
import main.java.service.TankManager;
import main.java.utils.SoundEffect;

import java.awt.*;

/**
 * The type Power ups.
 */
public class PowerUps {
    private final Type type;
    private final TankManager tankManager;
    private int x;
    private int y;
    private Image image;
    private int width = (int) (2 * App.FRAME_HEIGHT / 27.9);
    private int height = (int) (2 * App.FRAME_HEIGHT / 27.9);

    /**
     * Instantiates a new Power ups.
     *
     * @param x           the x
     * @param y           the y
     * @param type        the type
     * @param tankManager the tank manager
     */
    public PowerUps(int x, int y, Type type, TankManager tankManager) {
        this.x = x;
        this.y = y;
        this.type = type;
        setImage(type);
        this.tankManager = tankManager;
    }

    /**
     * Activate.
     *
     * @param tank the tank
     */
    public void activate(PlayerTank tank) {
        SoundEffect.bonusSound();
        switch (type) {
            case HELMET -> new Helmet(tank).activate();
            case SHOVEL -> Shovel.getInstance().activate();
            case STAR -> new Star(tank).activate(tank);
            case TANK -> new Tank(tank).activate();
            case GRENADE -> new Grenade().activate(tankManager);
            case TIMER -> new Timer().activate(tankManager);
        }
    }

    /**
     * Deactivate.
     */
    public void deactivate() {
        if (type == Type.SHOVEL) {
            Shovel.getInstance().deactivate();
        }
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
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
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

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }
}
