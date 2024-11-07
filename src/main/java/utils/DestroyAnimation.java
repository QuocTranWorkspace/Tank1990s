package main.java.utils;

import main.java.App;
import main.java.model.Bullet;
import main.java.model.tanks.Directions;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DestroyAnimation {
    private static final int ANIMATION_INTERVAL = 50;
    private java.util.List<Image> destroyImages = new ArrayList<>();

    private final java.util.List<AnimationState> activeAnimations = new ArrayList<>();

    public DestroyAnimation(java.util.List<Image> destroyImages) {
        this.destroyImages = destroyImages;
    }

    public void startDestroyAnimation(int x, int y, Directions direction) {
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

    public void drawAnimations(Graphics g, int explosionSize) {
        for (AnimationState animation : activeAnimations) {
            Image destroyImage = destroyImages.get(animation.getStep());

            int offsetX = - explosionSize / 4;
            int offsetY = - explosionSize / 4;

            int x = animation.getLocation().getX() + offsetX;
            int y = animation.getLocation().getY() + offsetY;

            g.drawImage(destroyImage, x, y, explosionSize, explosionSize, null);
        }
    }
}
