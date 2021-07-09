package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.EquipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentItemRepository extends JpaRepository<EquipmentItem, Long> {
}
