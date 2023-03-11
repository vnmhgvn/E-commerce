package com.laptopecom.ecom.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "manufacturer_id")
    private Manufacturer manufacturer;
    private String cpu;
    private String screen;
    private String operatingSystem;
    private String ram;
    private String hardDrive;
    private String battery;
    private String warranty;
    private double price;
    private int quantity;
    private String description;
    private String imageName;
}
