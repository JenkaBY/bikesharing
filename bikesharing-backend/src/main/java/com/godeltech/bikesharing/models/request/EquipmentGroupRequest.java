package com.godeltech.bikesharing.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentGroupRequest {
  @Pattern(regexp = "^[A-Z(_*)]*$",
      message = "Invalid code! "
          + "Must contain all upperCase-words with '_' between them. Valid example: VALID_CODE_EXAMPLE")
  private String code;

  @NotBlank
  private String name;
}
