package d2.money.service;

import d2.money.service.dto.WalletDTO;

import java.util.List;
import java.util.Optional;

public interface WalletService {
    List<WalletDTO> getAllWallet();
    WalletDTO save(WalletDTO walletRequest);
    WalletDTO update(WalletDTO walletRequest);
    void delete(int id);
    Optional<WalletDTO> findByWalletId(int id);
}
