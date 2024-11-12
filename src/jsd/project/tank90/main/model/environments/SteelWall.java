package jsd.project.tank90.main.model.environments;

import jsd.project.tank90.main.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Steel wall.
 */
public class SteelWall extends Environment {
    /**
     * Instantiates a new Steel wall.
     *
     * @param position the position
     */
    public SteelWall(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/environment/stone.png"))).getImage());
        this.setDestroyable(true);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
