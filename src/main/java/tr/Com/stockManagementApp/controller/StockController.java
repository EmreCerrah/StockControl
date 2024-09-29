package tr.com.stockmanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tr.com.stockmanagementapp.model.Product;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.service.StockService;

@Controller // RestController olsaydi URL uzerinden HTTP olusturulacakti
public class StockController {
    /// bu sinif direkt swing ile iletisime gececek olan sinif burdan modeller olusturulacak.

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    public Product createProduct(String name, double price) {
        return StockService.addProduct(name, price);
    }

    public StockItem updateStock(Long productId, int quantity) {
        return StockService.addStock(productId, quantity);
    }

    public StockItem viewStock(Long productId) {
        return StockService.getStock(productId);
    }
}
