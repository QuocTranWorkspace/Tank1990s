package main.java;

import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class App extends JFrame {

    public static final int FRAME_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int FRAME_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final JFrame frame = new JFrame();

    public static void prepareGUI() {
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        App.prepareGUI();
    }

}
