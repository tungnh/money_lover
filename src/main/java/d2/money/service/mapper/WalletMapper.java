package d2.money.service.mapper;

import d2.money.domain.Wallet;
import d2.money.service.dto.WalletDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class WalletMapper implements EntityMapper<WalletDTO, Wallet> {
    @Override
    public Wallet toEntity(WalletDTO dto) {
        Wallet wallet = new Wallet();
        wallet.setId(dto.getId());
        wallet.setBalance(dto.getBalance());
        wallet.setImage(dto.getImage());
        wallet.setName(dto.getName());
        wallet.setCreateDate(dto.getCreateDate());
        wallet.setCurrencyId(dto.getCurrencyId());
        wallet.setUserId(dto.getUserId());
        return wallet;
    }

    @Override
    public WalletDTO toDto(Wallet entity) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(entity.getId());
        walletDTO.setBalance(entity.getBalance());
        walletDTO.setImage(entity.getImage());
        walletDTO.setName(entity.getName());
        walletDTO.setCreateDate(entity.getCreateDate());
        walletDTO.setCurrencyId(entity.getCurrencyId());
        walletDTO.setUserId(entity.getUserId());
        return walletDTO;
    }

    @Override
    public List<Wallet> toEntity(List<WalletDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Wallet> walletList = new ArrayList<>(dtoList.size());
        for (WalletDTO walletDTO : dtoList) {
            walletList.add(toEntity(walletDTO));
        }
        return walletList;
    }

    @Override
    public List<WalletDTO> toDto(List<Wallet> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<WalletDTO> walletDTOList = new ArrayList<>(entityList.size());
        for (Wallet wallet : entityList) {
            walletDTOList.add(toDto(wallet));
        }
        return walletDTOList;
    }

    @Override
    public void partialUpdate(Wallet entity, WalletDTO dto) {

    }
}