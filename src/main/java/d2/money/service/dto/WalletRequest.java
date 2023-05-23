package d2.money.service.dto;

public class WalletRequest {
    private int id;
    private String name;
    private double balance;
    private String image;
    private int currencyId;


    public WalletRequest() {
    }

    public WalletRequest(int id, String name, double balance, String image, int currencyId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.image = image;
        this.currencyId = currencyId;

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

}
