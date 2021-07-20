package com.godeltech.bikesharing.models.response.lookup;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
abstract class LookupResponse {

  private String code;
  private String name;
}
