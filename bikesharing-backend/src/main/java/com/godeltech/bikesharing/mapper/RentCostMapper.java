package com.godeltech.bikesharing.mapper;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.RentCostModel;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.models.request.RentCostRequest;
import com.godeltech.bikesharing.models.response.RentCostResponse;
import com.godeltech.bikesharing.persistence.entity.RentCost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EquipmentGroupMapper.class)
public interface RentCostMapper {

  RentCostModel mapToModel(RentCost entity);

  @Mapping(target = "equipmentGroup.code", source = "request.equipmentGroupCode")
  RentCostModel mapToModel(RentCostRequest request);

  RentCost mapToEntity(RentCostModel model);

  @Mapping(target = "id", source = "model.id")
  @Mapping(target = "equipmentGroup", source = "equipmentGroupModel")
  RentCost mapToEntity(RentCostModel model, EquipmentGroupModel equipmentGroupModel);

  RentCostResponse mapToResponse(RentCostModel model);
}
