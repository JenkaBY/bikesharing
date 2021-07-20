package com.godeltech.bikesharing.persistence.repository;

import com.godeltech.bikesharing.persistence.entity.RentCost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentCostRepository extends JpaRepository<RentCost, Long> {
  List<RentCost> findByEquipmentGroupCode(String equipmentGroupCode);
}
