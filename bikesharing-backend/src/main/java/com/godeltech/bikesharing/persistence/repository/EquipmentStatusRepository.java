package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentStatusRepository extends JpaRepository<EquipmentStatus, Long> {
}
