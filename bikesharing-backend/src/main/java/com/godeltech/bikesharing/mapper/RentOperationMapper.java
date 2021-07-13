package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.RentOperationModel;
import com.godeltech.bikesharing.persistence.entity.RentOperation;
import org.mapstruct.Mapper;

@Mapper
public interface RentOperationMapper {

  RentOperationModel mapToModel(RentOperation rentOperation);

  RentOperation mapToEntity(RentOperationModel operationModel);
}
