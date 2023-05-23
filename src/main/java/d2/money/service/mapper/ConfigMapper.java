package d2.money.service.mapper;

import d2.money.domain.Config;
import d2.money.service.dto.ConfigDTO;

import java.util.List;

public class ConfigMapper implements EntityMapper<ConfigDTO, Config> {
    @Override
    public Config toEntity(ConfigDTO dto) {
        return null;
    }

    @Override
    public ConfigDTO toDto(Config entity) {
        return null;
    }

    @Override
    public List<Config> toEntity(List<ConfigDTO> dtoList) {
        return null;
    }

    @Override
    public List<ConfigDTO> toDto(List<Config> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Config entity, ConfigDTO dto) {

    }
}
