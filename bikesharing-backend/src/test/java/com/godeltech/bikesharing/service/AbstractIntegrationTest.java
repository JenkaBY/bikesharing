package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.config.TestContainerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = TestContainerConfig.class)
public class AbstractIntegrationTest {
  @Autowired
  protected ClientAccountService clientAccountService;

}
