package d2.money.service.util;

import d2.money.domain.Wallet;
import d2.money.service.dto.WalletRequest;

import java.util.List;

public interface WalletService {

    List<Wallet> getAllWallet();

    Wallet save(WalletRequest walletRequest);

    Wallet update(WalletRequest walletRequest);

    void delete(int id);

    Wallet findByWalletId(int id);

}
