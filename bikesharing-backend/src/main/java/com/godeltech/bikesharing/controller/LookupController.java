package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.LookupResponseMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.models.response.lookup.EquipmentStatusResponse;
import com.godeltech.bikesharing.models.response.lookup.RentStatusResponse;
import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/bikesharing/lookup")
public class LookupController {

  private final LookupEntityServiceImpl<EquipmentGroupModel, EquipmentGroup> equipmentGroupService;
  private final LookupEntityServiceImpl<EquipmentStatusModel, EquipmentStatus> equipmentStatusService;
  private final LookupEntityServiceImpl<RentStatusModel, RentStatus> rentStatusService;
  private final LookupResponseMapper responseMapper;

  @GetMapping("/equipment_group")
  public ResponseEntity<List<EquipmentGroupResponse>> getEquipmentGroups() {
    var response = equipmentGroupService.findAll()
        .map(responseMapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/equipment_status")
  public ResponseEntity<List<EquipmentStatusResponse>> getEquipmentStatuses() {
    var response = equipmentStatusService.findAll()
        .map(responseMapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/rent_status")
  public ResponseEntity<List<RentStatusResponse>> getRentStatuses() {
    var response = rentStatusService.findAll()
        .map(responseMapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
