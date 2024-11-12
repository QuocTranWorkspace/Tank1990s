package jsd.project.tank90.main.model.tanks;

import java.util.Random;

/**
 * The enum Tank type.
 */
public enum TankType {
    /**
     * Basic tank tank type.
     */
    BASIC_TANK,
    /**
     * Fast tank tank type.
     */
    FAST_TANK,
    /**
     * Armor tank tank type.
     */
    ARMOR_TANK,
    /**
     * Power tank tank type.
     */
    POWER_TANK;

    private static final Random random = new Random();

    /**
     * Gets random type.
     *
     * @return the random type
     */
    public static TankType getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
