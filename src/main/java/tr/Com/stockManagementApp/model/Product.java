package tr.com.stockmanagementapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
