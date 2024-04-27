package by.bsuir.kostyademens.tennisscoreboard.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlayerScore {
    private int game;
    private int set;
    private int tieBreakPoint;
    Point point = Point.LOVE;


    public void winPoint() {
        switch (point) {
            case LOVE -> point = Point.FIFTEEN;
            case FIFTEEN -> point = Point.THIRTY;
            case THIRTY -> point = Point.FORTY;
        }
    }

    public void winGame() {
        this.game++;
    }

    public void winTieBreakPoint() {
        this.tieBreakPoint++;
    }

    public void winSet() {
        this.set++;
    }
}
