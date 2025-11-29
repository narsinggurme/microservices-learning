package com.ng.microservice.service;

import com.ng.orderservice.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender JavaMailSender;

    @KafkaListener(topics = "order_placed_topic")
    public void listenOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received order placed message: {}", orderPlacedEvent);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String body = String.format("""
                    Hi,
                    Your order with order id %s has been placed successfully.
                    
                    Best regards,
                    Spring Shop Team
                    """, orderPlacedEvent.getOrderNumber());
            helper.setFrom("springshop@gmail.com");
            helper.setTo(orderPlacedEvent.getEmail());
            helper.setSubject(String.format("Order Placed Successfully - %s", orderPlacedEvent.getOrderNumber()));
            helper.setText(body, false);
        };

        try {
            // Simulate email sending
            log.info("Sending email to {}", orderPlacedEvent.getEmail());
            JavaMailSender.send(messagePreparator);
            log.info("Email sent successfully to {}", orderPlacedEvent.getEmail());
        } catch (Exception e) {
            log.error("Failed to send email to {}", orderPlacedEvent.getEmail(), e);
        }
    }

}
