package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

/**
 * The type Water.
 */
public class Water extends Environment {
    public Water(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/water.png"))).getImage());
        this.setDestroyable(false);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
