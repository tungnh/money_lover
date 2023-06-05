package d2.money.repository;

import d2.money.domain.Budget;
import d2.money.domain.Transaction;
import d2.money.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {
    List<Budget> findByNameContainingIgnoreCase(String name);
    @Query(value = "SELECT budget.id,budget.amount,budget.choose_period,budget.end_date,budget.name,budget.spent,budget.start_date\n" +
            "FROM user \n" +
            "JOIN wallet ON user.id = wallet.user_id \n" +
            "JOIN wallet_budget ON wallet.id = wallet_budget.wallet_id \n" +
            "JOIN budget ON budget.id = wallet_budget.budget_id \n" +
            "WHERE user.id =:userId", nativeQuery = true)
    List<Budget> findBudgetAmountsByUserId(@Param("userId") int userId);
}