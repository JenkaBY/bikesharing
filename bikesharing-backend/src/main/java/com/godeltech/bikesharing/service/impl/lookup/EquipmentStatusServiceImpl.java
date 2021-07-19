package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.mapper.lookup.EquipmentStatusMapper;
import com.godeltech.bikesharing.models.lookup.EquipmentStatusModel;
import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import com.godeltech.bikesharing.persistence.repository.EquipmentStatusRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EquipmentStatusServiceImpl extends
    LookupEntityServiceImpl<EquipmentStatusModel, EquipmentStatus, EquipmentStatusRepository, EquipmentStatusMapper> {
  public EquipmentStatusServiceImpl(EquipmentStatusRepository repository, EquipmentStatusMapper mapper) {
    super(repository, mapper);
  }
}
