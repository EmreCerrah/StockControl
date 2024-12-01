package tr.com.stockmanagementapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.service.StockService;

import java.util.List;

@Controller // RestController olsaydi URL uzerinden HTTP olusturulacakti
public class StockController {
    /// bu sinif direkt swing ile iletisime gececek olan sinif burdan modeller olusturulacak.

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    public List<StockItem> getStock(String name, double price) {
        return stockService.getAllProducts();
    }

   
}
