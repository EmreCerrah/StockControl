package tr.com.stockmanagementapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.stockmanagementapp.model.StockItem;

@Repository
public interface StockRepository extends JpaRepository<StockItem, Long> {
    
    @Query("SELECT s FROM StockItem s WHERE s.id=?")
    StockItem findByProductId(Long productId);
}
