package com.godeltech.bikesharing.service.impl.lookup;

import com.godeltech.bikesharing.mapper.lookup.TimePeriodMapper;
import com.godeltech.bikesharing.models.lookup.TimePeriodModel;
import com.godeltech.bikesharing.persistence.entity.lookup.TimePeriod;
import com.godeltech.bikesharing.persistence.repository.lookup.TimePeriodRepository;
import com.godeltech.bikesharing.service.impl.LookupEntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TimePeriodServiceImpl extends
    LookupEntityServiceImpl<TimePeriodModel, TimePeriod> {
  public TimePeriodServiceImpl(TimePeriodRepository repository, TimePeriodMapper mapper) {
    super(repository, mapper);
  }
}
