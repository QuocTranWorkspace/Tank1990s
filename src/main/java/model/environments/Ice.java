package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Ice.
 */
public class Ice extends Environment {
    /**
     * Instantiates a new Ice.
     *
     * @param position the position
     */
    public Ice(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/ice.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(true);
        this.setPosition(position);
    }
}
