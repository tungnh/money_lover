package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.mapper.CurrencyMapper;
import d2.money.service.CurrencyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;
    private final CurrencyMapper currencyMapper;

    public CurrencyServiceImp(CurrencyRepository currencyRepository, UserRepository userRepository, CurrencyMapper currencyMapper) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public Optional<CurrencyDTO> findById(int id) {
        return currencyRepository.findById(id).map(currencyMapper::toDto);
    }

    @Override
    public Optional<CurrencyDTO> findByName(String name) {
        return currencyRepository.findOneByName(name).map(currencyMapper::toDto);
    }

    @Override
    public List<CurrencyDTO> getAllCurrency() {
        return currencyMapper.toDto(currencyRepository.findAll());
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currencyDto) {
        Currency currency = currencyMapper.toEntity(currencyDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                currency.setCreatedBy(user.getUsername());
                currency.setLastModifiedBy(user.getUsername());
            }
        }
        currency.setCreatedDate(new Date());
        currency.setLastModifiedDate(new Date());
        currencyRepository.save(currency);
        CurrencyDTO savedCurrencyDto = currencyMapper.toDto(currency);
        return savedCurrencyDto;
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDto) {
        Currency currency = currencyMapper.toEntity(currencyDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                currency.setLastModifiedBy(user.getUsername());
            }
        }
        currency.setLastModifiedDate(new Date());
        currencyRepository.save(currency);
        CurrencyDTO updatedCurrencyDto = currencyMapper.toDto(currency);
        return updatedCurrencyDto;
    }

    @Override
    public double currencyConversion(double transfer, double receiving) {
        double exchangeRate = receiving / transfer;
        return exchangeRate;
    }

    @Override
    public void delete(int id) {
        currencyRepository.deleteById(id);
    }
}