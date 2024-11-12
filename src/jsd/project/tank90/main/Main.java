package jsd.project.tank90.main;

import jsd.project.tank90.main.service.SceneManager;

import javax.swing.*;
import java.awt.*;

/**
 * The type App.
 */
public class Main extends JFrame {
    /**
     * The constant FRAME_WIDTH.
     */
    public static final int FRAME_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * The constant FRAME_HEIGHT.
     */
    public static final int FRAME_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final JFrame frame = new JFrame();

    /**
     * The constant sceneManager.
     */
    public static final SceneManager sceneManager = new SceneManager(frame);

    /**
     * Prepare gui.
     */
    public static void prepareGUI() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Main();
        Main.prepareGUI();
    }
}
