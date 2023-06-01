package d2.money.repository;

import d2.money.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.walletId = :id OR t.receiveWalletId = :id")
    List<Transaction> findByDayBetweenAndWalletId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Transaction WHERE (wallet_id = :id OR receive_wallet_id = :id) AND day BETWEEN :start AND :end ORDER BY day DESC LIMIT 3", nativeQuery = true)
    List<Transaction> findTop3ByDayBetweenAndWalletIdOrderByDayDesc(@Param("start") Date start, @Param("end") Date end, @Param("id") Integer id);

    List<Transaction> findByWalletId(int id);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.category.id IN :categoryIds " +
            "AND t.wallet.id IN :walletIds " +
            "AND t.day >= :startDate AND t.day <= :endDate")
    List<Transaction> findTransactionsByCategoryAndWalletAndDate(@Param("categoryIds") List<Integer> categoryIds,
                                                                 @Param("walletIds") List<Integer> walletIds,
                                                                 @Param("startDate") Date startDate,
                                                                 @Param("endDate") Date endDate);

}