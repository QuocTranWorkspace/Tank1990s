package jsd.project.tank90.main.model.tanks;

import jsd.project.tank90.main.model.Bullet;
import jsd.project.tank90.main.model.Point2D;
import jsd.project.tank90.common.utils.SoundEffect;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

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
        Random random = new Random();
        directionTimer = new Timer(Math.max(500, random.nextInt(2000)), e -> currentDirection = Directions.getRandomType());
        shootingTimer = new Timer(Math.max(500, random.nextInt(1000)), event -> shoot());
    }

    private void changeImage(String image) {
        String imagePath = "../../../common/resources/img/enemy/";
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

    /**
     * Shoot.
     */
    public void shoot() {
        if (isShooting() && isShootable()) {
            Bullet bullet = new Bullet(0, 0, getBulletSpeed(), currentDirection);
            if (currentDirection == Directions.DOWN) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY() + 2 * this.getHeight() / 3, getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.UP) {
                bullet = new Bullet(this.getPosition().getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY(), getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.LEFT) {
                bullet = new Bullet(this.getPosition().getX() + bullet.getWidth(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
            } else if (currentDirection == Directions.RIGHT) {
                bullet = new Bullet(this.getPosition().getX() + 2 * this.getWidth() / 3, this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), currentDirection);
            }
            bulletList.add(bullet);
        }
    }

    /**
     * Stop all timer.
     */
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
