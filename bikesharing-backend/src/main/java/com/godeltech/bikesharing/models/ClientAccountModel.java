package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ClientAccountModel {

  private Long id;
  private String phoneNumber;
  private String name;
  private String address;
  private Integer rating;
  private String comments;
}
