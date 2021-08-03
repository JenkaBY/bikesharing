package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.models.request.FinishEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.request.StartEquipmentMaintenanceRequest;
import com.godeltech.bikesharing.models.response.EquipmentMaintenanceResponse;
import com.godeltech.bikesharing.models.response.FinishEquipmentMaintenanceResponse;
import com.godeltech.bikesharing.service.equipmentmaintenance.EquipmentMaintenanceService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/service_operation")
public class ServiceOperationController {
  private final EquipmentMaintenanceService service;
  private final ServiceOperationMapper mapper;

  @GetMapping("/unfinished")
  public ResponseEntity<List<EquipmentMaintenanceResponse>> getAllUnfinished() {
    var serviceOperations = service.getAllUnfinished();
    var response = serviceOperations.stream()
        .map(mapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping()
  public ResponseEntity<EquipmentMaintenanceResponse> startMaintenanceService(
      @Valid @RequestBody StartEquipmentMaintenanceRequest request) {
    var serviceOperationModel = mapper.mapToModel(request);
    var response = mapper
        .mapToStartResponse(service.startEquipmentServiceOperation(serviceOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FinishEquipmentMaintenanceResponse> finishMaintenanceService(
      @Valid @RequestBody FinishEquipmentMaintenanceRequest request, @Min(1) @PathVariable Long id) {
    var serviceOperationModel = mapper.mapToModel(request);
    var finishedService = service
        .finishEquipmentServiceOperation(serviceOperationModel, id);
    var response = mapper.mapToFinishResponse(finishedService);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
