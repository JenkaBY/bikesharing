package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.request.EquipmentItemRequest;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.service.admin.EquipmentItemManagementService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/admin/equipment_item")
public class EquipmentItemManagementController {
  private final EquipmentItemManagementService managementService;
  private final EquipmentItemMapper mapper;

  @PostMapping()
  public ResponseEntity<EquipmentItemResponse> create(
      @Valid @RequestBody EquipmentItemRequest request) {
    var equipmentItemModel = mapper.mapToModel(request);
    var response = mapper
        .mapToResponse(managementService.create(equipmentItemModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EquipmentItemResponse> update(
      @Valid @RequestBody EquipmentItemRequest request, @Min(1) @PathVariable Long id) {
    var equipmentItemModel = mapper.mapToModel(request);
    var updatedEquipmentItem = managementService
        .update(equipmentItemModel, id);
    var response = mapper.mapToResponse(updatedEquipmentItem);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{registrationNumber}")
  public ResponseEntity<String> delete(@NotBlank @PathVariable String registrationNumber) {
    managementService.setOutOfUse(registrationNumber);
    return new ResponseEntity<>("Resource deleted successfully", HttpStatus.OK);
  }
}
