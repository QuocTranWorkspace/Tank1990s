package main.java.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.*;

public class StartMenu extends BaseScene implements KeyListener {

    public StartMenu() throws IOException {
        sceneIndex = 1;

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        gameLoop = new Timer(1000 / 90, this);

        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 640, 480);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + (int) 100, 10, 35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
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
