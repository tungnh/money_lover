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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
    public CurrencyDTO findById(int id) {
        Currency currency = currencyRepository.findById(id).get();
        return currencyMapper.toDto(currency);
    }

    @Override
    public List<CurrencyDTO> getAllCurrency() {
        List<Currency> currencyList=currencyRepository.findAll();
        return currencyMapper.toDto(currencyList);
    }

    @Override
    public CurrencyDTO save(CurrencyDTO currencyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser= userRepository.findUserByName(userDetails.getUsername());
            if (optionalUser.isPresent()){
                User user=optionalUser.get();
                currencyDto.setCreatedBy(user.getName());
                currencyDto.setLastModifiedBy(user.getName());
            }
        }
        currencyDto.setCreatedDate(new Date());
        currencyDto.setLastModifiedDate(new Date());
        Currency currency=currencyMapper.toEntity(currencyDto);
        currencyRepository.save(currency);
        return currencyDto;
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDto) {
        Currency currency=currencyRepository.findById(currencyDto.getId()).get();
        if (currency!=null){
            currencyDto.setId(currency.getId());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser= userRepository.findUserByName(userDetails.getUsername());
            if (optionalUser.isPresent()){
                User user=optionalUser.get();
                currencyDto.setLastModifiedBy(user.getName());
            }
        }
        currencyDto.setLastModifiedDate(new Date());
        return currencyMapper.toDto(currencyRepository.save(currencyMapper.toEntity(currencyDto)));
    }

    @Override
    public void delete(int id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public CurrencyDTO findByCurrencyId(int id) {
        return currencyMapper.toDto(currencyRepository.findById(id).get());
    }
}
