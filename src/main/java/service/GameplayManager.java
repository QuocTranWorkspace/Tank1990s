package main.java.service;

import main.java.model.PlayerTank;
import main.java.model.tanks.EnemyTank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameplayManager extends JPanel implements ActionListener, KeyListener {
    private int currentLevel = 0;
    private List<EnemyTank> enemyTanks;
    private PlayerTank player;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
