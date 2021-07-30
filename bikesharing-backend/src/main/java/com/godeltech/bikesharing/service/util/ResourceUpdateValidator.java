package com.godeltech.bikesharing.service.util;

import com.godeltech.bikesharing.exception.RequestIdIsNotEqualToPathVariableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ResourceUpdateValidator {

  public void checkIdFromModelEqualsToPathVariable(Long idFromModel, Long id) {
    log.info("checkIdFromModelEqualsToPathVariable for idFromModel: {} and idFromURL: {}", idFromModel, id);
    if (!idFromModel.equals(id)) {
      throw new RequestIdIsNotEqualToPathVariableException(String
          .format("Object from request has index: %s and it doesn't match index from url: %s", idFromModel, id));
    }
  }
}
