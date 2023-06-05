package d2.money.controller;

import d2.money.service.CategoryService;
import d2.money.service.TransactionService;
import d2.money.service.UserService;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category/")
public class CategoryController {
    private final UserService userService;
    private final CategoryService categoryService;
    private final TransactionService transactionService;

    public CategoryController(UserService userService, CategoryService categoryService, TransactionService transactionService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping("index")
    public String showCategoryList(Model model, @RequestParam(name = "search", required = false) String search, Pageable pageable) {
        Page<CategoryDTO> categoryList = categoryService.findAll(search, pageable);
        model.addAttribute("categories", categoryList.getContent());
        model.addAttribute("search", search);
        model.addAttribute("currentPage", categoryList.getNumber());
        model.addAttribute("totalPages", categoryList.getTotalPages());
        return "user/category/index";
    }

    @GetMapping("add")
    public String addCategory(Model model) {
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("category", categoryDTO);
        return "/user/category/add";
    }

    @PostMapping("add")
    public String createCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) {
        categoryService.save(categoryDTO);
        return "redirect:/category/index";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Optional<CategoryDTO> optionalCategoryDTO = categoryService.finbyCategoryId(id);
        if (optionalCategoryDTO.isPresent()) {
            CategoryDTO categoryDTO = optionalCategoryDTO.get();
            model.addAttribute("category", categoryDTO);
        }
        return "user/category/update";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@ModelAttribute CategoryDTO categoryDTO) {
        CategoryDTO existingCategoryDTO = categoryService.finbyCategoryId(categoryDTO.getId()).get();
        Date createDate = existingCategoryDTO.getCreatedDate();
        Integer userId = existingCategoryDTO.getUserId();
        categoryDTO.setCreatedDate(createDate);
        categoryDTO.setUserId(userId);
        categoryService.update(categoryDTO);
        return "redirect:/category/index";
    }

    @GetMapping("/{id}")
    private String deleteCategory(@PathVariable int id) {
        List<TransactionDTO> transactionDTOList= transactionService.findByCategoryId(id);
        if (transactionDTOList!=null){
            for (TransactionDTO tra:transactionDTOList) {
                transactionService.delete(tra.getId());
            }
        }
        categoryService.delete(id);
        return "redirect:/category/index";
    }
}