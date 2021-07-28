package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.ServiceOperation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServiceOperationRepository extends JpaRepository<ServiceOperation, Long> {
  @Query("SELECT serviceOperation "
      + "FROM ServiceOperation serviceOperation "
      + "WHERE serviceOperation.equipmentItem.registrationNumber = :registrationNumber "
      + "AND serviceOperation.endDate IS NULL")
  Optional<ServiceOperation> getByEquipmentItemRegistrationNumberEndDateIsNull(String registrationNumber);
}
