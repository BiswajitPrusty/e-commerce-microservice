package com.biswa.ecommerce.order;

import com.biswa.ecommerce.customer.CustomerClient;
import com.biswa.ecommerce.exception.BusinessException;
import com.biswa.ecommerce.kafka.OrderConfirmation;
import com.biswa.ecommerce.kafka.OrderProducer;
import com.biswa.ecommerce.orderline.OrderLineRequest;
import com.biswa.ecommerce.orderline.OrderLineService;
import com.biswa.ecommerce.product.ProductClient;
import com.biswa.ecommerce.product.PurchaseRequest;
import com.biswa.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(@Valid OrderRequest request) {
        //check the customer from customer-service using FeignClient
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with provided Id: " + request.customerId()));

        // purchase the product from product-service using rest template
        List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(request.products());

        //persist order
        Order order = repository.save(toOrder(request));

        //persist order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process

        //send the order confirmation to notification-service
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts
        ));
        return order.getId();
    }


    public List<OrderResponse> findAll() {

        return repository.findAll().stream().map(this::toOrderResponse).toList();
    }

    private OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }

    private Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse findById(Integer orderId) {

        return repository.findById(orderId)
                .map(this::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %d not found", orderId)));
    }
}
