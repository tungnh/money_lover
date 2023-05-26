package d2.money.service.mapper;

import d2.money.domain.Budget;
import d2.money.service.dto.BudgetDTO;

import java.util.List;

public class BudgetMapper implements EntityMapper<BudgetDTO, Budget> {
    @Override
    public Budget toEntity(BudgetDTO dto) {
        return null;
    }

    @Override
    public BudgetDTO toDto(Budget entity) {
        return null;
    }

    @Override
    public List<Budget> toEntity(List<BudgetDTO> dtoList) {
        return null;
    }

    @Override
    public List<BudgetDTO> toDto(List<Budget> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Budget entity, BudgetDTO dto) {

    }
}