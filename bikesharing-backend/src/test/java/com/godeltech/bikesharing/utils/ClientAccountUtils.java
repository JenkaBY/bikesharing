package com.godeltech.bikesharing.utils;

import com.godeltech.bikesharing.models.ClientAccountModel;
import com.godeltech.bikesharing.models.request.ClientAccountRequest;
import com.godeltech.bikesharing.models.response.ClientAccountResponse;
import com.godeltech.bikesharing.persistence.entity.ClientAccount;

public class ClientAccountUtils {
  public static final String NAME = "FelixTheCat";
  public static final String ADDRESS = "London, Downing st. 10";
  public static final Integer RATING = 1;
  public static final String PHONE_NUMBER = "+100223334455";
  public static final String COMMENTS = "BlaBlaBlaaaaa";

  public static ClientAccount getClientAccount() {
    var clientAccount = new ClientAccount();
    clientAccount.setName(NAME);
    clientAccount.setAddress(ADDRESS);
    clientAccount.setRating(RATING);
    clientAccount.setPhoneNumber(PHONE_NUMBER);
    clientAccount.setComments(COMMENTS);
    return clientAccount;
  }

  public static ClientAccountModel getClientAccountModel(Long id) {
    var clientAccountModel = new ClientAccountModel();
    clientAccountModel.setId(id);
    clientAccountModel.setName(NAME);
    clientAccountModel.setAddress(ADDRESS);
    clientAccountModel.setRating(RATING);
    clientAccountModel.setPhoneNumber(PHONE_NUMBER);
    clientAccountModel.setComments(COMMENTS);
    return clientAccountModel;
  }

  public static ClientAccountRequest getClientAccountRequest() {
    var clientAccountRequest = new ClientAccountRequest();
    clientAccountRequest.setName(NAME);
    clientAccountRequest.setAddress(ADDRESS);
    clientAccountRequest.setPhoneNumber(PHONE_NUMBER);
    clientAccountRequest.setRating(RATING);
    clientAccountRequest.setComments(COMMENTS);
    return clientAccountRequest;
  }

  public static ClientAccountResponse getClientAccountResponse(Long id) {
    var clientAccountResponse = new ClientAccountResponse();
    clientAccountResponse.setId(id);
    clientAccountResponse.setAddress(ADDRESS);
    clientAccountResponse.setName(NAME);
    clientAccountResponse.setPhoneNumber(PHONE_NUMBER);
    clientAccountResponse.setComments(COMMENTS);
    return clientAccountResponse;
  }
}
