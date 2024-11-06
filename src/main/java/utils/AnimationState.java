package main.java.utils;

import main.java.model.Point2D;
import main.java.model.tanks.Directions;

public class AnimationState {
    private int step = 0;
    private Point2D location;
    private Directions direction;

    AnimationState(int x, int y, Directions direction) {
        this.location = new Point2D(x, y);
        this.direction = direction;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public void incrementStep() {
        this.step++;
    }
}
