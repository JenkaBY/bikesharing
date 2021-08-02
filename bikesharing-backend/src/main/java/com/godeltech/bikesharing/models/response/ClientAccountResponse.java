package com.godeltech.bikesharing.models.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ClientAccountResponse {
  private Long id;
  private String phoneNumber;
  private String name;
  private String address;
  private String comments;
}
