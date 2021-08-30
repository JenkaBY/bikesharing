package com.godeltech.bikesharing.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration
public class RabbitMqTestConfig {
  private static final String IMAGE_VERSION = "rabbitmq:3-management";
  private static final String NOTIFICATION_ROUTING_KEY = "notifications";
  private static final String TESTCONTAINERS_TEST_EXCHANGE = "testcontainers.test.exchange";
  private static final String TESTCONTAINERS_TEST_QUEUE = "testcontainers.test.queue";

  @Bean(initMethod = "start", destroyMethod = "stop")
  public GenericContainer<?> rabbit() {
    return new GenericContainer<>(IMAGE_VERSION)
        .withExposedPorts(5672, 15672);
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory fac = new CachingConnectionFactory();
    fac.setHost(rabbit().getContainerIpAddress());
    fac.setPort(rabbit().getMappedPort(5672));
    return fac;
  }

}
