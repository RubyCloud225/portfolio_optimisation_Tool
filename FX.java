
public class FX extends FinancialInstrument {
    private final String currency;
    private double rate;

    public FX(String symbol, String name, double price, String currency, double rate) {
        super(symbol, name, price);
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "FX{" +
                "symbol='" + getSymbol() + '\'' +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", currency='" + currency + '\'' +
                ", rate=" + rate +
                '}';
    }
}
