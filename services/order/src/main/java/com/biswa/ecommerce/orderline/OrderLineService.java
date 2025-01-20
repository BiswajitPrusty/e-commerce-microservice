package com.biswa.ecommerce.orderline;

import com.biswa.ecommerce.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = toOrderLine(orderLineRequest);
        return repository.save(order).getId();
    }
    private OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .productId(orderLineRequest.productId())
                .id(orderLineRequest.id())
                .quanity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .build();
    }

    public List<OrderLineResponse> findAllByOrderById(Integer orderId) {
        return repository.findAllByOrderId(orderId).stream()
                .map(this::toOrderLineResponse)
                .collect(Collectors.toList());

    }

    private OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuanity());
    }
}
