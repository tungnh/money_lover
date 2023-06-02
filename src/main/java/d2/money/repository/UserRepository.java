package d2.money.repository;

import d2.money.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUsername(String name);
    Optional<User> findOneByEmail(String email);
    Optional<User> findByRole(String role);
    @Query("SELECT u FROM User u WHERE u.id <> :id")
    List<User> findAllUsersExceptId(@Param("id") int id);
}