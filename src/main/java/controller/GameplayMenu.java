package main.java.controller;

import main.java.App;
import main.java.model.SaveDTO;
import main.java.service.GameplayManager;
import main.java.service.TimerManager;
import main.java.utils.SaveGame;
import main.java.utils.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

/**
 * The type Gameplay menu.
 */
public class GameplayMenu extends BaseScene {
    /**
     * The constant pausePanel.
     */
    public static JPanel pausePanel = new JPanel();
    /**
     * The constant gameOverPanel.
     */
    public static JPanel gameOverPanel;
    /**
     * The High score value.
     */
    JLabel highScoreValue;
    /**
     * The Current score value.
     */
    JLabel currentScoreValue;
    /**
     * The Enemy count label.
     */
    JLabel enemyCountLabel;
    /**
     * The Health value label.
     */
    JLabel healthValueLabel;
    /**
     * The Level value label.
     */
    JLabel levelValueLabel;
    private List<SaveDTO> scoreBoard = SaveGame.loadScores();

    /**
     * Instantiates a new Gameplay menu.
     */
    public GameplayMenu() {
        sceneIndex = 2;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        gameLoop = TimerManager.getSharedTimer();
        gameLoop.addActionListener(this);
        setFocusable(true);
        addKeyListener(this);

        initPanels();
    }

    /**
     * Toggle pause.
     *
     * @param panel the panel
     */
    public static void togglePause(JPanel panel) {
        if (!panel.isVisible()) {
            SoundEffect.pauseSound();
        }
        panel.setVisible(!pausePanel.isVisible());
        if (TimerManager.getSharedTimer().isRunning()) {
            TimerManager.getSharedTimer().stop();
        } else {
            TimerManager.getSharedTimer().start();
        }
    }

    /**
     * Display game over panel.
     */
    public static void displayGameOverPanel() {
        gameOverPanel.setVisible(true);
        pausePanel.setVisible(false);
    }

    private static JLabel getInstructionsLabel() {
        JLabel instructionsLabel = new JLabel("<html>"
                + "<div style='text-align:center; font-family:" + "rockwell" + "; font-weight:bold; font-size:" + (FRAME_HEIGHT / 60) + "px; color:white; margin-left: " + (FRAME_HEIGHT / 30) + "'>"
                + "W - Move Up<br>"
                + "A - Move Left&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D - Move Right<br>"
                + "S - Move Down<br><br>"
                + "Space - Shoot<br><br>"
                + "Esc - Pause"
                + "</div></html>");
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return instructionsLabel;
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
        JButton saveButton = createPauseButton("  Save  ", pausePanel);
        JButton exitButton = createPauseButton("  Exit  ", pausePanel);

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
                case "  Save  " -> displayGameOverPanel();
                case "  Exit  " -> {
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
        leftSidebar.setLayout(new BoxLayout(leftSidebar, BoxLayout.Y_AXIS));
        leftSidebar.setPreferredSize(new Dimension(sidebarWidth, FRAME_HEIGHT));
        leftSidebar.setBackground(new Color(50, 50, 50));

        // Pause Button
        JButton pauseButton = getPauseButton();

        leftSidebar.add(Box.createVerticalStrut(20));
        leftSidebar.add(pauseButton);
        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 25));

