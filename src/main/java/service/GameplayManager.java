package main.java.service;

import main.java.controller.BaseScene;
import main.java.controller.GameplayMenu;
import main.java.model.PlayerTank;
import main.java.model.Point2D;
import main.java.model.tanks.Directions;
import main.java.model.tanks.EnemyTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GameplayManager extends BaseScene implements ActionListener, KeyListener {
    private int currentLevel = 0;
    private List<EnemyTank> enemyTanks;
    private PlayerTank player;
    
    public GameplayManager() throws Exception {
        Timer gameLoop = TimerManager.getSharedTimer();
        player = new PlayerTank(new Point2D(10, 10));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        gameLoop.addActionListener(this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    public void drawComponents(Graphics g) {
        g.drawImage(player.getImage(), player.getPosition().getX(), player.getPosition().getY(),
               30, 30,
                null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("hello");
        if (e.getKeyCode() == KeyEvent.VK_S) {
            try {
                player.move(Directions.DOWN, 1);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameplayMenu.togglePause(GameplayMenu.pausePanel);
            if (TimerManager.getSharedTimer().isRunning()) {
                TimerManager.getSharedTimer().stop();
            } else {
                TimerManager.getSharedTimer().start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
