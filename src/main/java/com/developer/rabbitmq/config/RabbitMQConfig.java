package com.developer.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.exchange.json.name}")
    private String exchangeNameJson;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJson;

    //spring bean for rabbitmq queue
    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    //spring bean for rabbitmq json queue (to store json messages)
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName, false);
    }

    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    //spring bean for rabbitmq json exchange
    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(exchangeNameJson);
    }

    //spring bean for rabbitmq binding between queue and exchange using routing key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }

    //spring bean for rabbitmq binding between json queue and json exchange using routing key
    @Bean
    public Binding jsonBinding(Queue jsonQueue, TopicExchange exchange2) {
        return BindingBuilder
                .bind(jsonQueue)
                .to(exchange2)
                .with(routingKeyJson);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
    // these three can be autoconfigured by spring boot autoconfiguration

}
