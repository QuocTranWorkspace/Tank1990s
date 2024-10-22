package main.java.model.powerups;

import java.util.Random;

public enum Type {
    GRENADE, HELMET, SHOVEL, STAR, TANK, TIMER;

    public static Type getRandomType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];

    }
}
