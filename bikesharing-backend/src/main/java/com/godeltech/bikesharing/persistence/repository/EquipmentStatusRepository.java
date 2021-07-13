package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatus, Long> {
  Optional<EquipmentStatus> findByCode(String code);
}
