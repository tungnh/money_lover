package d2.money.service.impl;

import d2.money.domain.Currency;
import d2.money.domain.User;
import d2.money.domain.Wallet;
import d2.money.repository.CurrencyRepository;
import d2.money.repository.UserRepository;
import d2.money.repository.WalletRepository;
import d2.money.service.dto.WalletRequest;
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
    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet save(WalletRequest walletRequest) {
        Wallet wallet = new Wallet();
        Currency currency = currencyRepository.findById(walletRequest.getCurrencyId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user=userRepository.findByName(userDetails.getUsername());
            if (user!=null){
                wallet.setUser(user);
            }
        }
        wallet.setName(walletRequest.getName());
        wallet.setBalance(walletRequest.getBalance());
        wallet.setImage(walletRequest.getImage());
        wallet.setCurrency(currency);
        wallet.setCreateDate(new Date());
        return walletRepository.save(wallet);
    }


    @Override
    public Wallet update(WalletRequest walletRequest) {
        Wallet oldWallet = walletRepository.findById(walletRequest.getId());
        Currency currency = currencyRepository.findById(walletRequest.getCurrencyId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user=userRepository.findByName(userDetails.getUsername());
            if (user!=null){
                oldWallet.setUser(user);
            }
        }
        oldWallet.setName(walletRequest.getName());
        oldWallet.setBalance(walletRequest.getBalance());
        oldWallet.setImage(walletRequest.getImage());
        oldWallet.setCurrency(currency);
        oldWallet.setCreateDate(new Date());
        return null;
    }


    @Override
    public void delete(int id) {
        Wallet wallet = walletRepository.findById(id);
        walletRepository.delete(wallet);
    }

    @Override
    public Wallet findByWalletId(int id) {
        return walletRepository.findById(id);
    }
}
