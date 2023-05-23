package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.service.dto.CurrencyRequest;
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
    public Currency findById(int id) {
        return currencyRepository.findById(id);
    }

    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency save(CurrencyRequest currency) {
        Currency currencyNew=new Currency();
        currencyNew.setName(currency.getName());
        currencyNew.setCode(currency.getCode());
        currencyNew.setSymbol(currency.getSymbol());
        currencyNew.setTransfer(currency.getTransfer());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user=userRepository.findByName(userDetails.getUsername());
            if (user!=null){
                currencyNew.setCreatedBy(user.getName());
                currencyNew.setLastModifiedBy(user.getName());
            }
        }
        currencyNew.setCreatedDate(new Date());
        currencyNew.setLastModifiedDate(new Date());
        return currencyRepository.save(currencyNew);
    }

    @Override
    public Currency update(CurrencyRequest currency) {
        Currency currencyOld=currencyRepository.findById(currency.getId());
        currencyOld.setName(currency.getName());
        currencyOld.setCode(currency.getCode());
        currencyOld.setSymbol(currency.getSymbol());
        currencyOld.setTransfer(currency.getTransfer());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user=userRepository.findByName(userDetails.getUsername());
            if (user!=null){
                currencyOld.setLastModifiedBy(user.getName());
            }
        }
        currencyOld.setLastModifiedDate(new Date());
        return currencyRepository.save(currencyOld);
    }


    @Override
    public void delete(int id) {
      currencyRepository.delete(currencyRepository.findById(id));
    }

    @Override
    public Currency findByCurrencyId(int id) {
        return currencyRepository.findById(id);
    }
}
