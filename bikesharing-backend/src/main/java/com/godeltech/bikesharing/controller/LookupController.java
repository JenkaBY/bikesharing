package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import java.util.List;
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


  @GetMapping("/equipment_group")
  public ResponseEntity<List<EquipmentGroupModel>> getEquipmentGroups() {
    return ResponseEntity.status(HttpStatus.OK).body(equipmentGroupService.findAll());
  }

  @GetMapping("/equipment_status")
  public ResponseEntity<List<EquipmentStatusModel>> getEquipmentStatuses() {
    return ResponseEntity.status(HttpStatus.OK).body(equipmentStatusService.findAll());
  }

  @GetMapping("/rent_status")
  public ResponseEntity<List<RentStatusModel>> getRentStatuses() {
    return ResponseEntity.status(HttpStatus.OK).body(rentStatusService.findAll());
  }
}
