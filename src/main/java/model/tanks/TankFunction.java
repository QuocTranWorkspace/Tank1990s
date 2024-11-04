package main.java.model.tanks;

public abstract class TankFunction {
    public abstract void move() throws Exception;

    public abstract void shoot(BaseTank tank);
}
