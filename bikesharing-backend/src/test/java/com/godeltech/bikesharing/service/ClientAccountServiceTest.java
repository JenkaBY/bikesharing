package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientAccountServiceTest extends AbstractIntegrationTest {
  @Test
  public void crudTest() {
    final NewClientAccountRequest request = ClientAccountUtils.getNewClientAccountRequest();
    final ClientAccountModel model = clientAccountService.save(request);
    final ClientAccountModel modelFromBase = clientAccountService.getByPhoneNumber(model.getPhoneNumber());
    assertNotNull(modelFromBase.getId());
    assertEquals(modelFromBase, model);
  }

}