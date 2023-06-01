package d2.money.service.mapper;

import d2.money.domain.Category;
import d2.money.service.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements EntityMapper<CategoryDTO, Category> {
    @Override
    public Category toEntity(CategoryDTO dto) {
        Category cate = new Category();
        cate.setId(dto.getId());
        cate.setUserId(dto.getUserId());
        cate.setName(dto.getName());
        cate.setImage(dto.getImage());
        cate.setType(dto.getType());
        cate.setStatus(dto.isStatus());
        cate.setCreatedDate(dto.getCreatedDate());
        cate.setLastModifiedBy(dto.getLastModifiedBy());
        cate.setLastModifiedDate(dto.getLastModifiedDate());
        cate.setParentId(dto.getParentId());
        return cate;
    }

    @Override
    public CategoryDTO toDto(Category entity) {
        CategoryDTO cate = new CategoryDTO();
        cate.setId(entity.getId());
        cate.setUserId(entity.getUserId());
        cate.setName(entity.getName());
        cate.setImage(entity.getImage());
        cate.setType(entity.getType());
        cate.setStatus(entity.isStatus());
        cate.setCreatedDate(entity.getCreatedDate());
        cate.setLastModifiedBy(entity.getLastModifiedBy());
        cate.setLastModifiedDate(entity.getLastModifiedDate());
        cate.setParentId(entity.getParentId());
        return cate;
    }

    @Override
    public List<Category> toEntity(List<CategoryDTO> dtoList) {
        if (dtoList.isEmpty()) {
            return null;
        }
        List<Category> categoryList = new ArrayList<>(dtoList.size());
        for (CategoryDTO categoryDTO : dtoList) {
            categoryList.add(toEntity(categoryDTO));
        }
        return categoryList;
    }

    @Override
    public List<CategoryDTO> toDto(List<Category> entityList) {
        if (entityList.isEmpty()) {
            return null;
        }
        List<CategoryDTO> categoryDTOS = new ArrayList<>(entityList.size());
        for (Category category : entityList) {
            categoryDTOS.add(toDto(category));
        }
        return categoryDTOS;
    }

    @Override
    public void partialUpdate(Category entity, CategoryDTO dto) {
    }
}