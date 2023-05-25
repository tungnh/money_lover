package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.service.dto.CurrencyDTO;
import d2.money.service.mapper.CurrencyMapper;
import d2.money.service.CurrencyService;
import javassist.NotFoundException;
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
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            CurrencyDTO currencyDTO = currencyMapper.toDto(currency);
            return Optional.of(currencyDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<CurrencyDTO> getAllCurrency() {
        return currencyMapper.toDto(currencyRepository.findAll());
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currencyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                currencyDto.setCreatedBy(user.getUsername());
                currencyDto.setLastModifiedBy(user.getUsername());
            }
        }
        currencyDto.setCreatedDate(new Date());
        currencyDto.setLastModifiedDate(new Date());
        Currency currency = currencyMapper.toEntity(currencyDto);
        currency = currencyRepository.save(currency);
        CurrencyDTO savedCurrencyDto = currencyMapper.toDto(currency);
        return savedCurrencyDto;
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDto) {
        Optional<Currency> currencyOptional = currencyRepository.findById(currencyDto.getId());
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            currencyDto.setId(currency.getId());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    currencyDto.setLastModifiedBy(user.getUsername());
                }
            }
            currencyDto.setLastModifiedDate(new Date());
            currency = currencyMapper.toEntity(currencyDto);
            currency = currencyRepository.save(currency);
            CurrencyDTO updatedCurrencyDto = currencyMapper.toDto(currency);
            return updatedCurrencyDto;
        } else {
            try {
                throw new NotFoundException("Currency not found with ID: " + currencyDto.getId());
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(int id) {
        currencyRepository.deleteById(id);
    }
}
