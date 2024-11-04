package main.java.model;

import main.java.App;
import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Player tank.
 */
public class PlayerTank extends BaseTank {
    private Directions direction;
    private Point2D position;
    private boolean shield = false;
    private int tier;

    /**
     * Instantiates a new Player tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public PlayerTank(Point2D position) throws Exception {
        super("PlayerTank", position, 0, 2, 2, 2, "Desc");
        this.direction = Directions.UP;
        this.position = position;
        this.tier = 1;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/player_up_1.png"))).getImage());
    }

    /**
     * Move.
     *
     * @param direction the direction
     * @param velocity  the velocity
     */
    public void move(Directions direction, int velocity) {
        updatePreviousPosition();
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
        String newImage = image + "_1.png";
        if (currentImage.compareTo(newImage) == 0) {
            newImage = image + "_2.png";
        }
        currentImage = newImage;
        String imagePath = "../../resource/img/player/";
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath + newImage))).getImage());
    }

    private void moveDown(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.direction = Directions.DOWN;
        changeImage("player_down");
    }

    private void moveUp(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(currentX), clampToBounds(newY)));
        this.direction = Directions.UP;
        changeImage("player_up");
    }

    private void moveRight(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.direction = Directions.RIGHT;
        changeImage("player_right");
    }

    private void moveLeft(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(clampToBounds(newX), clampToBounds(currentY)));
        this.direction = Directions.LEFT;
        changeImage("player_left");
    }

    /**
     * Shoot.
     */
    public void shoot() {
        if (isShooting()) {
            Bullet bullet = new Bullet(0 , 0, getBulletSpeed(), direction);
            if (direction == Directions.DOWN) {
                bullet = new Bullet(this.position.getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY() + this.getHeight(), getBulletSpeed(), direction);
            } else if (direction == Directions.UP) {
                bullet = new Bullet(this.position.getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY(), getBulletSpeed(), direction);
            } else if (direction == Directions.LEFT) {
                bullet = new Bullet(this.position.getX(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), direction);
            } else if (direction == Directions.RIGHT) {
                bullet = new Bullet(this.position.getX() + this.getWidth(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), direction);
            }
            bulletList.add(bullet);
        }
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Directions getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
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
