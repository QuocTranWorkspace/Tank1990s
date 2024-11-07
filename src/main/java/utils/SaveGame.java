package main.java.utils;

import main.java.model.SaveDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveGame {
    private static final String FILE_PATH = "src/main/resource/data.dat";
    private static final Logger LOGGER = Logger.getLogger(SaveGame.class.getName());

    public static void saveScore(String name, String score) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        SaveDTO saveDTO = new SaveDTO(name, Integer.parseInt(score), ft.format(dNow));
        String encodedEntry = encode(saveDTO.getName() + " " + saveDTO.getScore() + " " + saveDTO.getDate());
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(encodedEntry + System.lineSeparator());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    public static List<SaveDTO> loadScores() {
        List<SaveDTO> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String decodedLine = decode(line);
                String[] data = decodedLine.split(" ");
                SaveDTO loadedData = new SaveDTO(data[0], Integer.parseInt(data[1]), data[2]);
                scores.add(loadedData);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        scores.sort((entry1, entry2) -> {
            int score1 = entry1.getScore();
            int score2 = entry2.getScore();
            return Integer.compare(score2, score1);
        });

        return scores;
    }

    private static String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String decode(String data) {
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
