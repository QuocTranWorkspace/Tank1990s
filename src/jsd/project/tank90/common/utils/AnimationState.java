package jsd.project.tank90.common.utils;

import jsd.project.tank90.main.model.Point2D;
import jsd.project.tank90.main.model.tanks.Directions;

/**
 * The type Animation state.
 */
public class AnimationState {
    private int step = 0;
    private Point2D location;
    private Directions direction;

    /**
     * Instantiates a new Animation state.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     */
    AnimationState(int x, int y, Directions direction) {
        this.location = new Point2D(x, y);
        this.direction = direction;
    }

    /**
     * Gets step.
     *
     * @return the step
     */
    public int getStep() {
        return step;
    }

    /**
     * Sets step.
     *
     * @param step the step
     */
    public void setStep(int step) {
        this.step = step;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Directions getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    /**
     * Increment step.
     */
    public void incrementStep() {
        this.step++;
    }
}
