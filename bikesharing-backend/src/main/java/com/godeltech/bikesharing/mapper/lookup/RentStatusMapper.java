package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.lookup.RentStatus;
import org.mapstruct.Mapper;

@Mapper
public interface RentStatusMapper extends LookupMapper<RentStatusModel, RentStatus> {
}
