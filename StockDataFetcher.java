import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yahoofinance.api.Stock;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StockDataFetcher {
    private final StockDataRespository respository;
    private final Cache<String, StockData> cache;

    
}


