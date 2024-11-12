package jsd.project.tank90.main.model.environments;

import jsd.project.tank90.main.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Tree.
 */
public class Tree extends Environment {
    /**
     * Instantiates a new Tree.
     *
     * @param position the position
     */
    public Tree(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../common/resources/img/environment/bush.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(true);
        this.setPosition(position);
    }
}
