package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientServiceTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;
  private static final ClientAccountModel expected = ClientAccountUtils.getClientAccountModel(ID);
  private static final ClientAccountRequest clientAccountToBeCreated = ClientAccountUtils.getClientAccountRequest();

  @Test
  public void shouldSaveNewClient() {
    var clientAccount = clientAccountMapper.mapToModel(clientAccountToBeCreated);

    var actual = clientService.save(clientAccount);

    assertEquals(expected, actual);
  }

  @Test
  @DataSet(value = "/dataset/clientAccount/clientAccountInitial.yml",
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = "/dataset/clientAccount/clientAccountUpdated.yml")
  public void shouldUpdateClientAccount() {
    var clientAccount = clientAccountMapper.mapToModel(clientAccountToBeCreated);

    var actual = clientService.update(clientAccount, ID);

    assertEquals(expected, actual);
  }
}
