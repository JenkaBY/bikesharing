package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RentOperationRepositoryRepository extends JpaRepository<EquipmentItem, Long> {
  Optional<EquipmentItem> findByRegistrationNumber(String registrationNumber);

  @Modifying(flushAutomatically = true, clearAutomatically = true)
  @Query("UPDATE EquipmentItem eItem "
      + "SET eItem.equipmentStatus.id = "
      + "(SELECT eStatus.id from EquipmentStatus eStatus where eStatus.code = :status) "
      + "where eItem.registrationNumber = :registrationNumber")
  void updateEquipmentItemStatus(String registrationNumber, String status);


  @Query("SELECT equipmentItem.equipmentStatus.code "
      + "from EquipmentItem equipmentItem "
      + "where equipmentItem.registrationNumber = :registrationNumber")
  String getEquipmentStatusCodeByRegistrationNumber(String registrationNumber);

}