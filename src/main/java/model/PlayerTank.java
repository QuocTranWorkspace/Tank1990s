package main.java.model;

import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.util.Objects;

public class PlayerTank extends BaseTank {
    private Directions direction;
    private Point2D position;
    private String currentImage = "";

    public PlayerTank(Point2D position) throws Exception {
        super("PlayerTank", position, 0, 2, 2, 2, "Desc");
        this.direction = Directions.UP;
        this.position = position;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/playerUp1.png"))).getImage());
    }

    public void move(Directions direction, int velocity) throws Exception {
        switch (direction) {
            case DOWN:
                moveDown(velocity);
                break;
            case UP:
                moveUp(velocity);
                break;
            case LEFT:
                moveLeft(velocity);
                break;
            case RIGHT:
                moveRight(velocity);
                break;
        }
    }

    private void changeImage(String image) {
        String newImage = image + "1.png";
        if (currentImage.compareTo(newImage) == 0) {
            newImage = image + "2.png";
        }
        currentImage = newImage;
        String imagePath = "../../resource/img/player/";
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath + newImage))).getImage());
        if (this.direction == Directions.UP || this.direction == Directions.DOWN) {
            this.width = 26;
            this.height = 32;
        }
        else {
            this.width = 32;
            this.height = 26;
        }
    }

    private void moveDown(int velocity) throws Exception {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
        this.direction = Directions.DOWN;
        changeImage("playerDown");
    }

    private void moveUp(int velocity) throws Exception {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
        this.direction = Directions.UP;
        changeImage("playerUp");
    }

    private void moveRight(int velocity) throws Exception {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.direction = Directions.RIGHT;
        changeImage("playerRight");
    }

    private void moveLeft(int velocity) throws Exception {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.direction = Directions.LEFT;
        changeImage("playerLeft");
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
