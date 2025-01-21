package com.biswa.ecommerce.payment;

import com.biswa.ecommerce.customer.CustomerResponse;
import com.biswa.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
