package main.java.service;

import main.java.controller.StartMenu;
import main.java.utils.AccessingAllClassesInPackage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneManager {

    private Class<?>[] classes;
    private List<Component> sceneList = new ArrayList<>();
    private JFrame frame;
    private static int sceneCount = 0;
    private Component currentScene;
    private static final String START_TIMER = "setIsStart";
    private static final Logger LOGGER = Logger.getLogger(SceneManager.class.getName());

    static Font tankFont;

    public SceneManager(JFrame frame) {
        this.frame = frame;
        loadClasses();
        initializeScenes();
        setUpFont();
    }

    private void loadClasses() {
        try {
            classes = AccessingAllClassesInPackage.getFilteredClasses("main.java.controller", "BaseScene");
            quickSort(classes, 0, classes.length - 1);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading classes: ", ex);
        }
    }

    private void initializeScenes() {
        try {
            for (Class<?> clazz : classes) {
                Component comp = (Component) clazz.getDeclaredConstructor().newInstance();
                sceneList.add(comp);
                sceneCount++;
            }

            // Set up the initial scene
            StartMenu startMenu = new StartMenu();
            currentScene = startMenu;
            invokeMethod(currentScene, START_TIMER, true);

            frame.add(currentScene);
            frame.revalidate();
            frame.repaint();
            startMenu.requestFocus();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error initializing scenes: ", ex);
        }
    }

    public void loadScene(int index) {
        if (index >= sceneList.size()) {
            LOGGER.log(Level.WARNING, "Scene index out of bounds: {0}", index);
            return;
        }

        frame.remove(currentScene);
        invokeMethod(currentScene, START_TIMER, false);

        try {
            Component newScene = sceneList.get(index);
            invokeMethod(newScene, START_TIMER, true);
            frame.add(newScene);
            newScene.requestFocus();
            frame.revalidate();
            frame.repaint();

            currentScene = newScene;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error loading scene: ", ex);
        }
    }

    private void invokeMethod(Component component, String methodName, boolean flag) {
        try {
            Method method = component.getClass().getMethod(methodName, boolean.class);
            method.invoke(component, flag);
        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Method invocation error: ", ex);
        }
    }

    public static void setUpFont() {
        File fontSource = new File("src/main/resource/font/tank_font.ttf");

        if (fontSource.exists()) {
            System.out.println("Font file exists: " + fontSource.getAbsolutePath());
            try {
                SceneManager.tankFont = Font.createFont(Font.TRUETYPE_FONT, fontSource).deriveFont(16f);
            } catch (FontFormatException | IOException e) {
                Logger.getLogger(e.getMessage());
            }
        } else {
            System.out.println("Font file does not exist at: " + fontSource.getAbsolutePath());
        }
    }


    public static int getSceneNum() {
        return sceneCount;
    }

    public void closeApp() {
        frame.dispose();
        System.exit(0);
    }

    // QuickSort and Partition logic for sorting classes based on `getSceneIndex`
    private static void quickSort(Class<?>[] arr, int low, int high) throws Exception {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Class<?>[] arr, int low, int high) throws Exception {
        Object o = arr[high].getDeclaredConstructor().newInstance();
        Method method = arr[high].getMethod("getSceneIndex");
        int pivot = (int) method.invoke(o);

        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            Object o1 = arr[j].getDeclaredConstructor().newInstance();
            Method method1 = arr[j].getMethod("getSceneIndex");
            int sceneIndex = (int) method1.invoke(o1);

            if (sceneIndex < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Class<?>[] arr, int i, int j) {
        Class<?> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static Font getTankFont() {
        return tankFont;
    }
}
