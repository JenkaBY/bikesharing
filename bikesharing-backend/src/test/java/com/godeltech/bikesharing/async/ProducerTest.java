package com.godeltech.bikesharing.async;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.eq;

import com.godeltech.bikesharing.async.rabbitmq.Producer;
import com.godeltech.bikesharing.models.request.EquipmentTimeInUseRequest;
import com.godeltech.bikesharing.utils.TimeInUseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

class ProducerTest {
  private static final Long ID = 1L;
  private static final EquipmentTimeInUseRequest EQUIPMENT_TIME_IN_USE_MODEL = TimeInUseUtils
      .getEquipmentTimeInUseRequest(ID);
  private Producer subject;
  private RabbitTemplate rabbitTemplateMock;

  @BeforeEach
  public void setUp() {
    this.rabbitTemplateMock = Mockito.mock(RabbitTemplate.class);
    this.subject = new Producer(this.rabbitTemplateMock);
  }

  @Test
  public void shouldSendMessage() {
    assertThatCode(() -> this.subject.sendMessage(EQUIPMENT_TIME_IN_USE_MODEL)).doesNotThrowAnyException();
    Mockito.verify(this.rabbitTemplateMock)
        .convertAndSend(eq(null), eq(null), eq(EQUIPMENT_TIME_IN_USE_MODEL));
  }

}