package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.domain.Wallet;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.repository.WalletRepository;
import d2.money.service.dto.WalletDTO;
import d2.money.service.util.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public WalletServiceImp(WalletRepository walletRepository, UserRepository userRepository, CurrencyRepository currencyRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }


    @Override
    public List<WalletDTO> getAllWallet() {
        return null;
    }

    @Override
    public WalletDTO save(WalletDTO walletRequest) {
        return null;
    }

    @Override
    public WalletDTO update(WalletDTO walletRequest) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public WalletDTO findByWalletId(int id) {
        return null;
    }
}
