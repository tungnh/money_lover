package d2.money.service.dto;

import d2.money.domain.Category;
import d2.money.domain.Wallet;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BudgetDTO {
    private Integer id;
    private String name;
    private double amount;
    private double spent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String choosePeriod;
    private List<Integer> categoryList = new ArrayList<>();
    private List<Integer> listWalletId = new ArrayList<>();
    private List<Wallet> walletList = new ArrayList<>();
    private List<Category> categoryArrayList = new ArrayList<>();

    public BudgetDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getChoosePeriod() {
        return choosePeriod;
    }

    public void setChoosePeriod(String choosePeriod) {
        this.choosePeriod = choosePeriod;
    }

    public List<Integer> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Integer> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Integer> getListWalletId() {
        return listWalletId;
    }

    public void setListWalletId(List<Integer> listWalletId) {
        this.listWalletId = listWalletId;
    }

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    public List<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(List<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }
}