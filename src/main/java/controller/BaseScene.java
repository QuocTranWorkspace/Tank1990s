package main.java.controller;

import main.java.App;
import main.java.service.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public abstract class BaseScene extends JPanel implements ActionListener, KeyListener {
    // JFrame size
    static final int FRAME_WIDTH = App.FRAME_WIDTH;
    static final int FRAME_HEIGHT = App.FRAME_HEIGHT;
    static final int GRAVITY = 1;
    // Scene index fo loading scene
    int sceneIndex;
    // Tank font
    Font tankFont = SceneManager.getTankFont();
    // Physics 2D
    int velocityX = 3;
    int velocityY = 0;
    // Component's asset
    transient Image playerImg = null;
    transient Image backgroundImg = null;

    // Game timer
    transient Timer gameLoop = null;
    boolean isLose = false;
    boolean isFirst = true;
    /*
     * @Problem: Exist 2 or more time loops on initiation
     *
     * @Solution: Check if started to start the time loop, else top the time loop of
     * previous scene
     */
    boolean isStart = false;

    // Move method
    public void move() {
    }

    public int getSceneIndex() {
        return sceneIndex;
    }

    public void setSceneIndex(int sceneIndex) {
        this.sceneIndex = sceneIndex;
    }

    public boolean getIsStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
        if (isStart && gameLoop != null) {
            gameLoop.start();
        }
    }

    public Timer getGameLoop() {
        return this.gameLoop;
    }

    protected void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Rockwell", Font.BOLD, 36));
    }
}