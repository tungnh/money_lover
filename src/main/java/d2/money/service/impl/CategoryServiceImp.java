package d2.money.service.impl;

import d2.money.domain.Category;
import d2.money.repository.CategoryRepository;
import d2.money.service.CategoryService;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryDTO.getId());
        if (existingCategory.isPresent()) {
            Category category = categoryMapper.toEntity(categoryDTO);
            Category updatedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(updatedCategory);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryDTO> findById(int id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }
}