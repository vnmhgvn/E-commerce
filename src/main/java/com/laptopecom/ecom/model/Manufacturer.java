package com.laptopecom.ecom.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manufacturer_id")
    private int id;

    private String name;
}
