package d2.money.service.util;

import d2.money.domain.Currency;
import d2.money.service.dto.CurrencyRequest;

import java.util.List;

public interface CurrencyService {
    Currency findById(int id);
    List<Currency> getAllCurrency();

    Currency save(CurrencyRequest currency);

    Currency update(CurrencyRequest currency);

    void delete(int id);

    Currency findByCurrencyId(int id);

}
