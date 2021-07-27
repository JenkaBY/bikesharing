package com.godeltech.bikesharing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;

public class ClientServiceTest extends AbstractIntegrationTest {

  @Test
  public void shouldSaveNewClient() {
    var request = ClientAccountUtils.getNewClientAccountRequest();

    var actual = clientService.save(request);

    var expected = clientService.getByPhoneNumber(actual.getPhoneNumber());
    assertNotNull(actual);
    assertEquals(expected, actual, "Saved client is not equal expected object");
  }

}
