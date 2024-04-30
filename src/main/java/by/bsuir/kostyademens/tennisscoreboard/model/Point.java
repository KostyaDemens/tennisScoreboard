package by.bsuir.kostyademens.tennisscoreboard.model;


import lombok.Getter;

@Getter
public enum Point {
    LOVE("0", 0), FIFTEEN("15", 15),
    THIRTY("30", 30), FORTY("40", 40),
    ADVANTAGE("AD", 50);

    private final String score;

    private final int numericValue;

    Point(String score, int numericValue) {
        this.score = score;
        this.numericValue = numericValue;
    }

    @Override
    public String toString() {
        return score;
    }
}
