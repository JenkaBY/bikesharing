package com.godeltech.bikesharing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@RequiredArgsConstructor
public class RabbitMqConfig {
  @Value("${spring.rabbitmq.queue-name}")
  private String QUEUE;
  @Value("${spring.rabbitmq.exchange-name}")
  private String EXCHANGE;
  @Value("${spring.rabbitmq.routing-key}")
  private String ROUTING_KEY;

  @Bean
  Queue queue() {
    return new Queue(QUEUE, true);
  }

  @Bean
  DirectExchange exchange() {
    return new DirectExchange(EXCHANGE);
  }

  @Bean
  Binding binding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(ROUTING_KEY);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    return rabbitTemplate;
  }
}
