package com.developer.rabbitmq.consumer;

import com.developer.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    private void consumeJsonMessage(User user){
        LOGGER.info("Json Message received from RabbitMQ: {}", user.toString());
    }
}
