package jsd.project.tank90.common.utils;

import jsd.project.tank90.main.model.tanks.Directions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The type Destroy animation.
 */
public class DestroyAnimation {
    private static final int ANIMATION_INTERVAL = 50;
    private final java.util.List<AnimationState> activeAnimations = new ArrayList<>();
    private java.util.List<Image> destroyImages = new ArrayList<>();

    /**
     * Instantiates a new Destroy animation.
     *
     * @param destroyImages the destroy images
     */
    public DestroyAnimation(java.util.List<Image> destroyImages) {
        this.destroyImages = destroyImages;
    }

    /**
     * Start destroy animation.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     */
    public void startDestroyAnimation(int x, int y, Directions direction) {
        SoundEffect.fExplosionSound();
        AnimationState animation = new AnimationState(x, y, direction);
        activeAnimations.add(animation);

        Timer destroyAnimationTimer = new Timer(ANIMATION_INTERVAL, e -> {
            animation.incrementStep();  // Increment the step in AnimationState

            if (animation.getStep() >= destroyImages.size()) {
                ((Timer) e.getSource()).stop();
                activeAnimations.remove(animation);
            }
        });
        destroyAnimationTimer.start();
    }

    /**
     * Draw animations.
     *
     * @param g             the g
     * @param explosionSize the explosion size
     */
    public void drawAnimations(Graphics g, int explosionSize) {
        for (AnimationState animation : activeAnimations) {
            Image destroyImage = destroyImages.get(animation.getStep());

            int offsetX = -explosionSize / 4;
            int offsetY = -explosionSize / 4;

            int x = animation.getLocation().getX() + offsetX;
            int y = animation.getLocation().getY() + offsetY;

            g.drawImage(destroyImage, x, y, explosionSize, explosionSize, null);
        }
    }
}
