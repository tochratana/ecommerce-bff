package com.tochratana.learnSpring.feature.category;

import com.tochratana.learnSpring.feature.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Make POJO = Plain Java Old Object
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Make JPA Entity
@Entity
@Table( name = "categories" )
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(nullable = false, length = 50)
    private String name; // Default String = 255 characters

    private String description;

    private String icon;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
