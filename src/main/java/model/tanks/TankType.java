package main.java.model.tanks;

import java.util.Random;

public enum TankType {
    BASIC_TANK,
    FAST_TANK,
    ARMOR_TANK,
    POWER_TANK;

    private static final Random random = new Random();

    public static TankType getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
