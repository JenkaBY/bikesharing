package com.godeltech.bikesharing.service;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  @DataSet(value = "/dataset/clientAccount/clientAccountsInitial.yml",
          cleanBefore = true, useSequenceFiltering = false)
  public void shouldReturnPageWithAccountsForPhoneNumber() {
    var lookup = "3445";

    var actual = clientService.findBySearchCriteria(lookup, Pageable.unpaged());

    List<ClientAccountModel> actualAccounts = actual.getContent();
    System.out.println("Actual " + actualAccounts);
    assertEquals(1, actualAccounts.size());
    assertTrue(actualAccounts.get(0).getPhoneNumber().contains(lookup));
  }

  @Test
  @DataSet(value = "/dataset/clientAccount/clientAccountsInitial.yml",
          cleanBefore = true, useSequenceFiltering = false)
  public void shouldReturnPageWithAccountsForName() {
    var lookup = "MARI";

    var actual = clientService.findBySearchCriteria(lookup, Pageable.unpaged());

    List<ClientAccountModel> actualAccounts = actual.getContent();
    System.out.println("Actual " + actualAccounts);
    assertEquals(2, actualAccounts.size());
    assertTrue(actualAccounts.get(0).getName().toLowerCase().contains(lookup.toLowerCase()));
    assertTrue(actualAccounts.get(1).getName().toLowerCase().contains(lookup.toLowerCase()));
  }
}
