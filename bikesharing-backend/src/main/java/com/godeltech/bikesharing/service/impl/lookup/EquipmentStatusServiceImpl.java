package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import com.godeltech.bikesharing.persistence.repository.EquipmentStatusRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EquipmentStatusServiceImpl extends LookupEntityServiceImpl<EquipmentStatus, EquipmentStatusRepository> {
  public EquipmentStatusServiceImpl(EquipmentStatusRepository repository) {
    super(repository);
  }
}
