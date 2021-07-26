package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.exception.AbstractBusinessException;
import com.godeltech.bikesharing.models.response.error.GeneralError;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GeneralErrorMapper {

  @Mapping(target = "exceptionCode", expression = "java(exception.getClass().getSimpleName().toUpperCase())")
  GeneralError mapToError(AbstractBusinessException exception);
}
