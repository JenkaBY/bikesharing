package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.mapper.lookup.RentStatusMapper;
import com.godeltech.bikesharing.models.lookup.RentStatusModel;
import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.persistence.repository.RentStatusRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RentStatusServiceImpl
    extends LookupEntityServiceImpl<RentStatusModel, RentStatus> {

  public RentStatusServiceImpl(RentStatusRepository repository, RentStatusMapper mapper) {
    super(repository, mapper);
  }
}
