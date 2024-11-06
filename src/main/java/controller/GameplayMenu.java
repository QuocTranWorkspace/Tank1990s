package main.java.controller;

import main.java.App;
import main.java.service.GameplayManager;
import main.java.service.TimerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameplayMenu extends BaseScene {
    public static JPanel pausePanel = new JPanel();

    public GameplayMenu() {
        sceneIndex = 2;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameLoop = TimerManager.getSharedTimer();
        gameLoop.addActionListener(this);
        setFocusable(true); // Make this component focusable
        addKeyListener(this); // Register the key listener

        initPanels();
    }

    public static void togglePause(JPanel panel) {
        panel.setVisible(!pausePanel.isVisible());
        if (TimerManager.getSharedTimer().isRunning()) {
            TimerManager.getSharedTimer().stop();
        } else {
            TimerManager.getSharedTimer().start();
        }
    }

    private void setUpPausePanel(JPanel pausePanel) {
        pausePanel.removeAll();

        pausePanel.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 4));
        pausePanel.setBackground(new Color(40, 40, 40));
        pausePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));
        pausePanel.setVisible(false);

        JLabel titleLabel = new JLabel("Paused", SwingConstants.CENTER);
        titleLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pausePanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        pausePanel.add(titleLabel);

        JButton continueButton = createPauseButton("Continue", pausePanel);
        JButton saveButton = createPauseButton("Save", pausePanel);
        JButton exitButton = createPauseButton("Exit", pausePanel);

        pausePanel.add(Box.createVerticalGlue());
        pausePanel.add(continueButton);
        pausePanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        pausePanel.add(saveButton);
        pausePanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        pausePanel.add(exitButton);
        pausePanel.add(Box.createVerticalGlue());

        pausePanel.revalidate();
        pausePanel.repaint();
    }

    private JButton createPauseButton(String text, JPanel pausePanel) {
        JButton button = new JButton(text);
        button.setFont(tankFont.deriveFont(Font.PLAIN, (float) FRAME_HEIGHT / 45));
        button.setBackground(new Color(80, 80, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160), 1));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(actionEvent -> {
            switch (text) {
                case "Continue" -> togglePause(pausePanel);
                case "Save" -> {
                    togglePause(pausePanel);
                    App.sceneManager.loadScene(0);
                }
                case "Exit" -> {
                    togglePause(pausePanel);
                    App.sceneManager.loadScene(0);
                    this.gameLoop.stop();
                    this.gameLoop.removeActionListener(this);
                }
                default -> {
                    //
                }
            }
        });
        return button;
    }

    private void initPanels() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));

        int sidebarWidth = (FRAME_WIDTH - FRAME_HEIGHT) / 2;

        // Left Sidebar
        JPanel leftSidebar = new JPanel();
        leftSidebar.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        leftSidebar.setBackground(Color.GRAY);

        JButton pauseButton = new JButton("| |");
        pauseButton.setFont(new Font("Arial", Font.BOLD, FRAME_HEIGHT / 30 - FRAME_HEIGHT / 60));
        pauseButton.setPreferredSize(new Dimension(FRAME_HEIGHT / 30, FRAME_HEIGHT / 30));
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pauseButton.addActionListener(actionEvent -> togglePause(pausePanel));
        leftSidebar.add(pauseButton);

        // Main Panel
        JPanel mainPanel;
        try {
            mainPanel = new GameplayManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mainPanel.setPreferredSize(new Dimension(FRAME_HEIGHT, FRAME_HEIGHT));
        mainPanel.setBackground(Color.BLACK);

        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        // Right Sidebar
        JPanel rightSidebar = new JPanel();
        rightSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        rightSidebar.setBackground(Color.GRAY);

        setUpPausePanel(pausePanel);

        containerPanel.add(leftSidebar);
        containerPanel.add(mainPanel);
        containerPanel.add(rightSidebar);

        add(containerPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(pausePanel);
    }


    private void requestFocusOnGameplayManager() {
        for (Component component : ((JPanel) getComponent(0)).getComponents()) {
            if (component instanceof GameplayManager) {
                component.requestFocusInWindow();
                break;
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    public void drawComponents(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        /**/
    }

    @Override
    public void keyPressed(KeyEvent e) {
        requestFocusOnGameplayManager();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /**/
    }


}
