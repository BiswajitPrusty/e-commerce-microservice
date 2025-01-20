package com.biswa.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductPurchaseRequest{
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @NotNull(message = "Quantity is mandatory")
    private double quantity;
}
