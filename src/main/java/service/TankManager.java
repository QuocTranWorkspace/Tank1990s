package main.java.service;

import main.java.App;
import main.java.model.Point2D;
import main.java.model.tanks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Tank manager.
 */
public class TankManager {
    private final List<EnemyTank> tankList = new ArrayList<>();

    /**
     * Instantiates a new Tank manager.
     *
     * @throws Exception the exception
     */
    public TankManager() throws Exception {
        int maxTank = 1;
        for (int i = 0; i < maxTank; i++) {
            Random random = new Random();
            int randomPosition = random.nextInt(2);
            Point2D[] spawnPoints = {new Point2D((int) (App.FRAME_HEIGHT / 27.9), (int) (App.FRAME_HEIGHT / 27.9)), new Point2D((int) (25 * App.FRAME_HEIGHT / 27.9), (int) (App.FRAME_HEIGHT / 27.9))};
            Point2D position = spawnPoints[randomPosition];
            TankType tankType = TankType.getRandomType();
            switch (tankType) {
                case FAST_TANK -> tankList.add(new FastTank(position));
                case BASIC_TANK -> tankList.add(new BasicTank(position));
                case ARMOR_TANK -> tankList.add(new ArmorTank(position));
                case POWER_TANK -> tankList.add(new PowerTank(position));
            }
        }
    }

    /**
     * Stop all timer.
     */
    public void stopAllTimer() {
        for (EnemyTank enemy : tankList) {
            enemy.stopAllTimer();
        }
    }

    /**
     * Gets tank list.
     *
     * @return the tank list
     */
    public List<EnemyTank> getTankList() {
        return tankList;
    }
}
