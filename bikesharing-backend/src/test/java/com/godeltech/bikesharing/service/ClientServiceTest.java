package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.CreateClientAccountRequest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientServiceTest extends AbstractIntegrationTest {

  @Test
  public void shouldSaveNewClient() {
//    You can omit 'final' word here and in other places.
    final CreateClientAccountRequest request = ClientAccountUtils.getNewClientAccountRequest();
    final ClientAccountModel model = clientService.save(request);
    final ClientAccountModel modelFromBase = clientService.getByPhoneNumber(model.getPhoneNumber());
//    TODO Do you sure that actual(modelFromBase) object is not null?
//     In my opinion the assertNotNull(modelFromBase) should be here at the first place
    assertNotNull(modelFromBase.getId());
    assertEquals(modelFromBase, model);
  }

}
