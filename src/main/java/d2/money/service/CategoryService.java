package d2.money.service;

import d2.money.service.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryDTO> findById(int id);
    List<CategoryDTO> findAll();
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO);
    void delete(int id);
}