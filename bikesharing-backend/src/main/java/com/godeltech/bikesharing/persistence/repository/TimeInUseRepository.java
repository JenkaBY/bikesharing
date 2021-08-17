package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.TimeInUse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeInUseRepository extends JpaRepository<TimeInUse, Long> {

  Optional<TimeInUse> findByEquipmentItemId(Long id);
}
