package tr.com.stockmanagementapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name",nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name="details", columnDefinition = "TEXT")
    private String details;

    @Column(name="price", columnDefinition = "NUMERIC")
    private double price;
    public Product (String name, String details,double price){
        this.name=name;
        this.details=details;
        this.price=price;
    }
    public Product (){
    }
}
