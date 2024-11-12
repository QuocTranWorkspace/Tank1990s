package jsd.project.tank90.main.model.powerups;

import java.util.Random;

/**
 * The enum Type.
 */
public enum Type {
    /**
     * Grenade type.
     */
    GRENADE,
    /**
     * Helmet type.
     */
    HELMET,
    /**
     * Shovel type.
     */
    SHOVEL,
    /**
     * Star type.
     */
    STAR,
    /**
     * Tank type.
     */
    TANK,
    /**
     * Timer type.
     */
    TIMER;

    private static final Random random = new Random();

    /**
     * Gets random type.
     *
     * @return the random type
     */
    public static Type getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
