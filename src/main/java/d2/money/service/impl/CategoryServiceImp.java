package d2.money.service.impl;

import d2.money.domain.Category;
import d2.money.repository.CategoryRepository;
import d2.money.service.CategoryService;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;
import d2.money.domain.User;
import d2.money.repository.CategoryRepository;
import d2.money.repository.UserRepository;
import d2.money.service.CategoryService;
import d2.money.service.UserService;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper, UserService userService, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public Page<CategoryDTO> findAll(String search, Pageable pageable) {
        if (search == null || search.isEmpty()) {
            return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
        } else {
            return categoryRepository.findByNameContaining(search, pageable).map(categoryMapper::toDto);
        }
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }

    public List<CategoryDTO> serchByName(String name) {
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(name);
        return categoryMapper.toDto(categories);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            Category category = categoryMapper.toEntity(categoryDTO);
            category.setUserId(categoryDTO.getUserId());
            category.setCreatedDate(categoryDTO.getCreatedDate());
            category.setUserId(optionalUser.get().getId());
            category.setCreatedDate(new Date());
            Category savedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(savedCategory);
        }
        return null;
    }

    @Override
    public Optional<CategoryDTO> findById(int id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryDTO> finbyCategoryId(int id) {
        return categoryRepository.findById(id).map(categoryMapper::toDto);
    }


    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Optional<Category> existingCategory = categoryRepository.findById(categoryDTO.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Optional<User> optionalUser = userRepository.findOneByUsername(userDetails.getUsername());
            if (existingCategory.isPresent()) {
                Category category = categoryMapper.toEntity(categoryDTO);
                category.setLastModifiedBy(optionalUser.get().getUsername());
                category.setLastModifiedDate(new Date());
                Category updatedCategory = categoryRepository.save(category);
                return categoryMapper.toDto(updatedCategory);
            }
        }
        return null;
    }
}



