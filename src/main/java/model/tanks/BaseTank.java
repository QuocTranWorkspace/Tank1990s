package main.java.model.tanks;

import main.java.App;
import main.java.model.Bullet;
import main.java.model.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTank extends TankFunction {
    protected String currentImage = "";
    protected List<Bullet> bulletList = new ArrayList<>();
    private String name;
    private Point2D position;
    private Directions direction;
    private int point;
    private int health;
    private int movementSpeed;
    private int bulletSpeed;
    private int life;
    private String description;
    private int bullet;
    private Image image;
    private int width = App.FRAME_HEIGHT / 13;
    private int height = App.FRAME_HEIGHT / 13;
    private boolean isDisplay = false;
    private Point2D previousPosition;

    protected BaseTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed, String description) {
        this.name = isValidName(name) ? name : "DefaultTank";
        this.position = isValidPosition(position) ? position : new Point2D(0, 0);
        this.point = isValidPoint(point) ? point : 0;
        this.health = isValidHealth(health) ? health : 1;
        this.movementSpeed = isValidMovementSpeed(movementSpeed) ? movementSpeed : 1;
        this.bulletSpeed = isValidBulletSpeed(bulletSpeed) ? bulletSpeed : 1;
        this.description = description;
        this.direction = Directions.UP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    private boolean isValidPosition(Point2D position2) {
        int minX = 0;
        int maxX = App.FRAME_HEIGHT - width;
        int minY = 0;
        int maxY = App.FRAME_HEIGHT - width;

        if (position2 == null) {
            return false;
        }
        return position2.getX() >= minX && position2.getX() <= maxX && position2.getY() >= minY && position2.getY() <= maxY;
    }

    private boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        if (name.trim().isEmpty()) {
            return false;
        }
        int minLength = 1;
        int maxLength = 30;
        if (name.length() < minLength || name.length() > maxLength) {
            return false;
        }
        return name.matches("[a-zA-Z0-9]+");
    }

    public boolean isValidPoint(int point) {
        return point >= 0;
    }

    public boolean isValidHealth(int health) {
        return health > 0 && health <= 4;
    }

    public boolean isValidMovementSpeed(int movementSpeed) {
        return movementSpeed > 0 && movementSpeed <= 3;
    }

    public boolean isValidBulletSpeed(int bulletSpeed) {
        return bulletSpeed > 0 && bulletSpeed <= 3;
    }

    @Override
    public String toString() {
        return "Tank [name=" + name + ", position=" + position + ", point=" + point + ", health=" + health
                + ", movementSpeed=" + movementSpeed + ", bulletSpeed=" + bulletSpeed + ", description=" + description
                + "]";
    }

    @Override
    public void move() throws Exception {
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public void shoot(BaseTank tank) {
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void addPoints(int score) {
        this.point += score;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public boolean isShooting() {
        return bulletList.isEmpty();
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }

    public int clampToBounds(int value) {
        return Math.max(0, Math.min(App.FRAME_HEIGHT - App.FRAME_HEIGHT / 13, value));
    }

    public void updatePreviousPosition() {
        previousPosition = new Point2D(getPosition().getX(), getPosition().getY());
    }

    public void revertToPreviousPosition() {
        setPosition(previousPosition);
    }
}
