package main.java.model;

import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.util.Objects;

public class PlayerTank extends BaseTank {
    private Directions direction;
    private Point2D position;

    public PlayerTank(Point2D position) throws Exception {
        super("PlayerTank", position, 0, 2, 2, 2, "Desc");
        this.direction = Directions.DOWN;
        this.position = position;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/playerUp.png"))).getImage());
    }

    public void move(Directions direction, int velocity) throws Exception {
        switch (direction) {
            case DOWN:
                moveDown(velocity);
                break;
            case UP: break;
            case LEFT: break;
            case RIGHT: break;
        }
    }

    private void moveDown(int velocity) throws Exception {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
    }

    @Override
    public Point2D getPosition() {
        return position;
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
