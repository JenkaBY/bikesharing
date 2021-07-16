package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import com.godeltech.bikesharing.persistence.repository.common.LookupRepository;
import com.godeltech.bikesharing.service.LookupEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public abstract class LookupEntityServiceImpl<T extends LookupEntity, R extends LookupRepository<T>>
    implements LookupEntityService<T> {
  private final R repository;


  @Override
  @Transactional(readOnly = true)
  public T getByCode(String code) {
    log.info("getByCode: {}", code);
    return repository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("Resource not found with code: %s", code)));
  }


}
