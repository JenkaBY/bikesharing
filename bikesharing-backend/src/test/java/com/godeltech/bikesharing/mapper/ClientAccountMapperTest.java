package com.godeltech.bikesharing.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.godeltech.bikesharing.utils.ClientAccountUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClientAccountMapperImpl.class)
class ClientAccountMapperTest {
  @Autowired
  private ClientAccountMapper clientAccountMapper;

  @Test
  void shouldMapEntityToModel() {
    var clientAccount = ClientAccountUtils.getClientAccount();
    var clientAccountModel = ClientAccountUtils.getClientAccountModel();

    assertTrue(EqualsBuilder
        .reflectionEquals(clientAccountMapper.mapToModel(clientAccount), clientAccountModel));
  }
}