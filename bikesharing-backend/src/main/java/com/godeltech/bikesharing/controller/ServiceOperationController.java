package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
import com.godeltech.bikesharing.models.response.FinishEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.service.equipmentmaintenance.EquipmentMaintenanceService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/serviceoperation")
public class ServiceOperationController {
  private final EquipmentMaintenanceService equipmentMaintenanceService;
  private final ServiceOperationMapper serviceOperationMapper;

  @GetMapping
  //TODO Implement method
  public ResponseEntity<List<EquipmentMaintenanceResponse>> getAllEquipmentMaintenances() {
    return ResponseEntity.of(Optional.empty());
  }

  @PostMapping()
  public ResponseEntity<EquipmentMaintenanceResponse> startMaintenanceService(
      @Valid @RequestBody StartEquipmentMaintenanceRequest request) {
    var serviceOperationModel = serviceOperationMapper.mapToModel(request);
    var response = serviceOperationMapper
        .mapToStartResponse(equipmentMaintenanceService.startEquipmentServiceOperation(serviceOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FinishEquipmentMaintenanceResponse> finishMaintenanceService(
      @Valid @RequestBody FinishEquipmentMaintenanceRequest request, @Min(1) @PathVariable Long id) {
    var serviceOperationModel = serviceOperationMapper.mapToModel(request);
    var finishedService = equipmentMaintenanceService
        .finishEquipmentServiceOperation(serviceOperationModel, id);
    var response = serviceOperationMapper.mapToFinishResponse(finishedService);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
