package d2.money.service.dto;

import d2.money.domain.Currency;
import d2.money.domain.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class WalletDTO {
    private int id;
    private String name;
    private double balance;
    private String image;
    private int currencyId;
    private Date createDate;
    private int userId;
    public WalletDTO() {
    }

    public WalletDTO(int id, String name, double balance, String image, int currencyId, Date createDate, int userId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.image = image;
        this.currencyId = currencyId;
        this.createDate = createDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
