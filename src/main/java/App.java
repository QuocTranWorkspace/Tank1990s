package main.java;

import main.java.service.SceneManager;

import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class App extends JFrame {

    public static final int FRAME_WIDTH = 640;
    public static final int FRAME_HEIGHT = 480;

    private static final JFrame frame = new JFrame();

    public static final SceneManager sceneManager = new SceneManager(frame);

    public static void prepareGUI() {
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
        App.prepareGUI();
    }

}
