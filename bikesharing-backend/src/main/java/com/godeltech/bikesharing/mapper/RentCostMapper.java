package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.persistence.entity.RentCost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RentCostMapper {

  RentCostModel mapToModel(RentCost entity);

  RentCost mapToEntity(RentCostModel model);

}
