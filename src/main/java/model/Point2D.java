package main.java.model;

import main.java.App;

public final class Point2D {
    private int x;
    private int y;

    public Point2D(int x, int y) {
        this.x = clampToBounds(x);
        this.y = clampToBounds(y);
    }

    private int clampToBounds(int value) {
        return Math.max(0, Math.min(App.FRAME_HEIGHT, value));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = clampToBounds(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = clampToBounds(y);
    }
}
