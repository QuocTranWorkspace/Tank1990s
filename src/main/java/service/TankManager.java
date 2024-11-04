package main.java.service;

import main.java.model.Point2D;
import main.java.model.tanks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankManager {
    private final List<EnemyTank> tankList = new ArrayList<>();

    public TankManager() throws Exception {
        int maxTank = 1;
        for (int i = 0; i < maxTank; i++) {
            Random random = new Random();
            Point2D position = new Point2D(0, 0);
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
