package com.godeltech.bikesharing.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class LookupEntityModel {

  @JsonIgnore
  private Long id;
  private String code;
  private String name;
}
