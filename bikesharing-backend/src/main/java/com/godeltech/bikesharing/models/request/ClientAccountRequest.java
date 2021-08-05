package com.godeltech.bikesharing.models.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ClientAccountRequest {

  @Pattern(regexp = "^(\\+\\d{1,3}( )?)?\\d{10,15}$",
      message = "Invalid phone number! Valid example:+1234...(to max 15 digits)")
  private String phoneNumber;
  @NotBlank
  private String name;
  @NotBlank
  private String address;
  @Min(0)
  @Max(10)
  private Integer rating;

  private String comments;
}
