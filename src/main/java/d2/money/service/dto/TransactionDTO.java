package d2.money.service.dto;

import d2.money.domain.Category;
import d2.money.domain.Wallet;

import java.util.List;

public class TransactionDTO {
    private Integer id;
    private Integer categoryId;
    private Integer walletTransferId;
    private Integer receivingWalletId;
    private double amount;
    private String day;
    private String note;
    private String image;
    private List<Integer> listFriend;
    private Category category;
    private Wallet wallet;

    public TransactionDTO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getWalletTransferId() {
        return walletTransferId;
    }

    public void setWalletTransferId(Integer walletTransferId) {
        this.walletTransferId = walletTransferId;
    }

    public Integer getReceivingWalletId() {
        return receivingWalletId;
    }

    public void setReceivingWalletId(Integer receivingWalletId) {
        this.receivingWalletId = receivingWalletId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getListFriend() {
        return listFriend;
    }

    public void setListFriend(List<Integer> listFriend) {
        this.listFriend = listFriend;
    }
}