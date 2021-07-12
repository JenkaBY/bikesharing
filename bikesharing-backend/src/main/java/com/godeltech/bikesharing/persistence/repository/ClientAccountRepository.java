package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.ClientAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Long> {
  Optional<ClientAccount> findByPhoneNumber(String phoneNum);
}
