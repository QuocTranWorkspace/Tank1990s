package main.java.model.tanks;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Power tank.
 */
public class PowerTank extends EnemyTank {
    private Directions direction;

    /**
     * Instantiates a new Power tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public PowerTank(Point2D position) throws Exception {
        super("PowerTank", position, 300, 1, 2, 3, "Desc");
        this.direction = Directions.DOWN;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/enemy/enemy_c_down.png"))).getImage());
        this.currentImage = "enemy_c";
    }

    public Directions getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Tank [name=" + getName() + ", position=" + getPosition() + ", point=" + getPoint() + ", health="
                + getHealth()
                + ", movementSpeed=" + getMovementSpeed() + ", bulletSpeed=" + getBulletSpeed() + ", description="
                + getDescription()
                + "]";
    }
}
