package d2.money.service.mapper;

import d2.money.domain.Transaction;
import d2.money.service.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TransactionMapper implements EntityMapper<TransactionDTO, Transaction> {
    @Override
    public Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        if (!dto.getDay().isEmpty()){
            String dateString = dto.getDay();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(dateString);
                transaction.setDay(date);
            } catch (ParseException e) {
                System.out.println("Failed to convert the string to a date: " + e.getMessage());
            }
        }
        transaction.setId(dto.getId());
        transaction.setAmount(dto.getAmount());
        transaction.setImage(dto.getImage());
        transaction.setNote(dto.getNote());
        transaction.setWalletId(dto.getWalletTransferId());
        transaction.setReceiveWalletId(dto.getReceivingWalletId());
        transaction.setCategoryId(dto.getCategoryId());
        return transaction;
    }

    @Override
    public TransactionDTO toDto(Transaction entity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(entity.getId());
        transactionDTO.setAmount(entity.getAmount());
        transactionDTO.setImage(entity.getImage());
        transactionDTO.setNote(entity.getNote());
        transactionDTO.setDay(entity.getDay()+"");
        transactionDTO.setWalletTransferId(entity.getWalletId());
        transactionDTO.setReceivingWalletId(entity.getReceiveWalletId());
        transactionDTO.setCategoryId(entity.getCategoryId());
        transactionDTO.setCategory(entity.getCategory());
        transactionDTO.setWallet(entity.getWallet());
        return transactionDTO;
    }

    @Override
    public List<Transaction> toEntity(List<TransactionDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Transaction> transactionList = new ArrayList<>(dtoList.size());
        for (TransactionDTO transactionDTO : dtoList) {
            transactionList.add(toEntity(transactionDTO));
        }
        return transactionList;
    }

    @Override
    public List<TransactionDTO> toDto(List<Transaction> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<TransactionDTO> dtoList = new ArrayList<>(entityList.size());
        for (Transaction transaction : entityList) {
            dtoList.add(toDto(transaction));
        }
        return dtoList;
    }

    @Override
    public void partialUpdate(Transaction entity, TransactionDTO dto) {
    }
}