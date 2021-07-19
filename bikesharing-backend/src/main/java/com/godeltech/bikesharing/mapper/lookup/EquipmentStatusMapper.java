package com.godeltech.bikesharing.mapper.lookup;

import com.godeltech.bikesharing.mapper.LookupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import org.mapstruct.Mapper;

@Mapper
public interface EquipmentStatusMapper extends LookupMapper<EquipmentStatusModel, EquipmentStatus> {
}
