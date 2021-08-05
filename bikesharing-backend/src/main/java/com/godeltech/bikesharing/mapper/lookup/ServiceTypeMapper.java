package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.ServiceTypeModel;
import com.godeltech.bikesharing.persistence.entity.lookup.ServiceType;
import org.mapstruct.Mapper;

@Mapper
public interface ServiceTypeMapper extends LookupMapper<ServiceTypeModel, ServiceType> {

}
