package d2.money.service;

import d2.money.service.dto.CurrencyDTO;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Optional<CurrencyDTO> findById(int id);
    List<CurrencyDTO> getAllCurrency();
    CurrencyDTO save(CurrencyDTO currencyDto);
    CurrencyDTO update(CurrencyDTO currencyDto);
    void delete(int id);
}
