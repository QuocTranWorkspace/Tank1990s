package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Border.
 */
public class Border extends Environment {
    /**
     * Instantiates a new Border.
     *
     * @param position the position
     */
    public Border(Point2D position) {
        this.setHealth(0);
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/border.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
