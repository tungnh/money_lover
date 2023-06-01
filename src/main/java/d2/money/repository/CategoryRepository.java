package d2.money.repository;

import d2.money.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);
    Page<Category> findByNameContaining(String name, Pageable pageable);
}