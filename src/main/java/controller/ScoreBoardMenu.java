package main.java.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ScoreBoardMenu extends BaseScene {
    public ScoreBoardMenu() {
        sceneIndex = 3;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameLoop = new Timer(1000 / 90, this);
        setFocusable(true);
    }

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
