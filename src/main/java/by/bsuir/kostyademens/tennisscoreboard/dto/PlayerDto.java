package by.bsuir.kostyademens.tennisscoreboard.dto;

public class PlayerDto {
    private Long id;
    private String name;

    public PlayerDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
