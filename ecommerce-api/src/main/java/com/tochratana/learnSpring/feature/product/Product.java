package com.tochratana.learnSpring.feature.product;

import com.tochratana.learnSpring.feature.category.Category;
import com.tochratana.learnSpring.feature.order.OrderLine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "products")

public class Product {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Boolean isAvailable;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLineList;

}
