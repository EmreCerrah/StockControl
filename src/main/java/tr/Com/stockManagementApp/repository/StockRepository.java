package tr.com.stockmanagementapp.repository;

import org.springframework.stereotype.Repository;
import tr.com.stockmanagementapp.model.StockItem;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StockRepository {
    private Map<Long, StockItem> stockMap = new HashMap<>();

    public StockItem save(StockItem stockItem) {
        stockMap.put(stockItem.getProduct().getId(), stockItem);
        return stockItem;
    }

    public StockItem findByProductId(Long productId) {
        return stockMap.get(productId);
    }
}
