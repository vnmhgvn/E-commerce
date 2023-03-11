package com.laptopecom.ecom.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int categoryId;
    private int manufacturerId;
    private String cpu;
    private String screen;
    private String operatingSystem;
    private String ram;
    private String battery;
    private String warranty;
    private double price;
    private String hardDrive;
    private int quantity;
    private String description;
    private String imageName;
}
