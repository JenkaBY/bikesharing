package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentGroupRepository extends JpaRepository<EquipmentGroup, Long> {
}
