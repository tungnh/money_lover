package d2.money.service;

import d2.money.service.dto.WalletDTO;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    Optional<WalletDTO> findById(int id);
    List<WalletDTO> findAll();
    List<WalletDTO> findByCurrencyId(int id);
    WalletDTO save(WalletDTO walletRequest);
    WalletDTO update(WalletDTO walletRequest);
    void delete(int id);
}