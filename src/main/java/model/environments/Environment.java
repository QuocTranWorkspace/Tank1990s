package main.java.model.environments;

public interface Environment {
    int getX();

    int getY();

    boolean isColliding(int x, int y);
}