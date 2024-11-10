package main.java.model.tanks;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Armor tank.
 */
public class ArmorTank extends EnemyTank {
    private Directions direction;

    /**
     * Instantiates a new Armor tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public ArmorTank(Point2D position) throws Exception {
        super("ArmorTank", position, 400, 4, 2, 1, "Desc");
        this.direction = Directions.DOWN;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/enemy/enemy_d_down.png"))).getImage());
        this.currentImage = "enemy_d";
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
