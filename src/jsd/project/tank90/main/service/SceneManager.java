package jsd.project.tank90.main.service;

import jsd.project.tank90.main.controller.GameplayMenu;
import jsd.project.tank90.main.controller.ScoreBoardMenu;
import jsd.project.tank90.main.controller.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Scene manager.
 */
public class SceneManager {
    private static final Logger LOGGER = Logger.getLogger(SceneManager.class.getName());
    /**
     * The constant tankFont.
     */
    public static Font tankFont;
    private final JFrame frame;
    private Component currentScene;

    /**
     * Instantiates a new Scene manager.
     *
     * @param frame the frame
     */
    public SceneManager(JFrame frame) {
        setUpFont();
        this.frame = frame;
        initializeScenes();
    }

    /**
     * Sets up font.
     */
    public static void setUpFont() {
        File fontSource = new File("src/jsd/project/tank90/common/resources/font/tank_font.ttf");

        if (fontSource.exists()) {
            try {
                SceneManager.tankFont = Font.createFont(Font.TRUETYPE_FONT, fontSource).deriveFont(16f);
            } catch (FontFormatException | IOException e) {
                Logger.getLogger(e.getMessage());
            }
        } else {
            LOGGER.severe("Font file does not exist at: " + fontSource.getAbsolutePath());
        }
    }

    private void initializeScenes() {
        try {
            StartMenu startMenu = new StartMenu();
            currentScene = startMenu;

            frame.add(currentScene);
            frame.revalidate();
            frame.repaint();
            startMenu.requestFocus();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error initializing scenes: ", ex);
        }
    }

    /**
     * Load scene.
     *
     * @param index the index
     */
    public void loadScene(int index) {
        frame.remove(currentScene);

        try {
            Component newScene = null;
            if (index == 0) {
                newScene = new StartMenu();
            } else if (index == 1) {
                newScene = new GameplayMenu();
            } else if (index == 2) {
                newScene = new ScoreBoardMenu();
            }
            frame.add(newScene);
            assert newScene != null;
            newScene.requestFocus();
            frame.revalidate();
            frame.repaint();

            currentScene = newScene;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading scene: ", ex);
        }
    }

    /**
     * Close app.
     */
    public void closeApp() {
        frame.dispose();
        System.exit(0);
    }
}
