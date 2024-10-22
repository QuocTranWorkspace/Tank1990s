package main.java.model.powerups;

public class PowerUps {
    private int x;
    private int y;
    private Type type;

    public PowerUps(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;

    }

    public void activate(Tank tank) {
        switch (type) {
            case GRENADE:
                new Grenade();
            case HELMET:
                new Helmet(tank);
            case SHOVEL:
                new Shovel();
            case STAR:
                new Star();
            case TANK:
                new Tank();
            case TIMER:
                new Timer();
                break;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY() {
        this.y = y;
    }

}
