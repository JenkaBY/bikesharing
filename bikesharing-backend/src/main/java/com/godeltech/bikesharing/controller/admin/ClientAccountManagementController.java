package com.godeltech.bikesharing.controller.admin;

import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.models.response.ClientAccountResponse;
import com.godeltech.bikesharing.service.ClientService;
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
@RequestMapping(path = "/v1/bikesharing/admin/client")
public class ClientAccountManagementController {

  private final ClientService service;
  private final ClientAccountMapper mapper;

  @PostMapping
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
}
