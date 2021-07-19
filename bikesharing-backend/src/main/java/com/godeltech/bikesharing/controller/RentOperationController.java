package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.models.request.StartRentOperationRequest;
import com.godeltech.bikesharing.models.response.StartRentOperationResponse;
import com.godeltech.bikesharing.models.response.error.GeneralError;
import com.godeltech.bikesharing.service.RentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/v1/bikesharing/rentoperation")
public class RentOperationController {
  private final RentService rentService;
  private final RentOperationMapper rentOperationMapper;

  @PostMapping()
  @ApiOperation(value = "Start new rent-operation", nickname = "startRentOperation",
      response = StartRentOperationResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Ok", response = StartRentOperationResponse.class),
      @ApiResponse(code = 400, message = "Bad Request", response = GeneralError.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralError.class)})
  public ResponseEntity<StartRentOperationResponse> startRentOperation(
      @ApiParam(value = "", required = true) @Valid @RequestBody
          StartRentOperationRequest request) {
    var rentOperationModel = rentOperationMapper.mapToModel(request);
    var response = rentOperationMapper.mapToResponse(rentService.startRentOperation(rentOperationModel));
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}
