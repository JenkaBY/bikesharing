package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.models.response.lookup.EquipmentStatusResponse;
import com.godeltech.bikesharing.models.response.lookup.RentStatusResponse;
import org.mapstruct.Mapper;

@Mapper
public interface LookupResponseMapper {

  EquipmentGroupResponse mapToResponse(EquipmentGroupModel model);

  EquipmentStatusResponse mapToResponse(EquipmentStatusModel model);

  RentStatusResponse mapToResponse(RentStatusModel model);
}
