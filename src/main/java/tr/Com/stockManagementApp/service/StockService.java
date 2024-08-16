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

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
