package d2.money.repository;

import d2.money.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Integer> {
    List<Wallet> findByUserId(int id);
    List<Wallet> findByCurrencyId(int id);
    List<Wallet> findByIdIn(List<Integer> integerList);
}