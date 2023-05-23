package d2.money.service.mapper;

import d2.money.domain.Currency;
import d2.money.service.dto.CurrencyRequest;

import java.util.List;

public class CurrencyMapper implements EntityMapper<Currency, CurrencyRequest> {


    @Override
    public CurrencyRequest toEntity(Currency dto) {
        return null;
    }

    @Override
    public Currency toDto(CurrencyRequest entity) {
        return null;
    }

    @Override
    public List<CurrencyRequest> toEntity(List<Currency> dtoList) {
        return null;
    }

    @Override
    public List<Currency> toDto(List<CurrencyRequest> entityList) {
        return null;
    }


}
