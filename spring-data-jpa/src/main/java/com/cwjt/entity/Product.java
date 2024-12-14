package com.cwjt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "PRODUCT_TBL")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String name;
    private double price;
//    @Column(name="DESC")
    private String description;
    private String productType;

}
