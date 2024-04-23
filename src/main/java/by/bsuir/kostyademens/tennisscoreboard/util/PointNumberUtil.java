package by.bsuir.kostyademens.tennisscoreboard.util;


public enum PointNumberUtil {
    LOVE("0"), FIFTEEN("15"),
    THIRTY("30"), FORTY("40"),
    ADVANTAGE("AD");

    private final String score;

    PointNumberUtil(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return score;
    }
}
