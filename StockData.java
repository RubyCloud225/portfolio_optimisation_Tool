import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "stock_data")
public class StockData {
    @Id 
    private string symbol;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal peg;
    private BigDecimal marketCap;
    private BigDecimal dividend;
    private BigDecimal volume;
}
