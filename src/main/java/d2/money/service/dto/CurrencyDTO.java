package d2.money.service.dto;



public class CurrencyDTO {
    private int id;
    private String name;
    private String code;
    private double transfer;
    private String symbol;

    public CurrencyDTO() {
    }

    public CurrencyDTO(int id, String name, String code, double transfer, String symbol) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.transfer = transfer;
        this.symbol = symbol;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTransfer() {
        return transfer;
    }

    public void setTransfer(double transfer) {
        this.transfer = transfer;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
