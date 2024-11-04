package main.java.model.tanks;

import main.java.model.Bullet;
import main.java.model.Point2D;
import main.java.service.GameplayManager;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The type Enemy tank.
 */
public class EnemyTank extends BaseTank {
    private final Timer directionTimer;
    private final Timer shootingTimer;
    private Directions currentDirection = Directions.getRandomType();

    /**
     * Instantiates a new Enemy tank.
     *
     * @param name          the name
     * @param position      the position
     * @param point         the point
     * @param health        the health
     * @param movementSpeed the movement speed
     * @param bulletSpeed   the bullet speed
     * @param description   the description
     * @throws Exception the exception
     */
    public EnemyTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed, String description) throws Exception {
        super(name, position, point, health, movementSpeed, bulletSpeed, description);
        // Update the direction randomly every 2 seconds
        directionTimer = new Timer(2000, e -> currentDirection = Directions.getRandomType());
        shootingTimer = new Timer(1000, event -> shoot());
    }

    private void changeImage(String image) {
        String imagePath = "../../../resource/img/enemy/";
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath + image))).getImage());
    }

    @Override
    public void move() {
        updatePreviousPosition();
        Directions direction = currentDirection;

        switch (direction) {
            case DOWN:
                moveDown();
                break;
            case UP:
                moveUp();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }

        if (isDisplay()) {
            directionTimer.start();
        }
    }

    private void moveDown() {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + GameplayManager.VELOCITY_MOVE * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.setDirection(Directions.DOWN);
        changeImage(currentImage + "_down.png");
    }

    private void moveUp() {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - GameplayManager.VELOCITY_MOVE * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.setDirection(Directions.UP);
        changeImage(currentImage + "_up.png");
    }

    private void moveRight() {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + GameplayManager.VELOCITY_MOVE * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.setDirection(Directions.RIGHT);
        changeImage(currentImage + "_right.png");
    }

    private void moveLeft() {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - GameplayManager.VELOCITY_MOVE * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.setDirection(Directions.LEFT);
        changeImage(currentImage + "_left.png");
    }

    /**
     * Shoot.
     */
    public void shoot() {
        if (isShooting()) {
            Bullet bullet = new Bullet(0 , 0, getBulletSpeed(), currentDirection);
            if (currentDirection == Directions.DOWN) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY() + this.getHeight(), getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.UP) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY(), getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.LEFT) {
                bullet = new Bullet(this.getPosition().getX(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.RIGHT) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
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
