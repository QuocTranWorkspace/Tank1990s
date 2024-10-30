package main.java.controller;

import main.java.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameplayMenu extends BaseScene {

    private JPanel leftSidebar, rightSidebar, mainPanel, pausePanel;
    private JButton pauseButton;

    public GameplayMenu() {
        sceneIndex = 2;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameLoop = new Timer(1000 / 90, this);
        setFocusable(true);

        initPanels();
    }

    private void initPanels() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));

        int mainPanelWidth = FRAME_HEIGHT;
        int sidebarWidth = (FRAME_WIDTH - FRAME_HEIGHT) / 2 ;

        // Left Sidebar
        leftSidebar = new JPanel();
        leftSidebar.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        leftSidebar.setBackground(Color.BLACK);

        pauseButton = new JButton("| |");
        pauseButton.setPreferredSize(new Dimension(50, 50));
        pauseButton.setBackground(Color.BLACK); // Inner color
        pauseButton.setForeground(Color.WHITE); // Text color
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // White border
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(actionEvent -> togglePause());
        leftSidebar.add(pauseButton);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(mainPanelWidth, FRAME_HEIGHT));
        mainPanel.setBackground(Color.DARK_GRAY);

        // Right Sidebar
        rightSidebar = new JPanel();
        rightSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        rightSidebar.setBackground(Color.BLACK);

        // Pause Panel
        pausePanel = new JPanel();
        pausePanel.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 6));
        pausePanel.setBackground(Color.BLACK);
        pausePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pausePanel.add(new JLabel("Paused", SwingConstants.CENTER));
        pausePanel.setVisible(false);

        containerPanel.add(leftSidebar);
        containerPanel.add(mainPanel);
        containerPanel.add(rightSidebar);

        // Add the container panel to the main frame
        add(containerPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(pausePanel);
    }

    private void togglePause() {
        pausePanel.setVisible(!pausePanel.isVisible());
        if (this.gameLoop.isRunning()) {
            gameLoop.stop();
        } else {
            gameLoop.start();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    public void drawComponents(Graphics g) {
        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
        System.out.println("hello");
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
