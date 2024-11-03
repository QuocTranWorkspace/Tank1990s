package main.java.service;

import main.java.model.powerups.Tank;
import main.java.model.tanks.EnemyTank;

import java.util.ArrayList;
import java.util.List;

public class TankManager {

    private final int MAX_TANK = 16;
    private List<EnemyTank> tankList = new ArrayList<>();

    public TankManager() {
        for (int i = 0; i < MAX_TANK; i++) {

        }
    }
}
