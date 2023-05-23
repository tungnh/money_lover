package d2.money.service.mapper;

import d2.money.domain.Currency;
import d2.money.service.dto.CurrencyDTO;

import java.util.List;

public class CurrencyMapper implements EntityMapper<CurrencyDTO, Currency> {

    @Override
    public Currency toEntity(CurrencyDTO dto) {
        return null;
    }

    @Override
    public CurrencyDTO toDto(Currency entity) {
        return null;
    }

    @Override
    public List<Currency> toEntity(List<CurrencyDTO> dtoList) {
        return null;
    }

    @Override
    public List<CurrencyDTO> toDto(List<Currency> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Currency entity, CurrencyDTO dto) {

    }
}
