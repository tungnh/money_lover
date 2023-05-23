package d2.money.service.util;

import d2.money.domain.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category save(Category category);

    Category update(Category category);

    void delete(int id);

    Category findByCategoryId(int id);
}
