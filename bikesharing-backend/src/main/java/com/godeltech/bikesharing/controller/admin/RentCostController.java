package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.RentCostMapper;
import com.godeltech.bikesharing.models.request.RentCostRequest;
import com.godeltech.bikesharing.models.response.RentCostResponse;
import com.godeltech.bikesharing.service.admin.RentCostManagementService;
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
@RequestMapping(path = "/v1/bikesharing/admin/rentcost")
public class RentCostController {
  private final RentCostManagementService managementService;
  private final RentCostMapper mapper;

  @PostMapping()
  public ResponseEntity<RentCostResponse> create(
      @Valid @RequestBody RentCostRequest request) {
    var rentCost = mapper.mapToModel(request);
    var response = mapper
        .mapToResponse(managementService.saveWithGroupCode(rentCost, request.getEquipmentGroupCode()));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RentCostResponse> update(
      @Valid @RequestBody RentCostRequest request, @Min(1) @PathVariable Long id) {
    var rentCost = mapper.mapToModel(request);
    var updatedEquipmentGroup = managementService
        .update(rentCost, id);
    var response = mapper.mapToResponse(updatedEquipmentGroup);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
