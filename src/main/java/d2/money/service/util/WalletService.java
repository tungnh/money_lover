package d2.money.service.util;

import d2.money.service.dto.WalletDTO;

import java.util.List;

public interface WalletService {

    List<WalletDTO> getAllWallet();

    WalletDTO save(WalletDTO walletRequest);

    WalletDTO update(WalletDTO walletRequest);

    void delete(int id);

    WalletDTO findByWalletId(int id);

}
