package d2.money.service;

import d2.money.service.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {
    CurrencyDTO findById(int id);
    List<CurrencyDTO> getAllCurrency();

    CurrencyDTO save(CurrencyDTO currencyDto);

    CurrencyDTO update(CurrencyDTO currencyDto);

    void delete(int id);

    CurrencyDTO findByCurrencyId(int id);
}
