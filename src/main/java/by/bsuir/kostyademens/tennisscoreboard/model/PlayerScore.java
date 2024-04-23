package by.bsuir.kostyademens.tennisscoreboard.model;

import by.bsuir.kostyademens.tennisscoreboard.util.PointNumberUtil;
import lombok.*;


@Getter
@Setter
public class PlayerScore {
    private int points;
    private int games;
    private int sets;

    PointNumberUtil point = PointNumberUtil.LOVE;


    private void winPoint() {
        switch (point) {
            case LOVE -> point = PointNumberUtil.FIFTEEN;
            case FIFTEEN -> point = PointNumberUtil.THIRTY;
            case THIRTY -> point = PointNumberUtil.FORTY;
            case FORTY -> point = PointNumberUtil.ADVANTAGE;
        }
    }
}
