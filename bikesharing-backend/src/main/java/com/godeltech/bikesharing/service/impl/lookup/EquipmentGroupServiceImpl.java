package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.mapper.lookup.EquipmentGroupMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentGroupModel;
import com.godeltech.bikesharing.persistence.entity.lookup.EquipmentGroup;
import com.godeltech.bikesharing.persistence.repository.lookup.EquipmentGroupRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EquipmentGroupServiceImpl extends
    LookupEntityServiceImpl<EquipmentGroupModel, EquipmentGroup> {

  public EquipmentGroupServiceImpl(EquipmentGroupRepository repository, EquipmentGroupMapper mapper) {
    super(repository, mapper);
  }
}
