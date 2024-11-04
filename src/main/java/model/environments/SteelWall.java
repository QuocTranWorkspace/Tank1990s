package main.java.model.environments;

import main.java.model.Point2D;

import javax.swing.*;
import java.util.Objects;

public class SteelWall extends Environment {
    public SteelWall(Point2D position) {
        this.setImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../../resource/img/environment/stone.png"))).getImage());
        this.setDestroyable(true);
        this.setWalkable(false);
        this.setPosition(position);
    }
}
