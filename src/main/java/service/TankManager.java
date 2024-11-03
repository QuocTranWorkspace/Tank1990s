package main.java.service;

import main.java.App;
import main.java.model.Point2D;
import main.java.model.tanks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TankManager {
    private final List<EnemyTank> tankList = new ArrayList<>();

    public TankManager() throws Exception {
        int maxTank = 16;
        for (int i = 0; i < maxTank; i++) {
            Random random = new Random();
            Point2D position = new Point2D(random.nextInt(App.FRAME_HEIGHT - 40) + 1, random.nextInt(App.FRAME_HEIGHT - 40) + 1);
            TankType tankType = TankType.getRandomType();
            switch (tankType) {
                case FAST_TANK -> tankList.add(new FastTank(position));
                case BASIC_TANK -> tankList.add(new BasicTank(position));
                case ARMOR_TANK -> tankList.add(new ArmorTank(position));
                case POWER_TANK -> tankList.add(new PowerTank(position));
            }
        }
    }

    public List<EnemyTank> getTankList() {
        return tankList;
    }
}
