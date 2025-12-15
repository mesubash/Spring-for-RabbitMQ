package com.developer.rabbitmq.publisher;

import com.developer.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.json.name}")
    private String jsonExchangeName;

    @Value("${rabbitmq.routing.key.json}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user) {
        LOGGER.info("Json message sent -> {}", user.toString());
        rabbitTemplate.convertAndSend(jsonExchangeName, jsonRoutingKey, user);
    }



}
