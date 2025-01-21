package com.biswa.ecommerce.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductPurchaseResponse {
    private Integer id;
    private String name;
    private String description;
    private double quantity;
    private BigDecimal price;
}
