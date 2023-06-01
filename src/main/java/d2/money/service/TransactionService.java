package d2.money.service;

import d2.money.service.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<TransactionDTO> findById(int id);

    List<TransactionDTO> findByWalletId(int id);

    TransactionDTO save(TransactionDTO transactionDTO);
    TransactionDTO update(TransactionDTO transactionDTO);

    List<TransactionDTO> findTransactionByWalletIdOrReceiveWalletId(int id);

    List<TransactionDTO> findByDayBetweenAndWalletId(int id);

    void delete(int id);
}