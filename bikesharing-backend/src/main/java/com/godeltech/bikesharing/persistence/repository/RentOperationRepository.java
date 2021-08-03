package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RentOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentOperationRepository extends JpaRepository<RentOperation, Long> {
  Optional<RentOperation> getByEquipmentItemRegistrationNumberAndRentStatusCode(String registrationNumber, String code);

  List<RentOperation> findByRentStatusCode(String statusCode);
}
