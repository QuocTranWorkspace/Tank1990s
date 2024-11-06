package main.java.model;

import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PlayerTank extends BaseTank {
    private Directions direction;
    private Point2D position;
    private boolean shield = false;
    private int tier;

    public static final Image shieldImage = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/shield.png"))).getImage();
    public static final Image invincibleImage1 = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/invincible_1.png"))).getImage();
    public static final Image invincibleImage2 = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/invincible_2.png"))).getImage();
    private Image currentInvincible = PlayerTank.invincibleImage1;

    public PlayerTank(Point2D position) {
        super("PlayerTank", position, 0, 2, 2, 2, "Desc");
        this.direction = Directions.UP;
        this.position = position;
        this.tier = 1;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/player_up_1.png"))).getImage());
        Timer waitTime = new Timer(1200, e -> {
            this.setInvincible(true);
            Timer timer = new Timer(3000, ev -> this.setInvincible(false));

            timer.setRepeats(false);
            timer.start();
        });

        waitTime.setRepeats(false);
        waitTime.start();
    }

    @Override
    public void move(int velocity) {
        updatePreviousPosition();
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

    public void shoot() {
        if (isShooting() && isShootable()) {
            Bullet bullet = new Bullet(0, 0, getBulletSpeed(), direction);
            if (direction == Directions.DOWN) {
                bullet = new Bullet(this.position.getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY() + 2 * this.getHeight() / 3, getBulletSpeed(), direction);
            } else if (direction == Directions.UP) {
                bullet = new Bullet(this.position.getX() + this.getWidth() / 2 - bullet.getHeight() / 2, this.getPosition().getY(), getBulletSpeed(), direction);
            } else if (direction == Directions.LEFT) {
                bullet = new Bullet(this.position.getX() + bullet.getWidth(), this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), direction);
            } else if (direction == Directions.RIGHT) {
                bullet = new Bullet(this.position.getX() + 2 * this.getWidth() / 3, this.getPosition().getY() + this.getHeight() / 2 - bullet.getHeight() / 2, getBulletSpeed(), direction);
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

    public Image getCurrentInvincible() {
        return currentInvincible;
    }

    public void setCurrentInvincible(Image currentInvincible) {
        this.currentInvincible = currentInvincible;
    }
}
