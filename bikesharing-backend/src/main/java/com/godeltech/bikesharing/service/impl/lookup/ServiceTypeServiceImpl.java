package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.mapper.lookup.ServiceTypeMapper;
import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.persistence.entity.lookup.ServiceType;
import com.godeltech.bikesharing.persistence.repository.lookup.ServiceTypeRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeServiceImpl extends
    LookupEntityServiceImpl<ServiceTypeModel, ServiceType> {
  public ServiceTypeServiceImpl(ServiceTypeRepository repository, ServiceTypeMapper mapper) {
    super(repository, mapper);
  }
}
