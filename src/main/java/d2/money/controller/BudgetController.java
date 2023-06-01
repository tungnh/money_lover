package d2.money.controller;

import d2.money.service.*;
import d2.money.service.dto.BudgetDTO;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.dto.UserDTO;
import d2.money.service.dto.WalletDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/budget/")
public class BudgetController {
    private final BudgetService budgetService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final WalletService walletService;
    private final TransactionService transactionService;

    public BudgetController(BudgetService budgetService, UserService userService, CategoryService categoryService, WalletService walletService, TransactionService transactionService) {
        this.budgetService = budgetService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.walletService = walletService;
        this.transactionService = transactionService;
    }

    @GetMapping("index")
    public String listBudget(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        List<BudgetDTO> budgetDTOS = budgetService.findAll();
        List<CategoryDTO> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("budgetList", budgetDTOS);
        return "user/budget/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        List<WalletDTO> walletDTOS = walletService.findAll();
        List<CategoryDTO> categoryList = categoryService.findAll();
        model.addAttribute("walletList", walletDTOS);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("budgetDTO", new BudgetDTO());
        return "user/budget/add";
    }

    @PostMapping("/create")
    public String createBudget(@ModelAttribute("budgetDTO") BudgetDTO budgetDTO) {
        budgetService.save(budgetDTO);
        return "redirect:/budget/index";
    }

    @GetMapping("/{id}")
    private String deletebudGet(@PathVariable int id) {
        budgetService.delete(id);
        return "redirect:/budget/index";
    }

    @GetMapping("/detail/{id}")
    public String getBudgetById(@PathVariable int id, Model model, Authentication authentication) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        Optional<BudgetDTO> budgetDTOOptional = budgetService.findByBudgetId(id);
        if (budgetDTOOptional.isPresent()) {
            BudgetDTO budgetDTO = budgetDTOOptional.get();
            model.addAttribute("spent", transactionService.spent(budgetDTO.getId()));
            model.addAttribute("budget", budgetDTO);
            model.addAttribute("walletList", budgetDTO.getWalletList());
            model.addAttribute("categoryList", budgetDTO.getCategoryArrayList());
        }
        return "user/budget/detail";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Authentication authentication, Model model) {
        Optional<UserDTO> userDTO = userService.getUserProfile(authentication);
        if (userDTO.isPresent()) {
            UserDTO user = userDTO.get();
            model.addAttribute("user", user);
        }
        Optional<BudgetDTO> budgetDTOOptional = budgetService.findByBudgetId(id);
        if (budgetDTOOptional.isPresent()) {
            BudgetDTO budgetDTO = budgetDTOOptional.get();
            model.addAttribute("budget", budgetDTO);
            List<CategoryDTO> categoryList = categoryService.findAll();
            model.addAttribute("categoryList", categoryList);
            List<WalletDTO> walletList = walletService.findAll();
            model.addAttribute("walletList", walletList);
        }
        return "user/budget/update";
    }

    @PostMapping("/update/{id}")
    public String updateBudget(@PathVariable int id, @ModelAttribute BudgetDTO budgetDTO) {
        budgetDTO.setId(id);
        budgetService.update(budgetDTO);
        return "redirect:/budget/index";
    }
}