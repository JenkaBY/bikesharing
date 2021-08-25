package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.async.rabbitmq.Consumer;
import com.godeltech.bikesharing.async.rabbitmq.Producer;
import com.godeltech.bikesharing.mapper.ClientAccountMapper;
import com.godeltech.bikesharing.mapper.EquipmentItemMapper;
import com.godeltech.bikesharing.mapper.RentOperationMapper;
import com.godeltech.bikesharing.mapper.ServiceOperationMapper;
import com.godeltech.bikesharing.mapper.TimeInUseMapper;
import com.godeltech.bikesharing.service.admin.EquipmentGroupManagementService;
import com.godeltech.bikesharing.service.admin.EquipmentItemManagementService;
import com.godeltech.bikesharing.service.admin.IncomeDetailsService;
import com.godeltech.bikesharing.service.admin.RentCostManagementService;
import com.godeltech.bikesharing.service.maintenance.EquipmentMaintenanceService;
import com.godeltech.bikesharing.support.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class AbstractIntegrationTest {
  @Autowired
  protected ClientService clientService;
  @Autowired
  protected RentService rentService;
  @Autowired
  protected EquipmentGroupManagementService equipmentGroupManagementService;
  @Autowired
  protected EquipmentItemService equipmentItemService;
  @Autowired
  protected EquipmentItemMapper equipmentItemMapper;
  @Autowired
  protected EquipmentItemManagementService equipmentItemManagementService;
  @Autowired
  protected EquipmentMaintenanceService equipmentMaintenanceService;
  @Autowired
  protected TimeInUseService timeInUseService;
  @Autowired
  protected RentOperationMapper rentOperationMapper;
  @Autowired
  protected ServiceOperationMapper serviceOperationMapper;
  @Autowired
  protected TimeInUseMapper timeInUseMapper;
  @Autowired
  protected RentCostService rentCostService;
  @Autowired
  protected RentCostManagementService rentCostManagementService;
  @Autowired
  protected ClientAccountMapper clientAccountMapper;
  @Autowired
  protected IncomeDetailsService incomeDetailsService;
  @Autowired
  protected Consumer consumer;
  @Autowired
  protected Producer producer;
}
