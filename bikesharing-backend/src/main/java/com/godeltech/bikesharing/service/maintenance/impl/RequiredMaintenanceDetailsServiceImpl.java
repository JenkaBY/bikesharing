package com.godeltech.bikesharing.service.maintenance.impl;

import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.persistence.repository.TimeInUseRepository;
import com.godeltech.bikesharing.service.maintenance.RequiredMaintenanceDetailsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequiredMaintenanceDetailsServiceImpl
    implements RequiredMaintenanceDetailsService {
  private final TimeInUseRepository repository;

  @Override
  public List<RequiredMaintenanceDetailsModel> getAllRequiredMaintenanceDetailsItems() {
    log.info("get all required maintenance-details models");

    return repository.getRequiredMaintenanceDetails();
  }
}
