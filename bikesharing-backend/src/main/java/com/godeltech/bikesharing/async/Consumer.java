package com.godeltech.bikesharing.async;

import com.godeltech.bikesharing.mapper.TimeInUseMapper;
import com.godeltech.bikesharing.models.EquipmentTimeInUseModel;
import com.godeltech.bikesharing.service.TimeInUseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class Consumer {
  private final TimeInUseService timeInUseService;
  private final TimeInUseMapper mapper;

  @RabbitListener(queues = "${spring.rabbitmq.queue-name}")
  public void receiveMessage(EquipmentTimeInUseModel modelFromMessage) {
    log.info("RabbitListener received message: {}", modelFromMessage.toString());
    var timeInUseModel = mapper.mapToModel(modelFromMessage);
    timeInUseService.addTimeInUse(timeInUseModel);
  }
}
