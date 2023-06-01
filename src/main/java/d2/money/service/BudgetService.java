package d2.money.service;

import d2.money.service.dto.BudgetDTO;

import java.util.List;
import java.util.Optional;

public interface BudgetService {

    List<BudgetDTO> findAll();

    List<BudgetDTO> serchByName(String name);

    BudgetDTO save(BudgetDTO budgetDTO);

    BudgetDTO update(BudgetDTO budgetDTO);

    void delete(int id);

    Optional<BudgetDTO> findByBudgetId(int id);
}