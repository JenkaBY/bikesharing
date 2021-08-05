package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {

  Optional<ClientAccount> findByPhoneNumber(String phoneNum);

  Optional<ClientAccount> findByPhoneNumberEndsWith(String searchPattern);

  @Query("SELECT rentOperation.clientAccount "
      + "from RentOperation rentOperation "
      + "where rentOperation.equipmentItem.registrationNumber = ?1")
  Optional<ClientAccount> findByRentOperationAndEquipmentRegistrationNumber(String registrationNumber);
}
