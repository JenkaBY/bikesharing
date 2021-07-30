package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
import com.godeltech.bikesharing.models.response.FinishEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.service.EquipmentMaintenanceService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/serviceoperation")
public class ServiceOperationController {
  private final EquipmentMaintenanceService equipmentMaintenanceService;
  private final ServiceOperationMapper serviceOperationMapper;

  @GetMapping
  public ResponseEntity<List<EquipmentMaintenanceResponse>> getAllEquipmentMaintenances() {
    return ResponseEntity.of(Optional.empty());
  }

  @PostMapping("/start")
//  FIXME rename to startMaintenanceService
  public ResponseEntity<EquipmentMaintenanceResponse> startRentOperation(
      @Valid @RequestBody StartEquipmentMaintenanceRequest request) {
    var serviceOperationModel = serviceOperationMapper.mapToModel(request);
    var response = serviceOperationMapper
        .mapToStartResponse(equipmentMaintenanceService.startEquipmentServiceOperation(serviceOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  // TODO The PUT method should be here. the similar as in RentOperationController
//    @PutMapping("/{id}")
  @PostMapping("/finish")
  //  FIXME rename to finishMaintenanceService
  public ResponseEntity<FinishEquipmentMaintenanceResponse> finishRentOperation(
      @Valid @RequestBody FinishEquipmentMaintenanceRequest request) {
    var serviceOperationModel = serviceOperationMapper.mapToModel(request);
    var finishedService = equipmentMaintenanceService
        .finishEquipmentServiceOperation(serviceOperationModel);
    var response = serviceOperationMapper.mapToFinishResponse(finishedService);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
