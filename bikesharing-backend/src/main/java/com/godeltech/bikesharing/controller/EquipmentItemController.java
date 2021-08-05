package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.service.EquipmentItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/equipment_item")
public class EquipmentItemController {
  private final EquipmentItemService service;
  private final EquipmentItemMapper mapper;

  @GetMapping()
  public ResponseEntity<Page<EquipmentItemResponse>> getAllByStatusCode(@RequestParam(defaultValue = "10") int pageSize,
                                                                        @RequestParam(defaultValue = "1")
                                                                            int pageNumber,
                                                                        @RequestParam(required = false)
                                                                            String statusCode) {
    var equipmentItems = service.getAllByStatusCode(statusCode, pageSize, pageNumber);

    var response = equipmentItems
        .map(mapper::mapToResponse);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
