package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.request.EquipmentGroupRequest;
import com.godeltech.bikesharing.models.response.lookup.EquipmentGroupResponse;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentGroup;
import org.mapstruct.Mapper;

@Mapper
public interface EquipmentGroupMapper extends LookupMapper<EquipmentGroupModel, EquipmentGroup> {
  EquipmentGroupModel mapToModel(EquipmentGroupRequest request);

  EquipmentGroupResponse mapToResponse(EquipmentGroupModel model);
}
