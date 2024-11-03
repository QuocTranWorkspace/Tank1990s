package main.java.model;

import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

/**
 * The type Player tank.
 */
public class PlayerTank extends BaseTank {
    private Directions direction;
    private Point2D position;
    private String currentImage = "";

    /**
     * Instantiates a new Player tank.
     *
     * @param position the position
     * @throws Exception the exception
     */
    public PlayerTank(Point2D position) throws Exception {
        super("PlayerTank", position, 0, 2, 2, 3, "Desc");
        this.direction = Directions.UP;
        this.position = position;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/player_up_1.png"))).getImage());
    }

    /**
     * Move.
     *
     * @param direction the direction
     * @param velocity  the velocity
     * @throws Exception the exception
     */
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
        this.setPosition(new Point2D(currentX, newY));
        this.direction = Directions.DOWN;
        changeImage("player_down");
    }

    private void moveUp(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newY = currentY - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(currentX, newY));
        this.direction = Directions.UP;
        changeImage("player_up");
    }

    private void moveRight(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX + velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.direction = Directions.RIGHT;
        changeImage("player_right");
    }

    private void moveLeft(int velocity) {
        int currentX = this.getPosition().getX();
        int currentY = this.getPosition().getY();
        int newX = currentX - velocity * this.getMovementSpeed();
        this.setPosition(new Point2D(newX, currentY));
        this.direction = Directions.LEFT;
        changeImage("player_left");
    }

    /**
     * Shoot.
     */
    public void shoot() {
        if (direction == Directions.DOWN) {
            Bullet bullet = new Bullet(this.position.getX() + this.getWidth() / 2, this.getPosition().getY() + this.getHeight(), getBulletSpeed(), direction);
            bulletList.add(bullet);
        } else if (direction == Directions.UP) {
            Bullet bullet = new Bullet(this.position.getX() + this.getWidth() / 2, this.getPosition().getY(), getBulletSpeed(), direction);
            bulletList.add(bullet);
        } else if (direction == Directions.LEFT) {
            Bullet bullet = new Bullet(this.position.getX(), this.getPosition().getY() + this.getHeight() / 2, getBulletSpeed(), direction);
            bulletList.add(bullet);
        } else if (direction == Directions.RIGHT) {
            Bullet bullet = new Bullet(this.position.getX() + this.getWidth(), this.getPosition().getY() + this.getHeight() / 2, getBulletSpeed(), direction);
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

    /**
     * Gets bullet list.
     *
     * @return the bullet list
     */
    public List<Bullet> getBulletList() {
        return bulletList;
    }

    /**
     * Sets bullet list.
     *
     * @param bulletList the bullet list
     */
    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
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
