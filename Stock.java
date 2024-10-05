public class Stock extends FinancialInstrument {
    private final int volume;

    public Stock(String symbol, String name, double price, int volume) {
        super(name, price, symbol);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }
}  
