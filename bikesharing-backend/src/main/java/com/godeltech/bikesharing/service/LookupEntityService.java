package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.LookupEntityModel;
import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;

public interface LookupEntityService<M extends LookupEntityModel, E extends LookupEntity> {
  M getByCode(String code);
}
