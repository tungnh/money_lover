package d2.money.service.mapper;

import d2.money.domain.Category;
import d2.money.service.dto.CategoryDTO;

import java.util.List;

public class CategoryMapper implements EntityMapper<CategoryDTO, Category> {
    @Override
    public Category toEntity(CategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        return null;
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> dtoList) {
        return null;
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> entityList) {
        return null;
    }

    @Override
    public void partialUpdate(Category entity, CategoryDTO dto) {

    }
}
