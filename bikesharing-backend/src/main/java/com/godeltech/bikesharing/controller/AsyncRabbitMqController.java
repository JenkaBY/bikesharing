package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.async.Producer;
import com.godeltech.bikesharing.models.EquipmentTimeInUseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/bikesharing/time-in-use")
public class AsyncRabbitMqController {
  private final Producer producer;

  @PostMapping()
  public ResponseEntity<String> postMessage(@RequestBody EquipmentTimeInUseModel model) {
    producer.sendMessage(model);
    return new ResponseEntity<>("EquipmentTimeInUseModel pushed to RabbitMQ", HttpStatus.CREATED);
  }
}

