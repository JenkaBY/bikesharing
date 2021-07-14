package com.godeltech.bikesharing.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateClientAccountRequest {
  private String phoneNumber;
  private String name;
  private String address;
  private String comments;
}
