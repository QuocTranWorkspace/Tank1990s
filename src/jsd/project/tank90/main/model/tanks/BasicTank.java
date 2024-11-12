package jsd.project.tank90.main.model.tanks;

import jsd.project.tank90.main.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Basic tank.
 */
public class BasicTank extends EnemyTank {
    private Directions direction;

    /**
     * Instantiates a new Basic tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public BasicTank(Point2D position) throws Exception {
        super("BasicTank", position, 100, 1, 1, 1, "Desc");
        this.direction = Directions.DOWN;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/enemy/enemy_a_down.png"))).getImage());
        this.currentImage = "enemy_a";
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
