package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClientAccountMapperImpl.class)
class ClientAccountMapperTest {

  private static final Long ID = 1L;
  private static final ClientAccountModel clientAccountModel = ClientAccountUtils.getClientAccountModel(ID);

  @Autowired
  private ClientAccountMapper clientAccountMapper;

  @Test
  void shouldMapEntityToModel() {
    var clientAccount = ClientAccountUtils.getClientAccount();
    clientAccount.setId(ID);

    var actual = clientAccountMapper.mapToModel(clientAccount);

    assertEquals(clientAccountModel, actual);
  }

  @Test
  void shouldMapRequestToModel() {
    var clientAccountRequest = ClientAccountUtils.getClientAccountRequest();

    var actual = clientAccountMapper.mapToModel(clientAccountRequest);
    actual.setId(ID);
    actual.setRating(1);

    assertEquals(clientAccountModel, actual);
    assertNotNull(actual.getRating());
  }

  @Test
  void shouldMapModelToResponse() {
    var expected = ClientAccountUtils.getClientAccountResponse(ID);

    var actual = clientAccountMapper.mapToResponse(clientAccountModel);

    assertEquals(expected, actual);
  }
}
