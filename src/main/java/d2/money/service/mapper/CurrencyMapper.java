package d2.money.service.mapper;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.repository.CurrencyRepository;
import d2.money.service.dto.CurrencyDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CurrencyMapper implements EntityMapper<CurrencyDTO, Currency> {

    private final CurrencyRepository currencyRepository;

    public CurrencyMapper(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    @Override
    public Currency toEntity(CurrencyDTO dto) {
        Currency currency = new Currency();
        Optional<Currency> currencyOptional = currencyRepository.findById(dto.getId());
        if (currencyOptional.isPresent()) {
            currency = currencyOptional.get();
        }
        currency.setName(dto.getName());
        currency.setCode(dto.getCode());
        currency.setTransfer(dto.getTransfer());
        currency.setSymbol(dto.getSymbol());
        currency.setCreatedBy(dto.getCreatedBy());
        currency.setCreatedDate(dto.getCreatedDate());
        currency.setLastModifiedBy(dto.getLastModifiedBy());
        currency.setLastModifiedDate(dto.getLastModifiedDate());
        return currency;
    }

    @Override
    public CurrencyDTO toDto(Currency entity) {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(entity.getId());
        currencyDTO.setName(entity.getName());
        currencyDTO.setCode(entity.getCode());
        currencyDTO.setTransfer(entity.getTransfer());
        currencyDTO.setSymbol(entity.getSymbol());
        return currencyDTO;
    }

    @Override
    public List<Currency> toEntity(List<CurrencyDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Currency> currencyList = new ArrayList<>(dtoList.size());
        for (CurrencyDTO currencyDTO : dtoList) {
            currencyList.add(toEntity(currencyDTO));
        }

        return currencyList;
    }

    @Override
    public List<CurrencyDTO> toDto(List<Currency> entityList) {
        if (entityList == null) {
            return null;
        }

        List<CurrencyDTO> currencyDTOList = new ArrayList<>(entityList.size());
        for (Currency currency : entityList) {
            currencyDTOList.add(toDto(currency));
        }

        return currencyDTOList;
    }

    @Override
    public void partialUpdate(Currency entity, CurrencyDTO dto) {
    }
}
