package tr.com.stockmanagementapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tr.com.stockmanagementapp.config.AppConfig;
import tr.com.stockmanagementapp.controller.StockController;
import tr.com.stockmanagementapp.model.Product;
import tr.com.stockmanagementapp.model.StockItem;
import tr.com.stockmanagementapp.service.StockService;

public class Main {
    public static void main(String[] args) {
//        App.main(args);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StockController stockController = context.getBean(StockController.class);
        System.out.print(stockController);

//        // Yeni bir Product ve StockItem oluştur ve ayarla
//        Product product = new Product("Example Product", "Product Details", 10);
//        StockItem stockItem = new StockItem(product);
//        stockItem.setQuantity(2);

    }
}
