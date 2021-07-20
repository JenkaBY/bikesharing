package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.LookupEntityModel;
import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;
import java.util.stream.Stream;

public interface LookupEntityService<M extends LookupEntityModel, E extends LookupEntity> {

  M getByCode(String code);

  Stream<M> findAll();
}
