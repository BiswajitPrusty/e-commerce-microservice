package com.biswa.ecommerce.kafka;

import com.biswa.ecommerce.customer.CustomerResponse;
import com.biswa.ecommerce.order.PaymentMethod;
import com.biswa.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
