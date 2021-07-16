package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.persistence.entity.RentStatus;
import com.godeltech.bikesharing.persistence.repository.RentStatusRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RentStatusServiceImpl extends LookupEntityServiceImpl<RentStatus, RentStatusRepository> {
  public RentStatusServiceImpl(RentStatusRepository repository) {
    super(repository);
  }
}
