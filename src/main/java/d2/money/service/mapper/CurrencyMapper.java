package d2.money.service.mapper;

import d2.money.domain.Currency;
import d2.money.service.dto.CurrencyDTO;

import java.util.List;

public class CurrencyMapper implements EntityMapper<Currency, CurrencyDTO> {

    @Override
    public CurrencyDTO toEntity(Currency dto) {
        return null;
    }

    @Override
    public Currency toDto(CurrencyDTO entity) {
        return null;
    }

    @Override
    public List<CurrencyDTO> toEntity(List<Currency> dtoList) {
        return null;
    }

    @Override
    public List<Currency> toDto(List<CurrencyDTO> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(CurrencyDTO entity, Currency dto) {

    }

}
