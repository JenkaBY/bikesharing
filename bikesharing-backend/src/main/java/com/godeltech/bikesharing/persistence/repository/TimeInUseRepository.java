package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.models.RequiredMaintenanceDetailsModel;
import com.godeltech.bikesharing.persistence.entity.TimeInUse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TimeInUseRepository extends JpaRepository<TimeInUse, Long> {

  Optional<TimeInUse> findByEquipmentItemId(Long id);

  @Query(nativeQuery = true)
  List<RequiredMaintenanceDetailsModel> getRequiredMaintenanceDetails();
}
