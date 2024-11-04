package main.java.model.powerups;

import java.util.Random;

public enum Type {
    GRENADE,
    HELMET,
    SHOVEL,
    STAR,
    TANK,
    TIMER;

    private static final Random random = new Random();

    public static Type getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
