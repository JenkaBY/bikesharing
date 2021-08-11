package com.godeltech.bikesharing.service.util;

import java.util.Arrays;

public class StringToEnumConverter {
  public static <T extends Enum<T>> T convert(String source, Class<T> enumType) {
    var enumNames = enumType.getEnumConstants();
    for (T name : enumNames) {
      if (name.toString().equals(source.toUpperCase())) {
        return name;
      }
    }
    throw new IllegalArgumentException(String.format("%s must be one of these values %s",
        enumType.getSimpleName(), Arrays.toString(enumNames)));
  }
}
