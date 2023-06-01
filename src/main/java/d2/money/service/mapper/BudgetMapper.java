package d2.money.service.mapper;

import d2.money.domain.Budget;
import d2.money.service.dto.BudgetDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BudgetMapper implements EntityMapper<BudgetDTO, Budget> {
    public Budget toEntity(BudgetDTO dto) {
        Budget budget = new Budget();
        budget.setId(dto.getId());
        budget.setAmount(dto.getAmount());
        budget.setChoosePeriod(dto.getChoosePeriod());
        budget.setEndDate(dto.getEndDate());
        budget.setName(dto.getName());
        budget.setSpent(dto.getSpent());
        budget.setStartDate(dto.getStartDate());
        budget.setCategoryList(dto.getCategoryArrayList());
        budget.setWalletList(dto.getWalletList());
        return budget;
    }

    @Override
    public BudgetDTO toDto(Budget entity) {
        BudgetDTO budget = new BudgetDTO();
        budget.setId(entity.getId());
        budget.setAmount(entity.getAmount());
        budget.setChoosePeriod(entity.getChoosePeriod());
        budget.setEndDate(entity.getEndDate());
        budget.setName(entity.getName());
        budget.setSpent(entity.getSpent());
        budget.setStartDate(entity.getStartDate());
        budget.setCategoryArrayList(entity.getCategoryList());
        budget.setWalletList(entity.getWalletList());
        return budget;
    }

    @Override
    public List<Budget> toEntity(List<BudgetDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Budget> list = new ArrayList<>(dtoList.size());
        for (BudgetDTO b : dtoList) {
            list.add(toEntity(b));
        }
        return list;
    }

    @Override
    public List<BudgetDTO> toDto(List<Budget> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<BudgetDTO> budgetDTOS = new ArrayList<>(entityList.size());
        for (Budget bu : entityList) {
            budgetDTOS.add(toDto(bu));
        }
        return budgetDTOS;
    }

    @Override
    public void partialUpdate(Budget entity, BudgetDTO dto) {
    }
}