package jsd.project.tank90.main.model.tanks;

/**
 * The type Tank function.
 */
public abstract class TankFunction {
    /**
     * Move.
     *
     * @param velocity the velocity
     * @throws Exception the exception
     */
    public abstract void move(int velocity) throws Exception;

    /**
     * Shoot.
     *
     * @param tank the tank
     */
    public abstract void shoot(BaseTank tank);
}
