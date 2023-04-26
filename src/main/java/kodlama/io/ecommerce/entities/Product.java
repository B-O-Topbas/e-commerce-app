package kodlama.io.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int stockQuantity;
    private double unitPrice;
    private String description;
    private boolean situation;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_category",joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private List<Category> categories;
}