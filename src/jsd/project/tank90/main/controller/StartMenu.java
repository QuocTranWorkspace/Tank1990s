package jsd.project.tank90.main.controller;

import jsd.project.tank90.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;

/**
 * The type Start menu.
 */
public class StartMenu extends BaseScene implements KeyListener {
    private final transient Image title = new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/gameScreen/start_screen.png"))).getImage();

    /**
     * Instantiates a new Start menu.
     *
     * @throws IOException the io exception
     */
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

        JButton playButton = new JButton("  Play  ");
        customizeButton(playButton);
        playButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(0x00A86B));
        playButton.setOpaque(true);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFocusPainted(false);
        playButton.addActionListener(actionEvent -> Main.sceneManager.loadScene(1));

        JButton scoreButton = new JButton("  Score ");
        customizeButton(scoreButton);
        scoreButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        scoreButton.setForeground(Color.WHITE);
        scoreButton.setBackground(new Color(0xFFD700));
        scoreButton.setOpaque(true);
        scoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreButton.setFocusPainted(false);
        scoreButton.addActionListener(actionEvent -> Main.sceneManager.loadScene(2));

        JButton exitButton = new JButton("  Exit  ");
        customizeButton(exitButton);
        exitButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0xB22222));
        exitButton.setOpaque(true);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(actionEvent -> Main.sceneManager.closeApp());

        this.add(Box.createVerticalGlue());
        this.add(playButton);
        this.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        this.add(scoreButton);
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
