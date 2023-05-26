package d2.money.repository;

import d2.money.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUsername(String name);
    Optional<User> findOneByEmail(String email);
<<<<<<< HEAD
=======

    Optional<User> findOneByUsername (String name);

>>>>>>> 764532906c982b035f812176e0cba543bb1975b2
}



