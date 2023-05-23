package d2.money.service.mapper;

import d2.money.domain.Wallet;
import d2.money.service.dto.WalletDTO;

import java.util.List;

public class WalletMapper implements EntityMapper<Wallet, WalletDTO> {
    @Override
    public WalletDTO toEntity(Wallet dto) {
        return null;
    }

    @Override
    public Wallet toDto(WalletDTO entity) {
        return null;
    }

    @Override
    public List<WalletDTO> toEntity(List<Wallet> dtoList) {
        return null;
    }

    @Override
    public List<Wallet> toDto(List<WalletDTO> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(WalletDTO entity, Wallet dto) {
    }
}
