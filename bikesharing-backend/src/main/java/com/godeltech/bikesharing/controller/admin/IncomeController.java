package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.IncomeDetailsItemMapper;
import com.godeltech.bikesharing.models.enums.IncomeTimeUnit;
import com.godeltech.bikesharing.models.response.IncomeDetailsItemResponse;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.util.StringToEnumConverter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping(path = "/v1/bikesharing/admin/income")
public class IncomeController {
  private final IncomeDetailsService service;
  private final IncomeDetailsItemMapper mapper;


  @GetMapping
  public ResponseEntity<List<IncomeDetailsItemResponse>> getAllByTimeUnitDate(
      @RequestParam @NotBlank String timeUnit,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    var incomeTimeUnit = StringToEnumConverter.convert(timeUnit, IncomeTimeUnit.class);
    var incomeDetailsItems = service.getAllIncomeDetailsItems(incomeTimeUnit, date);
    var response = incomeDetailsItems.stream()
        .map(mapper::mapToResponse)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
