package com.godeltech.bikesharing.service.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.service.AbstractIntegrationTest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientAccountManagementServiceTest extends AbstractIntegrationTest {
  private static final Long ID = 1L;

  @Test
  public void shouldSaveNewClient() {
    var clientAccount = ClientAccountUtils.getClientAccountModel(null);
    var expected = ClientAccountUtils.getClientAccountModel(ID);

    var actual = clientAccountManagementService.save(clientAccount);

    assertNotNull(actual);
    assertEquals(expected, actual);
  }

  @Test
  @DataSet(value = "/dataset/clientAccount/clientAccountInitial.yml",
      cleanBefore = true, useSequenceFiltering = false)
  @ExpectedDataSet(value = "/dataset/clientAccount/clientAccountUpdated.yml")
  public void shouldUpdateClientAccount() {
    var clientAccount = ClientAccountUtils.getClientAccountModel(null);
    var expected = ClientAccountUtils.getClientAccountModel(ID);

    var actual = clientAccountManagementService.update(clientAccount, ID);

    assertNotNull(actual);
    assertEquals(expected, actual);
  }
}
