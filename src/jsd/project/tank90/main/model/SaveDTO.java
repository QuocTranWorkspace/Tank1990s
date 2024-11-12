package jsd.project.tank90.main.model;

/**
 * The type Save dto.
 */
public class SaveDTO {
    private String name;
    private int score;
    private String date;

    /**
     * Instantiates a new Save dto.
     *
     * @param name  the name
     * @param score the score
     * @param date  the date
     */
    public SaveDTO(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
