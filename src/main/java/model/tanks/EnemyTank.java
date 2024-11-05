package main.java.model.tanks;

import main.java.model.Bullet;
import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class EnemyTank extends BaseTank {
    private final Timer directionTimer;
    private final Timer shootingTimer;
    private Directions currentDirection = Directions.getRandomType();

    public EnemyTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed, String description) throws Exception {
        super(name, position, point, health, movementSpeed, bulletSpeed, description);
        Random random = new Random();
        directionTimer = new Timer(Math.max(500, random.nextInt(2000)), e -> currentDirection = Directions.getRandomType());
        shootingTimer = new Timer(Math.max(500, random.nextInt(1000)), event -> shoot());
    }

    private void changeImage(String image) {
        String imagePath = "../../../resource/img/enemy/";
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath + image))).getImage());
    }

    @Override
    public void move(int velocity) {
        updatePreviousPosition();
        Directions direction = currentDirection;

        if (isMovable()) {
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

        if (isDisplay()) {
            directionTimer.start();
        }
    }

    private void moveDown(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.setDirection(Directions.DOWN);
        changeImage(currentImage + "_down.png");
    }

    private void moveUp(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.setDirection(Directions.UP);
        changeImage(currentImage + "_up.png");
    }

    private void moveRight(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.setDirection(Directions.RIGHT);
        changeImage(currentImage + "_right.png");
    }

    private void moveLeft(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.setDirection(Directions.LEFT);
        changeImage(currentImage + "_left.png");
    }

    public void shoot() {
        if (!isShooting() && isShootable()) {
            Bullet bullet = new Bullet(0, 0, getBulletSpeed(), currentDirection);
            if (currentDirection == Directions.DOWN) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY() + 2 * this.getHeight() / 3, getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.UP) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY(), getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.LEFT) {
                bullet = new Bullet(this.getPosition().getX() + bullet.getWidth(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.RIGHT) {
                bullet = new Bullet(this.getPosition().getX() +  2 * this.getWidth() / 3, this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
            }
            bulletList.add(bullet);
        }
    }

    public void stopAllTimer() {
        if (directionTimer != null && directionTimer.isRunning()) {
            this.directionTimer.stop();
        }
        if (shootingTimer != null && shootingTimer.isRunning()) {
            this.shootingTimer.stop();
        }
    }

    @Override
    public void setDisplay(boolean display) {
        super.setDisplay(display);
        shootingTimer.start();
    }
}
