package d2.money.service.impl;

import d2.money.domain.User;
import d2.money.domain.Wallet;
import d2.money.repository.UserRepository;
import d2.money.repository.WalletRepository;
import d2.money.service.dto.WalletDTO;
import d2.money.service.WalletService;
import d2.money.service.mapper.WalletMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImp implements WalletService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper walletMapper;

    public WalletServiceImp(WalletRepository walletRepository, UserRepository userRepository, WalletMapper walletMapper) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.walletMapper = walletMapper;
    }

    @Override
    public List<WalletDTO> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                return walletMapper.toDto(walletRepository.findByUserId(user.getId()));
            }
        }
        return null;
    }

    @Override
    public List<WalletDTO> findByCurrencyId(int id) {
        return walletMapper.toDto(walletRepository.findByCurrencyId(id));
    }

    @Override
    public WalletDTO save(WalletDTO walletRequest) {
        Wallet entity = walletMapper.toEntity(walletRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                entity.setUserId(user.getId());
            }
        }
        entity.setImage(walletRequest.getImage());
        entity.setCurrencyId(walletRequest.getCurrencyId());
        entity.setCreateDate(new Date());
        walletRepository.save(entity);
        WalletDTO walletDTO = walletMapper.toDto(entity);
        return walletDTO;
    }

    @Override
    public WalletDTO update(WalletDTO walletRequest) {
        Wallet wallet = walletMapper.toEntity(walletRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                wallet.setUserId(user.getId());
            }
        }
        wallet.setBudgetList(walletRequest.getBudgetList());
        wallet.setCurrencyId(walletRequest.getCurrencyId());
        wallet.setCreateDate(new Date());
        walletRepository.save(wallet);
        WalletDTO walletDTO = walletMapper.toDto(wallet);
        return walletDTO;
    }

    @Override
    public void delete(int id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Optional<WalletDTO> findById(int id) {
        return walletRepository.findById(id).map(walletMapper::toDto);
    }
}