package jsd.project.tank90.main.controller;

import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.service.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * The type Base scene.
 */
public abstract class BaseScene extends JPanel implements ActionListener, KeyListener {
    /**
     * The constant FRAME_WIDTH.
     */
// JFrame size
    static final int FRAME_WIDTH = Main.FRAME_WIDTH;
    /**
     * The Frame height.
     */
    static final int FRAME_HEIGHT = Main.FRAME_HEIGHT;
    /**
     * The Scene index.
     */
// Scene index for loading scene
    int sceneIndex;
    /**
     * The Tank font.
     */
// Tank font
    Font tankFont = SceneManager.tankFont;
    /**
     * The Player img.
     */
// Component's asset
    transient Image playerImg = null;
    /**
     * The Background img.
     */
    transient Image backgroundImg = null;

    /**
     * The Game loop.
     */
// Game timer
    transient Timer gameLoop = null;
    /**
     * The Is lose.
     */
    boolean isLose = false;
    /**
     * The Is first.
     */
    boolean isFirst = true;
    /**
     * The Is start.
     */
    /*
     * @Problem: Exist 2 or more time loops on initiation
     *
     * @Solution: Check if started to start the time loop, else top the time loop of
     * previous scene
     */
    boolean isStart = false;

    /**
     * Move.
     */
// Move method
    public void move() {
    }

    /**
     * Gets scene index.
     *
     * @return the scene index
     */
    public int getSceneIndex() {
        return sceneIndex;
    }

    /**
     * Sets scene index.
     *
     * @param sceneIndex the scene index
     */
    public void setSceneIndex(int sceneIndex) {
        this.sceneIndex = sceneIndex;
    }

    /**
     * Gets is start.
     *
     * @return the is start
     */
    public boolean getIsStart() {
        return isStart;
    }

    /**
     * Sets is start.
     *
     * @param isStart the is start
     */
    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
        if (isStart && gameLoop != null) {
            gameLoop.start();
        }
    }

    /**
     * Gets game loop.
     *
     * @return the game loop
     */
    public Timer getGameLoop() {
        return this.gameLoop;
    }

    /**
     * Customize button.
     *
     * @param button the button
     */
    protected void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(true);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Rockwell", Font.BOLD, 36));
    }
}