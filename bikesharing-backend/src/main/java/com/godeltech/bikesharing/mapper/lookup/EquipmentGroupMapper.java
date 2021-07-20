package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import org.mapstruct.Mapper;

@Mapper
public interface EquipmentGroupMapper extends LookupMapper<EquipmentGroupModel, EquipmentGroup> {
}