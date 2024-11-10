package main.java.model;

import main.java.App;
import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;
import main.java.utils.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Player tank.
 */
public class PlayerTank extends BaseTank {
    /**
     * The constant shieldImage.
     */
    public static final Image shieldImage = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/shield.png"))).getImage();
    /**
     * The constant invincibleImage1.
     */
    public static final Image invincibleImage1 = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/invincible_1.png"))).getImage();
    /**
     * The constant invincibleImage2.
     */
    public static final Image invincibleImage2 = new ImageIcon(Objects.requireNonNull(PlayerTank.class.getResource("../../resource/img/player/invincible_2.png"))).getImage();
    private Directions direction;
    private Point2D position;
    private boolean shield = false;
    private int tier;
    private Image currentInvincible = PlayerTank.invincibleImage1;

    /**
     * Instantiates a new Player tank.
     *
     * @param position the position
     */
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
            SoundEffect.movingSound();
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

    /**
     * Shoot.
     */
    public void shoot() {
        if (isShooting() && isShootable()) {
            SoundEffect.shootSound();
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

    /**
     * Respawn.
     */
    public void respawn() {
        this.direction = Directions.UP;
        this.position = new Point2D((int) (10 * App.FRAME_HEIGHT / 27.9), (int) (25 * App.FRAME_HEIGHT / 27.9));
        this.tier = 1;
        this.setInvincible(true);
        Timer timer = new Timer(3000, ev -> this.setInvincible(false));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public Directions getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    /**
     * Is shield boolean.
     *
     * @return the boolean
     */
    public boolean isShield() {
        return shield;
    }

    /**
     * Sets shield.
     *
     * @param shield the shield
     */
    public void setShield(boolean shield) {
        this.shield = shield;
    }

    /**
     * Gets tier.
     *
     * @return the tier
     */
    public int getTier() {
        return tier;
    }

    /**
     * Sets tier.
     *
     * @param tier the tier
     */
    public void setTier(int tier) {
        this.tier = tier;
    }

    /**
     * Gets current invincible.
     *
     * @return the current invincible
     */
    public Image getCurrentInvincible() {
        return currentInvincible;
    }

    /**
     * Sets current invincible.
     *
     * @param currentInvincible the current invincible
     */
    public void setCurrentInvincible(Image currentInvincible) {
        this.currentInvincible = currentInvincible;
    }

    @Override
    public boolean isShooting() {
        if (this.tier < 3) {
            return bulletList.isEmpty();
        } else
            return bulletList.size() < getBullet();
    }
}
