package d2.money.repository;

import d2.money.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {
    List<Budget> findByNameContainingIgnoreCase(String name);
}