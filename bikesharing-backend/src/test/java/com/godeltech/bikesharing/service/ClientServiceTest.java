package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.NewClientAccountRequest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientServiceTest extends AbstractIntegrationTest {
  @Test
  public void shouldSaveNewClient() {
    final NewClientAccountRequest request = ClientAccountUtils.getNewClientAccountRequest();
    final ClientAccountModel model = clientService.save(request);
    final ClientAccountModel modelFromBase = clientService.getByPhoneNumber(model.getPhoneNumber());
    assertNotNull(modelFromBase.getId());
    assertEquals(modelFromBase, model);
  }

}