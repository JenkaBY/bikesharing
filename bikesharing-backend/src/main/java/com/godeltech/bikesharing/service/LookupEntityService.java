package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.persistence.entity.common.LookupEntity;

public interface LookupEntityService<T extends LookupEntity> {
  T getByCode(String code);
}
