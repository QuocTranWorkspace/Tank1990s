package main.java.model.tanks;

/**
 * The type Tank function.
 */
public abstract class TankFunction {
    /**
     * Move.
     *
     * @throws Exception the exception
     */
    public abstract void move() throws Exception;

    /**
     * Shoot.
     *
     * @param tank the tank
     */
    public abstract void shoot(BaseTank tank);
}
