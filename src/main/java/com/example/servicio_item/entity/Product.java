package com.example.servicio_item.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Integer id;
    private String productName;
    private Supplier supplier;
    private Category category;
    private String quantityPerUnit;
    private Double unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;
    private Boolean discontinued;
    //coloco el puerto del servidor que esta sirviendo el servicio.
    private int port;
}