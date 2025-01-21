package com.biswa.ecommerce.kafka;

import com.biswa.ecommerce.email.EmailService;
import com.biswa.ecommerce.kafka.order.OrderConfirmation;
import com.biswa.ecommerce.kafka.payment.PaymentConfirmation;
import com.biswa.ecommerce.notification.Notification;
import com.biswa.ecommerce.notification.NotificationRepository;
import com.biswa.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("PaymentConfirmation received: {}", paymentConfirmation);
        repository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .paymentConfirmation(paymentConfirmation)
                .notificationDate(LocalDateTime.now())
                .build());

        //send email
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
                );
    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("OrderConfirmation received: {}", orderConfirmation);
        repository.save(Notification.builder()
                .type(NotificationType.ORDER_CONFIRMATION)
                .orderConfirmation(orderConfirmation)
                .notificationDate(LocalDateTime.now())
                .build());
        //send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
