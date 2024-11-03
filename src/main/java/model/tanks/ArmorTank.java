package main.java.model.tanks;

import main.java.model.Point2D;

public class ArmorTank extends EnemyTank {
    private Directions direction;

    public ArmorTank(Point2D position) throws Exception {
        super("Armor Tank", position, 400, 4, 2, 1, "Desc");
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
