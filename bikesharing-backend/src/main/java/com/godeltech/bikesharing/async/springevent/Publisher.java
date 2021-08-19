package com.godeltech.bikesharing.async.springevent;

import com.godeltech.bikesharing.models.request.EquipmentTimeInUseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Publisher {
  private final ApplicationEventPublisher publisher;

  public void publishEvent(final Long equipmentItemId, final Long minutesInUse) {
    var event = new EquipmentTimeInUseRequest(equipmentItemId, minutesInUse);
    publisher.publishEvent(event);
  }
}
