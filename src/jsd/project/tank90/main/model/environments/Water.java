package jsd.project.tank90.main.model.environments;

import jsd.project.tank90.main.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Water.
 */
public class Water extends Environment {
    /**
     * Instantiates a new Water.
     *
     * @param position the position
     */
    public Water(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/environment/water.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
