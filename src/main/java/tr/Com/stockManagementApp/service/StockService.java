package tr.com.stockmanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.repository.StockRepository;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    @Transactional(readOnly = true)
    public List<StockItem> getAllProducts() {
        return stockRepository.findAll();
    }
    @Transactional
    public void saveProducts(StockItem stockItem) {
        stockRepository.save(stockItem);
    }
    @Transactional(readOnly = true)
    public void getStock(Long productId, int quantity) {
        stockRepository.findByProductId(productId);
    }
}
