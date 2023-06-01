package d2.money.service;

import d2.money.service.dto.CurrencyDTO;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    Optional<CurrencyDTO> findById(int id);
    Optional<CurrencyDTO> findByName(String name);
    List<CurrencyDTO> getAllCurrency();
    CurrencyDTO save(CurrencyDTO currencyDto);
    CurrencyDTO update(CurrencyDTO currencyDto);
    double currencyConversion(double transfer,double receiving);
    void delete(int id);
}