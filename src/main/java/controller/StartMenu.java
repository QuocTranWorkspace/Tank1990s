package main.java.controller;

import main.java.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;

public class StartMenu extends BaseScene implements KeyListener {

    private final transient Image title = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameScreen/StartScreen.png"))).getImage();

    public StartMenu() throws IOException {
        sceneIndex = 1;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);
        setUpGUI();
    }

    private void setUpGUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        // Create Play button
        JButton playButton = new JButton("Play");
        customizeButton(playButton);
        playButton.addActionListener(actionEvent ->
                App.sceneManager.loadScene(1)
        );

        // Create Exit button
        JButton exitButton = new JButton("Exit");
        customizeButton(exitButton);
        exitButton.addActionListener(actionEvent -> {
            App.sceneManager.closeApp();
        });

        this.add(Box.createVerticalGlue());
        this.add(playButton);
        this.add(Box.createVerticalStrut(20));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(title, FRAME_WIDTH / 4, 100, FRAME_WIDTH / 2, FRAME_HEIGHT / 4, null);
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
