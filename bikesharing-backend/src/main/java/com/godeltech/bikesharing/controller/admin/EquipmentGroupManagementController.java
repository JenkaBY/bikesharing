package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.request.EquipmentGroupRequest;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.service.admin.EquipmentGroupManagementService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/admin/equipment_group")
public class EquipmentGroupManagementController {

  private final EquipmentGroupManagementService managementService;
  private final EquipmentGroupMapper mapper;

  @PostMapping()
  public ResponseEntity<EquipmentGroupResponse> create(
      @Valid @RequestBody EquipmentGroupRequest request) {
    var equipmentGroupModel = mapper.mapToModel(request);
    var response = mapper
        .mapToResponse(managementService.save(equipmentGroupModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipmentGroupResponse> update(
      @Valid @RequestBody EquipmentGroupRequest request, @Min(1) @PathVariable Long id) {
    var equipmentGroupModel = mapper.mapToModel(request);
    var updatedEquipmentGroup = managementService
        .update(equipmentGroupModel, id);
    var response = mapper.mapToResponse(updatedEquipmentGroup);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
