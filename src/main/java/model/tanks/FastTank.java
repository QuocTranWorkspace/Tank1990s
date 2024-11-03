package main.java.model.tanks;

import main.java.model.Point2D;

public class FastTank extends EnemyTank {
    private Directions direction;

    public FastTank(Point2D position) throws Exception {
        super("Fast Tank", position, 200, 1, 3, 2, "Desc");
        this.direction = Directions.DOWN;
    }

    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
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
