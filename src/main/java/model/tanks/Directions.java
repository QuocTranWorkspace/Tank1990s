package main.java.model.tanks;

import java.util.Random;

public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private static final Random random = new Random();

    public static Directions getRandomType() {
        return values()[random.nextInt(values().length)];
    }
}
