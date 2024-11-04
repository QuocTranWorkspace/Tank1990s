package main.java;

import main.java.service.SceneManager;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static final int FRAME_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int FRAME_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final JFrame frame = new JFrame();

    public static final SceneManager sceneManager = new SceneManager(frame);

    public static void prepareGUI() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
        App.prepareGUI();
    }
}
