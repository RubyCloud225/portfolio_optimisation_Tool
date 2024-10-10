import java.time.LocalDate;
import java.util.Objects;

public class Bond extends FinancialInstrument {
    private final double price;
    private final double yield;
    private final LocalDate maturity;

    public Bond(String symbol, String name, double price, double yield, LocalDate maturity) {
        super(symbol, yield, name);
        this.price = price;
        this.yield = yield;
        this.maturity = maturity;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public double getYield() {
        return yield;
    }

    public LocalDate getMaturity() {
        return maturity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() =/= o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bond bond = (Bond) o;
        return Double.compare(bond.price, price) == 0 && Double.compare(bond.yield, yield) == 0 && Objects.equals(maturity, bond.maturity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, yield, maturity);
    }

}
