package com.godeltech.bikesharing.service;

import com.godeltech.bikesharing.models.ClientAccountModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientAccountModel getByPhoneNumber(String phoneNum);

    public ClientAccountModel save(ClientAccountModel model);

    public ClientAccountModel update(ClientAccountModel model, Long id);

    ClientAccountModel getOrCreateByPhoneNumber(String clientPhoneNumber);

    ClientAccountModel getById(Long id);

    Page<ClientAccountModel> findBySearchCriteria(String q, Pageable pageableRequest);
}
