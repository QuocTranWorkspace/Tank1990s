package main.java.model.environments;

import main.java.model.Point2D;

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
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/bush.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(true);
        this.setPosition(position);
    }
}
