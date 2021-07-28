package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.FinishRentOperationResponse;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/rentoperation")
public class RentOperationController {
  private final RentService rentService;
  private final RentOperationMapper rentOperationMapper;

  @PostMapping("/start")
  public ResponseEntity<StartRentOperationResponse> startRentOperation(
      @Valid @RequestBody StartRentOperationRequest request) {
    var rentOperationModel = rentOperationMapper.mapToModel(request);
    var response = rentOperationMapper.mapToStartResponse(rentService.startRentOperation(rentOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/finish")
  public ResponseEntity<FinishRentOperationResponse> finishRentOperation(
      @Valid @RequestBody FinishRentOperationRequest request) {
    var rentOperationModel = rentOperationMapper.mapToModel(request);
    var response = rentOperationMapper.mapToFinishResponse(rentService.startRentOperation(rentOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
