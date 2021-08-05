package com.godeltech.bikesharing.models.request;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EquipmentItemRequest {

  @NotBlank
  private String equipmentGroupCode;

  private String equipmentStatusCode;
  @NotBlank
  private String registrationNumber;
  @NotBlank
  private String name;
  @NotBlank
  private String factoryNumber;
  @NotNull
  private LocalDate purchaseDate;

  private String comment;
}
