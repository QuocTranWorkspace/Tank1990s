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
    private final transient Image title = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/gameScreen/start_screen.png"))).getImage();

    public StartMenu() throws IOException {
        sceneIndex = 1;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);
        setUpGUI();
    }

    private void setUpGUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);
        this.add(Box.createVerticalGlue());

        JButton playButton = new JButton("Play");
        customizeButton(playButton);
        playButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(0x00A86B));
        playButton.setOpaque(true);  // Ensure the background color is applied
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFocusPainted(false);
        playButton.addActionListener(actionEvent -> App.sceneManager.loadScene(1));

        JButton exitButton = new JButton("Exit");
        customizeButton(exitButton);
        exitButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0xB22222));
        exitButton.setOpaque(true);  // Ensure the background color is applied
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(actionEvent -> App.sceneManager.closeApp());

        this.add(Box.createVerticalGlue());
        this.add(playButton);
        this.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        this.add(exitButton);
        this.add(Box.createVerticalGlue());

        this.revalidate();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(title, FRAME_WIDTH / 4, FRAME_HEIGHT / 8, FRAME_WIDTH / 2, FRAME_HEIGHT / 4, null);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
