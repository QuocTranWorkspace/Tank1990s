package main.java.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StartMenu extends BaseScene implements KeyListener {

    public StartMenu() throws IOException {
        sceneIndex = 1;

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        setFocusable(true);
        playerImg = new ImageIcon(getClass().getResource("../../resource/img/flappybird.png")).getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
