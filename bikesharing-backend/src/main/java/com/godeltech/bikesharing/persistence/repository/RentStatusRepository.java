package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RentStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentStatusRepository extends JpaRepository<RentStatus, Long> {
  Optional<RentStatus> findByCode(String code);
}
