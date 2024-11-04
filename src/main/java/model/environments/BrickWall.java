package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The type Brick wall.
 */
public class BrickWall extends Environment {
    private int health;

    public BrickWall(Point2D position) {
        this.health = 4;
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/brick.png"))).getImage());
        this.setDestroyable(true);
        this.setWalkable(false);
        this.setPosition(position);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
