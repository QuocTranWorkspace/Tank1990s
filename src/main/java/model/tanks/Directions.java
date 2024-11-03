package main.java.model.tanks;

import java.util.Random;

/**
 * The enum Directions.
 */
public enum Directions {
    /**
     * Up directions.
     */
    UP,
    /**
     * Down directions.
     */
    DOWN,
    /**
     * Left directions.
     */
    LEFT,
    /**
     * Right directions.
     */
    RIGHT;

    private static final Random random = new Random();

    /**
     * Gets random type.
     *
     * @return the random type
     */
    public static Directions getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
