package d2.money.service.impl;

import d2.money.domain.Transaction;
import d2.money.repository.TransactionRepository;
import d2.money.service.*;
import d2.money.service.dto.*;
import d2.money.service.mapper.CategoryMapper;
import d2.money.service.mapper.TransactionMapper;
import d2.money.service.mapper.WalletMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImp implements TransactionService {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final CategoryService categoryService;
    private final WalletService walletService;
    private final CurrencyService currencyService;

    private final WalletMapper walletMapper;
    private final CategoryMapper categoryMapper;

    public TransactionServiceImp(TransactionMapper transactionMapper, TransactionRepository transactionRepository, CategoryService categoryService, WalletService walletService, CurrencyService currencyService, WalletMapper walletMapper, CategoryMapper categoryMapper) {
        this.transactionMapper = transactionMapper;
        this.transactionRepository = transactionRepository;
        this.categoryService = categoryService;
        this.walletService = walletService;
        this.currencyService = currencyService;
        this.walletMapper = walletMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Optional<TransactionDTO> findById(int id) {
        return transactionRepository.findById(id).map(transactionMapper::toDto);
    }

    @Override
    public List<TransactionDTO> findByWalletId(int id) {
        return transactionMapper.toDto(transactionRepository.findByWalletId(id));
    }

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        if (transactionDTO.getDay() == null) {
            transaction.setDay(new Date());
        }
        Optional<WalletDTO> optionalWalletTransfer = walletService.findById(transactionDTO.getWalletTransferId());
        WalletDTO walletTransfer = optionalWalletTransfer.get();
        if (transactionDTO.getReceivingWalletId() != null) {
            Optional<WalletDTO> optionalWalletReceiving = walletService.findById(transactionDTO.getReceivingWalletId());
            WalletDTO walletReceiving = optionalWalletReceiving.get();
            if (walletReceiving.getCurrencyId() == walletTransfer.getCurrencyId()) {
                walletReceiving.setBalance(walletReceiving.getBalance() + transactionDTO.getAmount());
                walletService.save(walletReceiving);
                transaction.setWallet(walletMapper.toEntity(walletReceiving));
            } else {
                Optional<CurrencyDTO> optionalCurrencyReceiving = currencyService.findById(walletReceiving.getCurrencyId());
                CurrencyDTO currencyReceiving = optionalCurrencyReceiving.get();
                Optional<CurrencyDTO> optionalCurrencyTransfer = currencyService.findById(walletTransfer.getCurrencyId());
                CurrencyDTO currencyTransfer = optionalCurrencyTransfer.get();
                double exchangeRate = currencyService.currencyConversion(currencyTransfer.getTransfer(), currencyReceiving.getTransfer());
                walletReceiving.setBalance(walletReceiving.getBalance() + (transactionDTO.getAmount() * exchangeRate));
                walletService.save(walletReceiving);
                transaction.setWallet(walletMapper.toEntity(walletReceiving));
                transaction.setAmount(transactionDTO.getAmount() * exchangeRate);
            }
            walletTransfer.setBalance(walletTransfer.getBalance() - transactionDTO.getAmount());
            walletService.save(walletTransfer);
        } else {
            Optional<CategoryDTO> categoryDTOOptional = categoryService.findById(transaction.getCategoryId());
            CategoryDTO categoryDTO = categoryDTOOptional.get();
            transaction.setCategory(categoryMapper.toEntity(categoryDTO));
            if (categoryDTO.isStatus()) {
                walletTransfer.setBalance(walletTransfer.getBalance() + transactionDTO.getAmount());
            } else {
                walletTransfer.setBalance(walletTransfer.getBalance() - transactionDTO.getAmount());
            }
            walletService.save(walletTransfer);
            transaction.setWallet(walletMapper.toEntity(walletTransfer));
        }
        transactionRepository.save(transaction);
        TransactionDTO dto = transactionMapper.toDto(transaction);
        return dto;
    }

    @Override
    public TransactionDTO update(TransactionDTO transactionDTO) {
        Optional<TransactionDTO> transactionOldOptional = findById(transactionDTO.getId());
        TransactionDTO transactionOld = transactionOldOptional.get();
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        Optional<WalletDTO> optionalWalletTransfer = walletService.findById(transactionDTO.getWalletTransferId());
        WalletDTO walletTransfer = optionalWalletTransfer.get();
        if (transactionDTO.getReceivingWalletId() != null) {
            Optional<WalletDTO> optionalWalletReceiving = walletService.findById(transactionDTO.getReceivingWalletId());
            WalletDTO walletReceiving = optionalWalletReceiving.get();
            if (walletReceiving.getCurrencyId() == walletTransfer.getCurrencyId()) {
                walletReceiving.setBalance((walletReceiving.getBalance() - transactionOld.getAmount()) + transactionDTO.getAmount());
                walletService.save(walletReceiving);
                transaction.setWallet(walletMapper.toEntity(walletReceiving));
            } else {
                Optional<CurrencyDTO> optionalCurrencyReceiving = currencyService.findById(walletReceiving.getCurrencyId());
                CurrencyDTO currencyReceiving = optionalCurrencyReceiving.get();
                Optional<CurrencyDTO> optionalCurrencyTransfer = currencyService.findById(walletTransfer.getCurrencyId());
                CurrencyDTO currencyTransfer = optionalCurrencyTransfer.get();
                double exchangeRate = currencyService.currencyConversion(currencyTransfer.getTransfer(), currencyReceiving.getTransfer());
                walletReceiving.setBalance((walletReceiving.getBalance() - (transactionOld.getAmount() * exchangeRate)) + (transactionDTO.getAmount() * exchangeRate));
                walletService.save(walletReceiving);
                transaction.setAmount(transactionDTO.getAmount() * exchangeRate);
                transaction.setWallet(walletMapper.toEntity(walletReceiving));
            }
            walletTransfer.setBalance((walletTransfer.getBalance() + transactionOld.getAmount()) - transactionDTO.getAmount());
            walletService.save(walletTransfer);
        } else {
            Optional<CategoryDTO> categoryDTOOptional = categoryService.findById(transaction.getCategoryId());
            CategoryDTO categoryDTO = categoryDTOOptional.get();
            transaction.setCategory(categoryMapper.toEntity(categoryDTO));
            if (categoryDTO.isStatus()) {
                if (transactionOld.getCategory().isStatus()) {
                    walletTransfer.setBalance((transactionOld.getWallet().getBalance() - transactionOld.getAmount()) + transactionDTO.getAmount());
                } else {
                    walletTransfer.setBalance((transactionOld.getWallet().getBalance() + transactionOld.getAmount()) + transactionDTO.getAmount());
                }
            } else {
                if (transactionOld.getCategory().isStatus()) {
                    walletTransfer.setBalance((transactionOld.getWallet().getBalance() - transactionOld.getAmount()) - transactionDTO.getAmount());
                } else {
                    walletTransfer.setBalance((transactionOld.getWallet().getBalance() + transactionOld.getAmount()) - transactionDTO.getAmount());
                }
            }
            walletService.save(walletTransfer);
            transaction.setWallet(walletMapper.toEntity(walletTransfer));
        }
        transactionRepository.save(transaction);
        TransactionDTO dto = transactionMapper.toDto(transaction);
        return dto;
    }

    @Override
    public List<TransactionDTO> findTransactionByWalletIdOrReceiveWalletId(int id) {
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date start = calendar.getTime();
        List<TransactionDTO> list = transactionMapper.toDto(transactionRepository.findTop3ByDayBetweenAndWalletIdOrderByDayDesc(start, end, id));
        return list;
    }

    @Override
    public List<TransactionDTO> findByDayBetweenAndWalletId(int id) {
        List<TransactionDTO> list = transactionMapper.toDto(transactionRepository.findByDayBetweenAndWalletId(id));
        return list;
    }

    @Override
    public void delete(int id) {
        Optional<TransactionDTO> transaction = findById(id);
        TransactionDTO transactionDTO = transaction.get();
        Optional<WalletDTO> walletDTO = walletService.findById(transactionDTO.getWalletTransferId());
        WalletDTO walletDTOUpdate = walletDTO.get();
        if (transactionDTO.getReceivingWalletId() == null) {
            if (transactionDTO.getCategory().isStatus()) {
                walletDTOUpdate.setBalance(walletDTOUpdate.getBalance() - transactionDTO.getAmount());
            } else {
                walletDTOUpdate.setBalance(walletDTOUpdate.getBalance() + transactionDTO.getAmount());
            }
            walletService.save(walletDTOUpdate);
        } else {
            Optional<WalletDTO> optionalReceiving = walletService.findById(transactionDTO.getReceivingWalletId());
            WalletDTO walletReceiving = optionalReceiving.get();
            if (walletReceiving.getCurrencyId() == walletDTOUpdate.getCurrencyId()) {
                walletReceiving.setBalance(walletReceiving.getBalance() - transactionDTO.getAmount());
            } else {
                walletReceiving.setBalance(walletReceiving.getBalance() - transactionDTO.getAmount());
            }
            walletService.save(walletReceiving);
            walletDTOUpdate.setBalance(walletDTOUpdate.getBalance() + transactionDTO.getAmount());
            walletService.save(walletDTOUpdate);
        }
        transactionRepository.deleteById(id);
    }
}