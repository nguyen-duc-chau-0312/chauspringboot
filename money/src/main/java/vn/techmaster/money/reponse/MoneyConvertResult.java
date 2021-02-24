package vn.techmaster.money.reponse;

public class MoneyConvertResult {
    private int amount;
    private double amountResult;
    private String fromCurrency;
    private String toCurrency;

    public MoneyConvertResult(int amount, double amountResult, String fromCurrency, String toCurrency) {
        this.amount = amount;
        this.amountResult = amountResult;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getAmountResult() {
        return amountResult;
    }

    public void setAmountResult(double amountResult) {
        this.amountResult = amountResult;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
