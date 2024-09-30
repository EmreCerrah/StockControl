package tr.com.stockmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.stockmanagementapp.model.StockItem;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface StockRepository extends JpaRepository<StockItem, Long> {
    
    @Query("SELECT s FROM stock s WHERE s.id=?")
    public StockItem findByProductId(Long productId);
}
