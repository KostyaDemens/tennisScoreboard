package by.bsuir.kostyademens.tennisscoreboard.dto;

public class MatchDto {

    private Long id;

    private PlayerDto player1;
    private PlayerDto player2;

    public MatchDto(PlayerDto player1, PlayerDto player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayerDto getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDto player1) {
        this.player1 = player1;
    }

    public PlayerDto getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDto player2) {
        this.player2 = player2;
    }
}
