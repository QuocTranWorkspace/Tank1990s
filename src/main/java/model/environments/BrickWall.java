package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Brick wall.
 */
public class BrickWall extends Environment {
    /**
     * Instantiates a new Brick wall.
     *
     * @param position the position
     */
    public BrickWall(Point2D position) {
        this.setHealth(4);
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/brick.png"))).getImage());
        this.setDestroyable(true);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
