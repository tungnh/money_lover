package d2.money.service.impl;

import d2.money.domain.Budget;
import d2.money.domain.Category;
import d2.money.domain.Wallet;
import d2.money.repository.BudgetRepository;
import d2.money.repository.WalletRepository;
import d2.money.service.BudgetService;
import d2.money.service.CategoryService;
import d2.money.service.WalletService;
import d2.money.service.dto.BudgetDTO;
import d2.money.service.dto.CategoryDTO;
import d2.money.service.dto.WalletDTO;
import d2.money.service.mapper.BudgetMapper;
import d2.money.service.mapper.CategoryMapper;
import d2.money.service.mapper.WalletMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImp implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final WalletRepository walletRepository;

    public BudgetServiceImp(BudgetRepository budgetRepository, BudgetMapper budgetMapper, CategoryService categoryService, CategoryMapper categoryMapper, WalletService walletService, WalletMapper walletMapper, WalletRepository walletRepository) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.walletService = walletService;
        this.walletMapper = walletMapper;
        this.walletRepository = walletRepository;
    }

    @Override
    public List<BudgetDTO> findAll() {
        return budgetMapper.toDto(budgetRepository.findAll());
    }

    @Override
    public List<BudgetDTO> serchByName(String name) {
        List<Budget> budgets = budgetRepository.findByNameContainingIgnoreCase(name);
        return budgetMapper.toDto(budgets);
    }

    @Override
    public BudgetDTO save(BudgetDTO budgetDTO) {
        if (budgetDTO.getAmount() <= 0 || budgetDTO.getStartDate().after(budgetDTO.getEndDate())) {
            throw new IllegalArgumentException("Ngân sách không hợp lệ");
        }
        List<Integer> categoryIdList = budgetDTO.getCategoryList();
        List<Category> categoryList = new ArrayList<>();
        for (Integer categoryId : categoryIdList) {
            CategoryDTO categoryDTO = categoryService.finbyCategoryId(categoryId).get();
            if (categoryDTO != null) {
                Category category = categoryMapper.toEntity(categoryDTO);
                categoryList.add(category);
            }
        }
        List<Integer> walletIdList = budgetDTO.getListWalletId();
        List<Wallet> walletList = new ArrayList<>();
        for (Integer walletId : walletIdList) {
            WalletDTO walletDTO = walletService.findById(walletId).get();
            if (walletDTO != null) {
                Wallet wallet = walletMapper.toEntity(walletDTO);
                walletList.add(wallet);
            }
        }
        Budget budget = budgetMapper.toEntity(budgetDTO);
        budget.setCategoryList(categoryList);
        budget.setWalletList(walletList);
        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDto(savedBudget);
    }

    @Override
    public BudgetDTO update(BudgetDTO budgetDTO) {
        Budget budget = budgetMapper.toEntity(budgetDTO);
        Budget budgetRp = budgetRepository.findById(budget.getId()).get();
        if (budgetDTO.getAmount() <= 0 || budgetDTO.getStartDate().after(budgetDTO.getEndDate())) {
            throw new IllegalArgumentException("Ngân sách không hợp lệ");
        }
        if (budgetDTO.getListWalletId() == null || budgetDTO.getListWalletId().isEmpty()) {
            budget.setWalletList(budgetRp.getWalletList());
        } else {
            List<Integer> walletIdList = budgetDTO.getListWalletId();
            List<Wallet> walletList = new ArrayList<>();
            for (Integer walletId : walletIdList) {
                WalletDTO walletDTO = walletService.findById(walletId).get();
                if (walletDTO != null) {
                    Wallet wallet = walletMapper.toEntity(walletDTO);
                    walletList.add(wallet);
                }
            }
            budget.setWalletList(walletList);
        }
        if (budgetDTO.getCategoryList() == null || budgetDTO.getCategoryList().isEmpty()) {
            budget.setCategoryList(budgetRp.getCategoryList());
        } else {
            List<Integer> categoryIdList = budgetDTO.getCategoryList();
            List<Category> categoryList = new ArrayList<>();
            for (Integer categoryId : categoryIdList) {
                CategoryDTO categoryDTO = categoryService.finbyCategoryId(categoryId).get();
                if (categoryDTO != null) {
                    Category category = categoryMapper.toEntity(categoryDTO);
                    categoryList.add(category);
                }
            }
            budget.setCategoryList(categoryList);
        }
        Budget savedBudget = budgetRepository.save(budget);
        return budgetMapper.toDto(savedBudget);
    }

    @Override
    public void delete(int id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public Optional<BudgetDTO> findByBudgetId(int id) {
        Optional<Budget> budgetOptional = budgetRepository.findById(id);
        return budgetOptional.map(budgetMapper::toDto);
    }
}