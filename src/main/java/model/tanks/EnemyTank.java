package main.java.model.tanks;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

public class EnemyTank extends BaseTank {
    private final Timer directionTimer;
    private Directions currentDirection = Directions.getRandomType();

    public EnemyTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed, String description) throws Exception {
        super(name, position, point, health, movementSpeed, bulletSpeed, description);
        // Update the direction randomly every 2 seconds
        directionTimer = new Timer(2000, e -> currentDirection = Directions.getRandomType());
    }

    private void changeImage(String image) {
        String imagePath = "../../../resource/img/enemy/";
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath + image))).getImage());
    }

    @Override
    public void move() {
        Directions direction = currentDirection;

        switch (direction) {
            case DOWN:
                moveDown(getMovementSpeed());
                break;
            case UP:
                moveUp(getMovementSpeed());
                break;
            case LEFT:
                moveLeft(getMovementSpeed());
                break;
            case RIGHT:
                moveRight(getMovementSpeed());
                break;
        }

        if (isDisplay()) {
            directionTimer.start();
        }
    }

    private void moveDown(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
        this.setDirection(Directions.DOWN);
        changeImage(currentImage + "_down.png");
    }

    private void moveUp(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
        this.setDirection(Directions.UP);
        changeImage(currentImage + "_up.png");
    }

    private void moveRight(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.setDirection(Directions.RIGHT);
        changeImage(currentImage + "_right.png");
    }

    private void moveLeft(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.setDirection(Directions.LEFT);
        changeImage(currentImage + "_left.png");
    }
}
