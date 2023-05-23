package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.util.CurrencyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    public CurrencyServiceImp(CurrencyRepository currencyRepository, UserRepository userRepository) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }


    @Override
    public CurrencyDTO findById(int id) {
        return null;
    }

    @Override
    public List<CurrencyDTO> getAllCurrency() {
        return null;
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currency) {
        return null;
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currency) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public CurrencyDTO findByCurrencyId(int id) {
        return null;
    }
}
