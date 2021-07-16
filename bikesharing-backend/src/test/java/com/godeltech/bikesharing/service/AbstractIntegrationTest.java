package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.config.TestContainerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = TestContainerConfig.class)
public class AbstractIntegrationTest {
  @Autowired
  protected ClientService clientService;
  @Autowired
  protected RentService rentService;
  @Autowired
  protected EquipmentItemService equipmentItemService;
  @Autowired
  protected EquipmentHandlingService equipmentHandlingService;

}
