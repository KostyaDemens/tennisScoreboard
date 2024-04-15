package by.bsuir.kostyademens.tennisscoreboard.mapper;

import by.bsuir.kostyademens.tennisscoreboard.dto.PlayerDto;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapper {

    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
    PlayerDto toDto(Player player);
}
