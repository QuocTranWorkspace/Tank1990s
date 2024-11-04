package main.java.utils;

import main.java.App;
import main.java.model.Point2D;
import main.java.model.environments.*;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelRenderer {
    List<Path> levelList = new ArrayList<>();
    List<Environment> map = new ArrayList<>();
    private final int level;

    public LevelRenderer(int level) {
        levelList = listFilesInResourceDirectory("level");
        this.level = level;
        drawMap();
    }

    public List<Path> listFilesInResourceDirectory(String folder) {
        URL resource = getClass().getResource("../../resource/" + folder);
        if (resource == null) {
            return List.of();
        }

        try (Stream<Path> paths = Files.walk(Paths.get(resource.toURI()))) {
            return paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public void drawMap() {
        Path path = this.levelList.get(level);
        try (InputStream inputStream = new FileInputStream(path.toString())) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

                String line;
                int currentX = 0;
                int currentY = 0;
                while ((line = reader.readLine()) != null) {
                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        Environment environment = null;
                        switch (c) {
                            case '#' -> environment = new BrickWall(new Point2D(currentX * (App.FRAME_HEIGHT /26), currentY * (App.FRAME_HEIGHT /26)));
                            case '@' -> environment = new SteelWall(new Point2D(currentX * (App.FRAME_HEIGHT /26), currentY * (App.FRAME_HEIGHT /26)));
                            case '%' -> environment = new Tree(new Point2D(currentX * (App.FRAME_HEIGHT /26), currentY * (App.FRAME_HEIGHT /26)));
                            case '~' -> environment = new Water(new Point2D(currentX * (App.FRAME_HEIGHT /26), currentY * (App.FRAME_HEIGHT /26)));
                            case '-' -> environment = new Ice(new Point2D(currentX * (App.FRAME_HEIGHT /26), currentY * (App.FRAME_HEIGHT /26)));
                        }
                        currentX++;
                        map.add(environment);
                    }
                    currentX = 0;
                    currentY++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Environment> getMap() {
        return map;
    }

    public void setMap(List<Environment> map) {
        this.map = map;
    }
}
