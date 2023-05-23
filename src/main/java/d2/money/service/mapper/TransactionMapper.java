package d2.money.service.mapper;

import d2.money.domain.Transaction;
import d2.money.service.dto.TransactionDTO;

import java.util.List;

public class TransactionMapper implements EntityMapper<TransactionDTO, Transaction> {
    @Override
    public Transaction toEntity(TransactionDTO dto) {
        return null;
    }

    @Override
    public TransactionDTO toDto(Transaction entity) {
        return null;
    }

    @Override
    public List<Transaction> toEntity(List<TransactionDTO> dtoList) {
        return null;
    }

    @Override
    public List<TransactionDTO> toDto(List<Transaction> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Transaction entity, TransactionDTO dto) {

    }
}