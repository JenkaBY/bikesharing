package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.models.response.ClientAccountResponse;
import com.godeltech.bikesharing.service.ClientService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/admin/client")
public class ClientAccountManagementController {
  private final ClientService service;
  private final ClientAccountMapper mapper;

  @PostMapping()
  public ResponseEntity<ClientAccountResponse> create(
      @Valid @RequestBody ClientAccountRequest request) {
    var clientAccountModel = mapper.mapToModel(request);
    var response = mapper
        .mapToResponse(service.save(clientAccountModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientAccountResponse> update(
      @Valid @RequestBody ClientAccountRequest request, @Min(1) @PathVariable Long id) {
    var clientAccountModel = mapper.mapToModel(request);
    var updatedAccount = service.update(clientAccountModel, id);
    var response = mapper.mapToResponse(updatedAccount);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @ApiResponse(responseCode = "200",
          description = "Return pageable list of clients gor given substring of phone number or name. The endpoint returns empty list if the lookup search less then 3 characters or for blank string")
  @GetMapping
  public Page<ClientAccountResponse> findAllBySearchCriteria(
          @RequestParam(name = "lookup") String lookup,
          @ParameterObject Pageable pageableRequest) {
    log.info("Find clients by criteria '{}' and page {}", lookup, pageableRequest);
    return service.findBySearchCriteria(lookup, pageableRequest)
            .map(mapper::mapToResponse);
  }
}
