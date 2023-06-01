package d2.money.repository;

import d2.money.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {
    List<Friend> findByUserIdAndAndState(int id,int state);
}