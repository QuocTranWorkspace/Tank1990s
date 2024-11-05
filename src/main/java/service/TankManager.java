package main.java.service;

import main.java.App;
import main.java.model.Point2D;
import main.java.model.tanks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TankManager {
    private final List<EnemyTank> tankList = new ArrayList<>();
    private final Point2D[] spawnPoints = { new Point2D((int) (App.FRAME_HEIGHT / 27.9), (int) (App.FRAME_HEIGHT / 27.9)), new Point2D((int) (25 * App.FRAME_HEIGHT / 27.9), (int) (App.FRAME_HEIGHT / 27.9))};

    public TankManager() throws Exception {
        int maxTank = 1;
        for (int i = 0; i < maxTank; i++) {
            Random random = new Random();
            int randomPosition = random.nextInt(2);
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

    public void stopAllTimer() {
        for (EnemyTank enemy : tankList) {
            enemy.stopAllTimer();
        }
    }

    public List<EnemyTank> getTankList() {
        return tankList;
    }
}
