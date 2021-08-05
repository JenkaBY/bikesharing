package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.LookupEntityModel;
import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;


public interface LookupMapper<M extends LookupEntityModel, E extends LookupEntity> {

  M mapToModel(E entity);

  E mapToEntity(M model);

  M initWithCode(String code);
}
