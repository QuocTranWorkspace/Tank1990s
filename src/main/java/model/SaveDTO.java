package main.java.model;

public class SaveDTO {
    private String name;
    private int score;
    private String date;

    public SaveDTO(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
