package d2.money.service.mapper;

import d2.money.domain.Wallet;
import d2.money.service.dto.WalletRequest;

import java.util.List;

public class WalletMapper implements EntityMapper<Wallet, WalletRequest> {

    @Override
    public WalletRequest toEntity(Wallet dto) {
        return null;
    }

    @Override
    public Wallet toDto(WalletRequest entity) {
        return null;
    }

    @Override
    public List<WalletRequest> toEntity(List<Wallet> dtoList) {
        return null;
    }

    @Override
    public List<Wallet> toDto(List<WalletRequest> entityList) {
        return null;
    }
}
