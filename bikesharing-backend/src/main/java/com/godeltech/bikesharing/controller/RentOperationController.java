package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.models.request.FinishRentOperationRequest;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.FinishRentOperationResponse;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
import com.godeltech.bikesharing.service.RentService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/rent_operation")
public class RentOperationController {

  private final RentService service;
  private final RentOperationMapper mapper;

  @GetMapping
  public ResponseEntity<List<RentOperationResponse>> getAllByStatusCode(@RequestParam String statusCode) {
    var rentOperationModels = service.getAllByStatusCode(statusCode);
    var response = rentOperationModels.stream()
        .map(mapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<RentOperationResponse> startRentOperation(
      @Valid @RequestBody StartRentOperationRequest request) {
    var rentOperationModel = mapper.mapToModel(request);
    var response = mapper.mapToResponse(service.startRentOperation(rentOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FinishRentOperationResponse> finishRentOperation(
      @Valid @RequestBody FinishRentOperationRequest request, @Min(1) @PathVariable Long id) {
    var rentOperationModel = mapper.mapToModel(request);
    RentOperationModel finishedRent = service.finishRentOperation(rentOperationModel, id);
    var response = mapper.mapToFinishResponse(finishedRent);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
