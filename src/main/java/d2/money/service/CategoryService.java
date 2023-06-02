package d2.money.service;

import d2.money.domain.Category;
import d2.money.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryDTO> findById(int id);

    Page<CategoryDTO> findAll(String search, Pageable pageable);

    List<CategoryDTO> findAll();
    List<CategoryDTO> findByUserId(int id);

    List<CategoryDTO> serchByName(String name);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(int id);

    Optional<CategoryDTO> finbyCategoryId(int id);
}