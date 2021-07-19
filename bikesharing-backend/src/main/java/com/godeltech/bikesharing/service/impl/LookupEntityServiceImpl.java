package com.godeltech.bikesharing.service.impl;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.LookupEntityModel;
import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import com.godeltech.bikesharing.persistence.repository.common.LookupRepository;
import com.godeltech.bikesharing.service.LookupEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
public abstract class LookupEntityServiceImpl<M extends LookupEntityModel, E extends LookupEntity,
    R extends LookupRepository<E>, T extends LookupMapper<M, E>>
    implements LookupEntityService<M, E> {

  private final R repository;
  private final T mapper;

  @Override
  @Transactional(readOnly = true)
  public M getByCode(String code) {
    log.info("getByCode: {}", code);
    E entity = repository.findByCode(code)
        .orElseThrow(() -> new ResourceNotFoundException(
            String.format("Resource not found with code: %s", code)));

    return mapper.mapToModel(entity);
  }


}
