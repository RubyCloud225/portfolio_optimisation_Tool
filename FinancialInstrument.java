public abstract class FinancialInstrument {
    private final String symbol;
    private final String name;
    private double price;

    public FinancialInstrument(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public FinancialInstrument(String name, double price, String symbol) {
        this.name = name;
        this.price = price;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}