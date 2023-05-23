package d2.money.service.util;

import d2.money.service.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {
    CurrencyDTO findById(int id);
    List<CurrencyDTO> getAllCurrency();

    CurrencyDTO save(CurrencyDTO currency);

    CurrencyDTO update(CurrencyDTO currency);

    void delete(int id);

    CurrencyDTO findByCurrencyId(int id);

}
