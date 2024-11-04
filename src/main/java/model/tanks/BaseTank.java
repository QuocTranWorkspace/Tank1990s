package main.java.model.tanks;

import main.java.App;
import main.java.model.Bullet;
import main.java.model.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Base tank.
 */
public abstract class BaseTank extends TankFunction {
    /**
     * The Current image.
     */
    protected String currentImage = "";
    /**
     * The Bullet list.
     */
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

    /**
     * Instantiates a new Base tank.
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     * Gets point.
     *
     * @return the point
     */
    public int getPoint() {
        return point;
    }

    /**
     * Sets point.
     *
     * @param point the point
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * Gets health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets health.
     *
     * @param health the health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets movement speed.
     *
     * @return the movement speed
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Sets movement speed.
     *
     * @param movementSpeed the movement speed
     */
    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Gets bullet speed.
     *
     * @return the bullet speed
     */
    public int getBulletSpeed() {
        return bulletSpeed;
    }

    /**
     * Sets bullet speed.
     *
     * @param bulletSpeed the bullet speed
     */
    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets life.
     *
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * Sets life.
     *
     * @param life the life
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Gets bullet.
     *
     * @return the bullet
     */
    public int getBullet() {
        return bullet;
    }

    /**
     * Sets bullet.
     *
     * @param bullet the bullet
     */
    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    private boolean isValidPosition(Point2D position2) {
        int minX = 1;
        int maxX = App.FRAME_HEIGHT;
        int minY = 1;
        int maxY = App.FRAME_HEIGHT;

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

    /**
     * Is valid point boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isValidPoint(int point) {
        return point >= 0;
    }

    /**
     * Is valid health boolean.
     *
     * @param health the health
     * @return the boolean
     */
    public boolean isValidHealth(int health) {
        return health > 0 && health <= 4;
    }

    /**
     * Is valid movement speed boolean.
     *
     * @param movementSpeed the movement speed
     * @return the boolean
     */
    public boolean isValidMovementSpeed(int movementSpeed) {
        return movementSpeed > 0 && movementSpeed <= 3;
    }

    /**
     * Is valid bullet speed boolean.
     *
     * @param bulletSpeed the bullet speed
     * @return the boolean
     */
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

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    @Override
    public void shoot(BaseTank tank) {
    }

    /**
     * Take damage.
     *
     * @param damage the damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Add points.
     *
     * @param score the score
     */
    public void addPoints(int score) {
        this.point += score;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Is display boolean.
     *
     * @return the boolean
     */
    public boolean isDisplay() {
        return isDisplay;
    }

    /**
     * Sets display.
     *
     * @param display the display
     */
    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    /**
     * Is shooting boolean.
     *
     * @return the boolean
     */
    public boolean isShooting() {
        return bulletList.isEmpty();
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

    public int clampToBounds(int value) {
        return Math.max(0, Math.min(App.FRAME_HEIGHT - App.FRAME_HEIGHT / 13, value));
    }
}
