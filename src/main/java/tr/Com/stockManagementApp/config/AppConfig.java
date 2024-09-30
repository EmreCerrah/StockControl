package tr.com.stockmanagementapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tr.com.stockmanagementapp.model.Product;
import tr.com.stockmanagementapp.model.StockItem;

@Configuration
@ComponentScan(basePackages = "com.stockmanagement")
public class AppConfig {
    @Bean
    public Product product(){
        return new Product(1L,"1","1" ,1);
    }
    @Bean
    public StockItem stockItem(){
        return new StockItem(product());
    }
}