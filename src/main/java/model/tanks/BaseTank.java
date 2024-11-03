package main.java.model.tanks;

import main.java.model.Point2D;

import java.awt.*;

public abstract class BaseTank extends TankFunction {
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
    protected int width = 26;
    protected int height = 32;

    protected BaseTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed,
            String description) throws Exception {
        if (!isValidName(name)) {
            throw new Exception("Invalid tank name!");
        }
        if (!isValidPosition(position)) {
            throw new Exception("Invalid tank position!");
        }
        // TODO: add validation

        if (!isValidPoint(point)) {
            throw new Exception("Invalid point value!");
        }
        if (!isValidHealth(health)) {
            throw new Exception("Invalid health value!");
        }
        if (!isValidMovementSpeed(movementSpeed)) {
            throw new Exception("Invalid movement speed value!");
        }
        if (!isValidBulletSpeed(bulletSpeed)) {
            throw new Exception("Invalid bullet speed value!");
        }

        this.name = name;
        this.position = position;
        this.direction = Directions.UP;
        this.point = point;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.bulletSpeed = bulletSpeed;
        this.description = description;
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
        int minX = 1;
        int maxX = 100;
        int minY = 1;
        int maxY = 100;

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
        switch (direction) {
            case UP:
                position = new Point2D(position.getX(), position.getY() - movementSpeed);
                break;
            case DOWN:
                position = new Point2D(position.getX(), position.getY() + movementSpeed);
                break;
            case LEFT:
                position = new Point2D(position.getX() - movementSpeed, position.getY());
                break;
            case RIGHT:
                position = new Point2D(position.getX() + movementSpeed, position.getY());
                break;
            default:
                throw new IllegalStateException("Unexpected direction: " + direction);
        }
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public void shoot(BaseTank tank) {
        // TODO Auto-generated method stub

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
}
