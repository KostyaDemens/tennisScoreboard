package by.bsuir.kostyademens.tennisscoreboard.model;

import lombok.*;


@Getter
@Setter
public class PlayerScore {
    private int games;
    private int sets;
    Point point = Point.LOVE;


    public void winPoint() {
        switch (point) {
            case LOVE -> point = Point.FIFTEEN;
            case FIFTEEN -> point = Point.THIRTY;
            case THIRTY -> point = Point.FORTY;
            case FORTY -> point = Point.ADVANTAGE;
        }
    }
}
