package tr.com.stockmanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tr.com.stockmanagementapp.model.Product;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.service.StockService;

@Controller
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    public Product createProduct(String name, double price) {
        return stockService.addProduct(name, price);
    }

    public StockItem updateStock(Long productId, int quantity) {
        return stockService.addStock(productId, quantity);
    }

    public StockItem viewStock(Long productId) {
        return stockService.getStock(productId);
    }
}
