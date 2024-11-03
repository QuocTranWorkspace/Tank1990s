package main.java.controller;

import main.java.App;
import main.java.service.GameplayManager;
import main.java.service.TimerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The type Gameplay menu.
 */
public class GameplayMenu extends BaseScene {
    /**
     * The constant pausePanel.
     */
    public static JPanel pausePanel;

    /**
     * Instantiates a new Gameplay menu.
     */
    public GameplayMenu() {
        sceneIndex = 2;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameLoop = TimerManager.getSharedTimer();
        gameLoop.addActionListener(this);
        setFocusable(true); // Make this component focusable
        addKeyListener(this); // Register the key listener

        initPanels();
    }

    private void setUpPausePanel(JPanel pausePanel) {
        pausePanel.setPreferredSize(new Dimension(FRAME_WIDTH / 5, FRAME_HEIGHT / 4));
        pausePanel.setBackground(Color.BLACK);
        pausePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pausePanel.add(new JLabel("Paused", SwingConstants.CENTER));
        pausePanel.setVisible(false);
        pausePanel.setLayout(new BoxLayout(pausePanel, BoxLayout.Y_AXIS));

        // Add Buttons to Pause Panel
        JButton continueButton = new JButton("Continue");
        customizeButton(continueButton);
        continueButton.addActionListener(actionEvent -> {
            togglePause(pausePanel);
        });
        JButton playButton = new JButton("Save");
        customizeButton(playButton);
        playButton.addActionListener(actionEvent -> {
            togglePause(pausePanel);
            App.sceneManager.loadScene(0);
        });
        JButton exitButton = new JButton("Exit");
        customizeButton(exitButton);
        exitButton.addActionListener(actionEvent -> {
            togglePause(pausePanel);
            App.sceneManager.loadScene(0);
            this.gameLoop.stop();
            this.gameLoop.removeActionListener(this);
        });

        pausePanel.add(Box.createVerticalGlue());
        pausePanel.add(continueButton);
        pausePanel.add(Box.createVerticalStrut(20));
        pausePanel.add(playButton);
        pausePanel.add(Box.createVerticalStrut(20));
        pausePanel.add(exitButton);
        pausePanel.add(Box.createVerticalGlue());
    }

    private void initPanels() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));

        int sidebarWidth = (FRAME_WIDTH - FRAME_HEIGHT) / 2;

        // Left Sidebar
        JPanel leftSidebar = new JPanel();
        leftSidebar.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        leftSidebar.setBackground(Color.BLACK);

        JButton pauseButton = new JButton("| |");
        pauseButton.setPreferredSize(new Dimension(50, 50));
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
        mainPanel.setBackground(Color.DARK_GRAY);

        // Request focus on GameplayManager to capture key events
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

        // Right Sidebar
        JPanel rightSidebar = new JPanel();
        rightSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        rightSidebar.setBackground(Color.BLACK);

        // Pause Panel
        pausePanel = new JPanel();
        setUpPausePanel(pausePanel);

        containerPanel.add(leftSidebar);
        containerPanel.add(mainPanel);
        containerPanel.add(rightSidebar);

        // Add container panel to the main frame
        add(containerPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(pausePanel);
    }

    /**
     * Toggle pause.
     *
     * @param panel the panel
     */
    public static void togglePause(JPanel panel) {
        panel.setVisible(!pausePanel.isVisible());
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

    /**
     * Draw components.
     *
     * @param g the g
     */
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
