package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.TimePeriodModel;
import com.godeltech.bikesharing.persistence.entity.lookup.TimePeriod;
import org.mapstruct.Mapper;

@Mapper
public interface TimePeriodMapper extends LookupMapper<TimePeriodModel, TimePeriod> {
}
