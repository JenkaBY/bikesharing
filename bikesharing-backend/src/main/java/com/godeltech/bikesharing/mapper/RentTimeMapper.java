package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.RentTimeModel;
import com.godeltech.bikesharing.models.request.RentTimeRequest;
import org.mapstruct.Mapper;

@Mapper
public interface RentTimeMapper {

  RentTimeModel mapToModel(RentTimeRequest request);
}
