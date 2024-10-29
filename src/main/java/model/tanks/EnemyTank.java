package main.java.model.tanks;

import main.java.model.Point2D;

public class EnemyTank extends BaseTank {
    Point2D position;

    public EnemyTank(String name, Point2D position, int point, int health, int movementSpeed, int bulletSpeed,
            String description) throws Exception {
        super(name, position, point, health, movementSpeed, bulletSpeed, description);
    }
}
