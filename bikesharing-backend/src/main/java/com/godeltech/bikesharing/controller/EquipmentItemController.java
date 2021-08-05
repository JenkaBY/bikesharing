package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.models.response.EquipmentItemResponse;
import com.godeltech.bikesharing.service.EquipmentItemService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/page/{pageNum}")
  public ResponseEntity<List<EquipmentItemResponse>> getAllByStatusCode(@PathVariable(name = "pageNum") int pageNum,
      @RequestParam String statusCode) {
    var equipmentItems = service.getAllByStatusCode(statusCode, pageNum);

    var response = equipmentItems.stream()
        .map(mapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