        // Section: Instructions
        JLabel instructionsTitle = new JLabel("How to Play");
        instructionsTitle.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        instructionsTitle.setForeground(new Color(0xFFD700));
        instructionsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidebar.add(instructionsTitle);

        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JLabel instructionsLabel = getInstructionsLabel();
        leftSidebar.add(instructionsLabel);

        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JSeparator separator1 = new JSeparator();
        separator1.setBackground(Color.WHITE);
        separator1.setMaximumSize(new Dimension(sidebarWidth - 40, 1));
        leftSidebar.add(separator1);
        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 45));

        // Section: High Score
        JLabel highScoreTitle = new JLabel("High Score");
        highScoreTitle.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        highScoreTitle.setForeground(new Color(0xFFD700));
        highScoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidebar.add(highScoreTitle);

        highScoreValue = new JLabel("0");
        highScoreValue.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 45));
        highScoreValue.setForeground(Color.WHITE);
        highScoreValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidebar.add(highScoreValue);

        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JSeparator separator2 = new JSeparator();
        separator2.setBackground(Color.WHITE);
        separator2.setMaximumSize(new Dimension(sidebarWidth - 40, 1));
        leftSidebar.add(separator2);
        leftSidebar.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        // Section: Current Score
        JLabel currentScoreTitle = new JLabel("Current Score");
        currentScoreTitle.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        currentScoreTitle.setForeground(new Color(0xFFD700));
        currentScoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidebar.add(currentScoreTitle);

        currentScoreValue = new JLabel("0");
        currentScoreValue.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 45));
        currentScoreValue.setForeground(Color.WHITE);
        currentScoreValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSidebar.add(currentScoreValue);

        leftSidebar.add(Box.createVerticalGlue());

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
        rightSidebar.setBackground(Color.DARK_GRAY);
        rightSidebar.setLayout(new BoxLayout(rightSidebar, BoxLayout.Y_AXIS));

        // Section: Enemy Count
        JPanel enemyPanel = new JPanel();
        enemyPanel.setBackground(Color.DARK_GRAY);
        JLabel enemyIconLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/sidebar/enemy_left.png"))));
        enemyCountLabel = new JLabel("16");
        enemyCountLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        enemyCountLabel.setForeground(Color.WHITE);
        enemyPanel.add(enemyIconLabel);
        enemyPanel.add(enemyCountLabel);
        rightSidebar.add(enemyPanel);
        rightSidebar.add(Box.createVerticalStrut(20));

        // Section: Player Health
        JPanel healthPanel = new JPanel();
        healthPanel.setBackground(Color.DARK_GRAY);
        JLabel healthIconLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/player/health.png"))));
        healthValueLabel = new JLabel("2");
        healthValueLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        healthValueLabel.setForeground(Color.WHITE);
        healthPanel.add(healthIconLabel);
        healthPanel.add(healthValueLabel);
        rightSidebar.add(healthPanel);
        rightSidebar.add(Box.createVerticalStrut(20));

        // Section: Current Level
        JPanel levelPanel = new JPanel();
        levelPanel.setBackground(Color.DARK_GRAY);
        JLabel levelIconLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/sidebar/level.png"))));
        levelValueLabel = new JLabel("1");
        levelValueLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        levelValueLabel.setForeground(Color.WHITE);
        levelPanel.add(levelIconLabel);
        levelPanel.add(levelValueLabel);
        rightSidebar.add(levelPanel);
        rightSidebar.add(Box.createVerticalStrut(20));

        rightSidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Assemble and add panels
        setUpPausePanel(pausePanel);
        containerPanel.add(leftSidebar);
        containerPanel.add(mainPanel);
        containerPanel.add(rightSidebar);
        add(containerPanel, BorderLayout.CENTER);

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(pausePanel);

        setUpGameOverPanel();
        mainPanel.add(gameOverPanel);
    }

    private void setUpGameOverPanel() {
        gameOverPanel = new JPanel();
        gameOverPanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        gameOverPanel.setBackground(new Color(30, 30, 30));
        gameOverPanel.setPreferredSize(new Dimension(FRAME_HEIGHT / 2, FRAME_HEIGHT / 3));
        gameOverPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Game Over label
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 30));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverPanel.add(gameOverLabel);

        gameOverPanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JLabel finalScoreLabel = new JLabel("Your Score: " + currentScoreValue.getText());
        finalScoreLabel.setFont(tankFont.deriveFont(Font.PLAIN, (float) FRAME_HEIGHT / 35));
        finalScoreLabel.setForeground(Color.WHITE);
        finalScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameOverPanel.add(finalScoreLabel);

        gameOverPanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JTextField nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(FRAME_HEIGHT / 3, FRAME_HEIGHT / 20));
        nameField.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        gameOverPanel.add(nameField);

        gameOverPanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 20));

        // Save button
        JButton saveButton = new JButton("  Save  ");
        saveButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 45));
        saveButton.setBackground(new Color(50, 50, 50));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> {
            SaveGame.saveScore(nameField.getText(), currentScoreValue.getText());
            App.sceneManager.loadScene(0);
        });
        gameOverPanel.add(saveButton);

        gameOverPanel.add(Box.createVerticalStrut(FRAME_HEIGHT / 40));

        JButton exitBtn = new JButton("  Exit  ");
        exitBtn.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 45));
        exitBtn.setBackground(new Color(50, 50, 50));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.addActionListener(e -> App.sceneManager.loadScene(0));
        gameOverPanel.add(exitBtn);

        gameOverPanel.setVisible(false);
    }

    private JButton getPauseButton() {
        JButton pauseButton = new JButton("| |");
        pauseButton.setFont(new Font("Arial", Font.BOLD, FRAME_HEIGHT / 45));
        pauseButton.setBackground(new Color(30, 30, 30));
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        pauseButton.addActionListener(actionEvent -> togglePause(pausePanel));
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.revalidate();
        pauseButton.repaint();
        return pauseButton;
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
        if (!scoreBoard.isEmpty()) {
            highScoreValue.setText(String.valueOf(scoreBoard.getFirst().getScore()));
        } else {
            highScoreValue.setText(String.valueOf(0));
        }
        currentScoreValue.setText(String.valueOf(GameplayManager.score));
        healthValueLabel.setText(String.valueOf(GameplayManager.health));
        enemyCountLabel.setText(String.valueOf(GameplayManager.enemyLeft));
        levelValueLabel.setText(String.valueOf(GameplayManager.level));
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
