package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentGroup;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentGroupRepository extends JpaRepository<EquipmentGroup, Long> {
  Optional<EquipmentGroup> findByCode(String code);
}
