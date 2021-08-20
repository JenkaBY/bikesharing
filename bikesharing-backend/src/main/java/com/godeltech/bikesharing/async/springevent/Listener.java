package com.godeltech.bikesharing.async.springevent;

import com.godeltech.bikesharing.mapper.TimeInUseMapper;
import com.godeltech.bikesharing.models.request.EquipmentTimeInUseRequest;
import com.godeltech.bikesharing.service.TimeInUseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Listener {
  private final TimeInUseService timeInUseService;
  private final TimeInUseMapper mapper;

  @Async
  @EventListener
  public void handleEquipmentTimeInUseRequest(EquipmentTimeInUseRequest event) {
    log.info("SpringEventListener received event: {}", event.toString());
    var timeInUseModel = mapper.mapToModel(event);
    timeInUseService.addTimeInUse(timeInUseModel);
  }
}
