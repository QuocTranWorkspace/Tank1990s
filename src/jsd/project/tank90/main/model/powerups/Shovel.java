package jsd.project.tank90.main.model.powerups;

import jsd.project.tank90.main.model.Point2D;
import jsd.project.tank90.main.model.environments.BrickWall;
import jsd.project.tank90.main.model.environments.Environment;
import jsd.project.tank90.main.model.environments.SteelWall;
import jsd.project.tank90.main.service.GameplayManager;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;

/**
 * The type Shovel.
 */
public class Shovel {
    /**
     * The constant image.
     */
    public static final Image image = new ImageIcon(Objects.requireNonNull(Shovel.class.getResource("../../../common/resources/img/bonus/bonus_shovel.png"))).getImage();
    private static Shovel instance;
    /**
     * The Timer.
     */
    javax.swing.Timer timer;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Shovel getInstance() {
        if (instance == null) {
            instance = new Shovel();
        }
        return instance;
    }

    /**
     * Activate.
     */
    public void activate() {
        java.util.List<Environment> tempEnvironments = new ArrayList<>();
        for (Environment env : GameplayManager.levelRenderer.getMap()) {
            if (env instanceof BrickWall) {
                tempEnvironments.add(new BrickWall(env.getPosition()));
            } else {
                tempEnvironments.add(env);
            }
        }

        ListIterator<Environment> environmentIterator = GameplayManager.levelRenderer.getMap().listIterator();
        while (environmentIterator.hasNext()) {
            Environment environment = environmentIterator.next();
            if (environment instanceof BrickWall) {
                Point2D temp = environment.getPosition();
                environmentIterator.set(new SteelWall(temp));
            }
        }

        timer = new Timer(10000, e -> {
            ListIterator<Environment> newEnvironmentIterator = GameplayManager.levelRenderer.getMap().listIterator();
            int index = 0;

            while (newEnvironmentIterator.hasNext() && index < tempEnvironments.size()) {
                newEnvironmentIterator.next();
                newEnvironmentIterator.set(tempEnvironments.get(index++));
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Deactivate.
     */
    public void deactivate() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = null;
    }
}
