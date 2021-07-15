package com.godeltech.bikesharing.controller;

import com.godeltech.bikesharing.models.request.RentOperationRequest;
import com.godeltech.bikesharing.models.response.RentOperationResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/v1/bikesharing/rentoperation")
public class RentOperationController {
  private final RentService rentService;

  @PostMapping()
  @ApiOperation(value = "Start new rent-operation", nickname = "startRentOperation",
      response = RentOperationResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Ok", response = RentOperationResponse.class),
      @ApiResponse(code = 400, message = "Bad Request", response = GeneralError.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralError.class)})
  public ResponseEntity<RentOperationResponse> startRentOperation(
      @ApiParam(value = "", required = true) @Valid @RequestBody
          RentOperationRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(rentService.startRentOperation(request));
  }

}
