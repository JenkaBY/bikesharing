package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;

public class ClientAccountUtils {
  public static final Long CLIENT_ACCOUNT_ID = 1L;
  public static final String NAME = "FelixTheCat";
  public static final String ADDRESS = "London, Downing st. 10";
  public static final Integer RATING = 1;
  public static final String PHONE_NUMBER = "+100223334455";
  public static final String COMMENTS = "BlaBlaBlaaaaa";

  public static ClientAccount getClientAccount() {
    var clientAccount = new ClientAccount();
    clientAccount.setId(CLIENT_ACCOUNT_ID);
    clientAccount.setName(NAME);
    clientAccount.setAddress(ADDRESS);
    clientAccount.setRating(RATING);
    clientAccount.setPhoneNumber(PHONE_NUMBER);
    clientAccount.setComments(COMMENTS);
    return clientAccount;
  }

  public static ClientAccountModel getClientAccountModel() {
    var clientAccountModel = new ClientAccountModel();
    clientAccountModel.setId(CLIENT_ACCOUNT_ID);
    clientAccountModel.setName(NAME);
    clientAccountModel.setAddress(ADDRESS);
    clientAccountModel.setRating(RATING);
    clientAccountModel.setPhoneNumber(PHONE_NUMBER);
    clientAccountModel.setComments(COMMENTS);
    return clientAccountModel;
  }
}
