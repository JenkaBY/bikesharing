package com.godeltech.bikesharing.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class LookupEntityModel {
  private Long id;
  private String code;
  private String name;
}
