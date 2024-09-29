package tr.com.stockmanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.stockmanagementapp.model.Product;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.repository.ProductRepository;
import tr.com.stockmanagementapp.repository.StockRepository;

import java.util.List;

@Service
public class StockService {
  
    private final StockRepository stockRepository;
    @Autowired
    public StudentService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Product> getAllProducts() {
        return stockRepository.findAll();
    }
    public void saveProducts(StockItem stockItem) {
        stockRepository.save(stockItem);
    }
    public void getStock(Long productId, int quantity) {
        stockRepository.findByProductId(productId);
    }
}
