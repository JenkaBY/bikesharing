package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClientAccountMapperImpl.class)
class ClientAccountMapperTest {
  private static final Long CLIENT_ACCOUNT_ID = 1L;
  @Autowired
  private ClientAccountMapper clientAccountMapper;

  @Test
  void shouldMapEntityToModel() {
    var clientAccount = ClientAccountUtils.getClientAccount();
    clientAccount.setId(CLIENT_ACCOUNT_ID);
    var expected = ClientAccountUtils.getClientAccountModel(CLIENT_ACCOUNT_ID);

    var actual = clientAccountMapper.mapToModel(clientAccount);

    assertEquals(expected, actual);
  }

  @Test
  void shouldMapRequestToEntity() {
    var clientAccountRequest = ClientAccountUtils.getNewClientAccountRequest();

    var actual = clientAccountMapper.mapRequestToEntity(clientAccountRequest);

    var expected = ClientAccountUtils.getClientAccount();
    assertEquals(expected, actual);
  }
}
