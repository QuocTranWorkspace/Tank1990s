package main.java.model;

import main.java.model.tanks.BaseTank;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends BaseTank {
    protected PlayerTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed, String description) throws Exception {
        super(name, position, point, health, movementSpeed, bulletSpeed, description);
    }
}
