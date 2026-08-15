package com.tochratana.learnSpring.feature.order;

import com.tochratana.learnSpring.feature.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderLineId;

    @Column(nullable = false)
    private Integer qty;

    private Float discount;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
