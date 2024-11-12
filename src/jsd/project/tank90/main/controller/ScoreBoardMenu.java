package jsd.project.tank90.main.controller;

import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.model.SaveDTO;
import jsd.project.tank90.common.utils.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * The type Score board menu.
 */
public class ScoreBoardMenu extends BaseScene {
    private static final int FRAME_WIDTH = Main.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = Main.FRAME_HEIGHT;

    /**
     * Instantiates a new Score board menu.
     */
    public ScoreBoardMenu() {
        sceneIndex = 3;
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setFocusable(true);

        List<SaveDTO> highScores = SaveGame.loadScores();
        JScrollPane scoreboardPanel = createScoreboardPanel(highScores);
        JScrollPane scrollPane = new JScrollPane(scoreboardPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createEscapeButton() {
        JButton escapeButton = new JButton("Escape");

        escapeButton.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));

        escapeButton.setForeground(Color.WHITE);
        escapeButton.setBackground(new Color(70, 70, 70));
        escapeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        escapeButton.setFocusPainted(false);

        escapeButton.addActionListener(e -> Main.sceneManager.loadScene(0));

        escapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return escapeButton;
    }

    private JScrollPane createScoreboardPanel(List<SaveDTO> highScores) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(45, 45, 45));

        panel.add(createEscapeButton());

        panel.add(createHeaderLabel());

        for (SaveDTO save : highScores) {
            panel.add(createScoreEntryPanel(save));
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scrollPane.setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));

        return scrollPane;
    }

    private JLabel createHeaderLabel() {
        JLabel headerLabel = new JLabel("High Scores", SwingConstants.CENTER);
        headerLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 40));
        headerLabel.setForeground(new Color(255, 215, 0));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return headerLabel;
    }

    private JPanel createScoreEntryPanel(SaveDTO save) {
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridLayout(1, 3));
        entryPanel.setPreferredSize(new Dimension(800, 50));
        entryPanel.setBackground(new Color(60, 63, 65));
        entryPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20),
                BorderFactory.createLineBorder(new Color(100, 100, 100), 1, true)
        ));

        // Name label
        JLabel nameLabel = new JLabel(save.getName(), SwingConstants.CENTER);
        nameLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 50));
        nameLabel.setForeground(Color.LIGHT_GRAY);

        // Score label
        JLabel scoreLabel = new JLabel(String.valueOf(save.getScore()), SwingConstants.CENTER);
        scoreLabel.setFont(tankFont.deriveFont(Font.BOLD, (float) FRAME_HEIGHT / 50));
        scoreLabel.setForeground(Color.GREEN);

        // Date label
        JLabel dateLabel = new JLabel(save.getDate(), SwingConstants.CENTER);
        dateLabel.setFont(tankFont.deriveFont(Font.PLAIN, (float) FRAME_HEIGHT / 50));
        dateLabel.setForeground(Color.CYAN);

        entryPanel.add(nameLabel);
        entryPanel.add(scoreLabel);
        entryPanel.add(dateLabel);

        return entryPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
}
