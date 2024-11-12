package jsd.project.tank90.main.model.environments;

import jsd.project.tank90.main.model.Point2D;

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
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/environment/border.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
