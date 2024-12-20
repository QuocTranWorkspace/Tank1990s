package jsd.project.tank90.main.model.tanks;

import jsd.project.tank90.main.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Fast tank.
 */
public class FastTank extends EnemyTank {
    private Directions direction;

    /**
     * Instantiates a new Fast tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public FastTank(Point2D position) throws Exception {
        super("FastTank", position, 200, 1, 3, 2, "Desc");
        this.direction = Directions.DOWN;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/enemy/enemy_b_down.png"))).getImage());
        this.currentImage = "enemy_b";
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
