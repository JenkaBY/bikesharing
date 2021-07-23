package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.support.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class AbstractIntegrationTest {
  @Autowired
  protected ClientService clientService;
  @Autowired
  protected RentService rentService;
  @Autowired
  protected EquipmentItemService equipmentItemService;
  @Autowired
  protected EquipmentMaintenanceService equipmentMaintenanceService;
  @Autowired
  protected RentOperationMapper rentOperationMapper;
  @Autowired
  protected RentCostService rentCostService;

}
